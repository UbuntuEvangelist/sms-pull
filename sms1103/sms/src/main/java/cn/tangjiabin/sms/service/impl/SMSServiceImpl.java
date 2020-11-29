package cn.tangjiabin.sms.service.impl;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.common.TokenManager;
import cn.tangjiabin.sms.common.TokenModel;
import cn.tangjiabin.sms.dto.SMSDTO;
import cn.tangjiabin.sms.pojo.Message;
import cn.tangjiabin.sms.pojo.Rate;
import cn.tangjiabin.sms.pojo.TwilioPhone;
import cn.tangjiabin.sms.pojo.User;
import cn.tangjiabin.sms.repository.RateRepository;
import cn.tangjiabin.sms.repository.SMSRepository;
import cn.tangjiabin.sms.repository.TwilioPhoneRepository;
import cn.tangjiabin.sms.repository.UserRepository;
import cn.tangjiabin.sms.service.SMSService;
import com.twilio.Twilio;
import com.twilio.rest.messaging.v1.service.AlphaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 短信
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Slf4j
@Service("SMSService")
public class SMSServiceImpl implements SMSService {

    @Autowired
    private SMSRepository smsRepository;
    @Autowired
    private TwilioPhoneRepository twilioPhoneRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ServerResponse findSMSPage(SMSDTO smsdto) {
        Integer page = smsdto.getPage();
        Integer size = smsdto.getSize();

        size = 10;

        if (page > 0) {
            page = page - 1;
        } else {
            page = 0;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "smsId");
        Page<Message> smsPage = smsRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(smsdto.getContent())) {
                list.add(criteriaBuilder.like(root.get("content").as(String.class), "%" + smsdto.getContent() + "%"));
            }

            if (!StringUtils.isEmpty(smsdto.getSender())) {
                list.add(criteriaBuilder.equal(root.get("sender").as(String.class), smsdto.getSender()));

            }

            if (!StringUtils.isEmpty(smsdto.getReceiver())) {
                list.add(criteriaBuilder.equal(root.get("receiver").as(String.class), smsdto.getReceiver()));
            }

            if (smsdto.getSendTime() != null) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = smsdto.getSendTime();
                try {
                    date = df.parse(df.format(smsdto.getSendTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list.add(criteriaBuilder.greaterThan(root.get("sendTime").as(Date.class), date));
            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
        return ServerResponse.createBySuccess(200, smsPage);
    }

    @Override
    public ServerResponse sendSMS(SMSDTO smsdto) {
        if (!StringUtils.isEmpty(smsdto.getSender()) && !StringUtils.isEmpty(smsdto.getReceiver()) && !StringUtils.isEmpty(smsdto.getContent())) {
            Message message;
            List<Message> messageList = new ArrayList<>();
            String title = smsdto.getTitle();
            String sender = smsdto.getSender();
            String receiver = smsdto.getReceiver();
            String content = smsdto.getContent();
            Integer region = smsdto.getRegion();
            String[] receivers = receiver.split("\n|\r");

            List<String> removes = new ArrayList<>();
            if (smsdto.getType() == 0) {
                String token = smsdto.getToken();
                try {
                    TokenModel tokenModel = tokenManager.getToken(token);
                    User user = userRepository.findByUserId(tokenModel.getUserId());
                    BigDecimal balance = new BigDecimal(String.valueOf(user.getBalance()));
                    BigDecimal price = BigDecimal.ZERO;
                    if (region == 1) {
                        price = user.getUnitPrice().multiply(new BigDecimal(receivers.length));
                    } else if (region == 2) {
                        price = user.getUnitMoney().multiply(new BigDecimal(receivers.length));
                    }
                    if (balance.compareTo(price) >= 0) {
                        balance = balance.subtract(price);
                        user.setBalance(balance);
                        userRepository.save(user);
                    } else {
                        return ServerResponse.createByError();
                    }
                    if (!StringUtils.isEmpty(user.getRate()) && smsdto.getType() == 0) {
                        List<String> list = new ArrayList<>(Arrays.asList(receivers));
                        int size = new BigDecimal(list.size()).multiply(user.getRate())
                                .divide(BigDecimal.TEN.multiply(BigDecimal.TEN), 2, BigDecimal.ROUND_HALF_DOWN).intValue();
                        int each = list.size() - size;
                        Random random = new Random();
                        for (int i = 0; i < each; i++) {
                            int remove = random.nextInt(list.size());
                            removes.add(list.get(remove));
                            list.remove(remove);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ServerResponse.createByError();
                }
            }

            List<TwilioPhone> phoneList = twilioPhoneRepository.findAllByState(1);
            Random random = new Random();
            int index = random.nextInt(phoneList.size());
            String from = phoneList.get(index).getPhone();
            String sid = phoneList.get(index).getSid();
            String token = phoneList.get(index).getToken();

            for (String to : receivers) {
                message = new Message();
                message.setTitle(title);
                message.setSender(sender);
                message.setReceiver(to);
                message.setContent(content);
                message.setSendTime(new Date());
                message.setUnitPrice(0.0);
                message.setState(1);
                message.setRegion(region);

                if (!removes.contains(to)) {
                    //新加坡  +65  马来西亚  +60
                    if (region == 1) {
                        to = "+65" + to;
                    } else if (region == 2) {
                        to = "+60" + to;
                    }
                    String messageId = "";
                    try {
                        Twilio.init(sid, token);
                        if (region == 1) {
                            if (!StringUtils.isEmpty(smsdto.getSenderId())) {
                                from = smsdto.getSenderId();
                                com.twilio.rest.messaging.v1.Service service = com.twilio.rest.messaging.v1.Service.creator("friendly_name").create();
                                String sid2 = service.getSid();
                                AlphaSender alphaSender = AlphaSender.creator(sid2, from).create();
                                String sid1 = alphaSender.getSid();
                            }
                        }
                        com.twilio.rest.api.v2010.account.Message tmessage = com.twilio.rest.api.v2010.account.Message.creator(
                                new com.twilio.type.PhoneNumber(to),
                                new com.twilio.type.PhoneNumber(from),
                                content)
                                .create();
                        messageId = tmessage.getSid();
                    } catch (Exception e) {
                        message.setState(2);
                    }
                    message.setMessageId(messageId);
                }
                messageList.add(message);
            }
            List<Message> saveAll = smsRepository.saveAll(messageList);
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByErrorMessage("发送失败");
        }
    }

    @Override
    public ServerResponse findTwilioPage(SMSDTO smsdto) {
        Integer page = smsdto.getPage();
        Integer size = smsdto.getSize();
        size = 10;

        if (page > 0) {
            page = page - 1;
        } else {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "sid");
        Page<TwilioPhone> twilioPage = twilioPhoneRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("state").as(String.class), "1"));
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
        return ServerResponse.createBySuccess(200, twilioPage);
    }

    @Override
    public ServerResponse findTwilioById(Integer id) {
        if (id != null && id != 0) {
            return ServerResponse.createMessage(200, twilioPhoneRepository.findById(id));
        }
        return ServerResponse.createByErrorCodeMessage(401, "API不存在");
    }

    @Override
    public synchronized ServerResponse addTwilioPhone(TwilioPhone savePhone) {
        TwilioPhone phone = null;
        if (savePhone.getId() != null && savePhone.getId() != 0) {
            phone = twilioPhoneRepository.findById(savePhone.getId()).get();
        }
        if (phone == null) {
            phone = new TwilioPhone();
        }
        if (!StringUtils.isEmpty(savePhone.getSid())) {
            phone.setSid(savePhone.getSid());
        }
        if (!StringUtils.isEmpty(savePhone.getPhone())) {
            phone.setPhone(savePhone.getPhone());
        }
        if (!StringUtils.isEmpty(savePhone.getToken())) {
            phone.setToken(savePhone.getToken());
        }
        phone.setState(1);
        return ServerResponse.createMessage(200, twilioPhoneRepository.save(phone));
    }

    @Override
    public ServerResponse findRateById(Integer id) {
        if (id != null && id != 0) {
            return ServerResponse.createMessage(200, rateRepository.findById(id));
        }
        return ServerResponse.createByErrorCodeMessage(401, "成功率不存在");
    }

    @Override
    public ServerResponse addRate(Rate rate) {
        if (StringUtils.isEmpty(rate.getRate())) {
            return ServerResponse.createByErrorCodeMessage(401, "成功率不存在");
        }
        BigDecimal rateDecimal = new BigDecimal(rate.getRate());
        if (rateDecimal.compareTo(BigDecimal.ZERO) == -1 ||
                rateDecimal.compareTo(BigDecimal.TEN.multiply(BigDecimal.TEN)) == 1) {
            return ServerResponse.createByErrorCodeMessage(401, "成功率超出范围");
        }
        rate.setId(1);
        return ServerResponse.createMessage(200, rateRepository.save(rate));
    }

    @Override
    public ServerResponse deleteTwilioById(Integer id) {
        if (id == null || id == 0) {
            return ServerResponse.createByErrorCodeMessage(401, "API删除失败");
        }
        twilioPhoneRepository.deleteById(id);
        return ServerResponse.createBySuccess();
    }
}
