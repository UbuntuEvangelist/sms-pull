package cn.tangjiabin.sms.service.impl;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.common.TokenManager;
import cn.tangjiabin.sms.common.TokenModel;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.dto.UserNumberDTO;
import cn.tangjiabin.sms.pojo.Admin;
import cn.tangjiabin.sms.pojo.Message;
import cn.tangjiabin.sms.pojo.Rate;
import cn.tangjiabin.sms.pojo.User;
import cn.tangjiabin.sms.repository.InviteRepository;
import cn.tangjiabin.sms.repository.RateRepository;
import cn.tangjiabin.sms.repository.UserRepository;
import cn.tangjiabin.sms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Slf4j
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private RateRepository rateRepository;

    @Override
    public ServerResponse addUser(User saveUser, HttpServletRequest request) {
        User user;
        if (saveUser.getUserId() != null && saveUser.getUserId() != 0) {
            user = userRepository.findByUserId(saveUser.getUserId());
            if (user == null) {
                return ServerResponse.createByErrorMessage("用户不存在");
            }
        } else {
            user = new User();
            user.setCreateDate(new Date());
        }

        if (!StringUtils.isEmpty(saveUser.getUserName())) {
            user.setUserName(saveUser.getUserName());
        }
        if (!StringUtils.isEmpty(saveUser.getUserPwd())) {
            user.setUserPwd(saveUser.getUserPwd());
        }
        if (!StringUtils.isEmpty(saveUser.getUnitPrice())) {
            user.setUnitPrice(saveUser.getUnitPrice());
        }
        if (!StringUtils.isEmpty(saveUser.getUnitMoney())) {
            user.setUnitMoney(saveUser.getUnitMoney());
        }
        if (!StringUtils.isEmpty(saveUser.getRate())) {
            user.setRate(saveUser.getRate());
        }
        if (!StringUtils.isEmpty(saveUser.getPhone())) {
            user.setPhone(saveUser.getPhone());
        }
        if (!StringUtils.isEmpty(saveUser.getBalance())) {
            user.setBalance(saveUser.getBalance());
        }
        if (!StringUtils.isEmpty(saveUser.getNotice())) {
            user.setNotice(saveUser.getNotice());
        }
        if (!StringUtils.isEmpty(saveUser.getRegion())) {
            user.setRegion(saveUser.getRegion());
        }
        if (!StringUtils.isEmpty(saveUser.getState())) {
            user.setState(saveUser.getState());
        }
        User save = userRepository.save(user);
        return ServerResponse.createMessage(200, save);
    }

    @Override
    public ServerResponse findUserPage(UserDTO userDTO) {
        Integer page = userDTO.getPage();
        Integer size = userDTO.getSize();
        size = 10;

        if (page > 0) {
            page = page - 1;
        } else {
            page = 0;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "userId");
        Page<User> userPage = userRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            if (!StringUtils.isEmpty(userDTO.getUserName())) {
                list.add(criteriaBuilder.equal(root.get("userName").as(String.class), userDTO.getUserName()));
            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
        return ServerResponse.createMessage(200, userPage);
    }

    @Override
    public ServerResponse getUserById(Integer userId) {
        if (userId != null && userId != 0) {
            return ServerResponse.createMessage(200, userRepository.findByUserId(userId));
        }
        return ServerResponse.createByErrorCodeMessage(401, "用户不存在");
    }

    @Override
    public ServerResponse findUserNumber() {

        List<User> userList = userRepository.findAll();

        BigDecimal balance = new BigDecimal(0.000);

        for (User user : userList) {
            balance = balance.add(user.getBalance());
        }

        Integer count = userList.size();

        UserNumberDTO numberDTO = new UserNumberDTO();
        numberDTO.setBalance(balance.setScale(3));
        numberDTO.setCount(count);

        return ServerResponse.createBySuccess(200, numberDTO);
    }

    @Override
    public ServerResponse login(UserDTO userDTO, HttpServletRequest request) {
        User user = userRepository.findAllByUserNameAndUserPwd(userDTO.getUserName(), userDTO.getUserPwd());
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
        if (!user.getUserName().equals(userDTO.getUserName()) || !user.getUserPwd().equals(userDTO.getUserPwd())) {
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
        try {
            TokenModel tokenModel = tokenManager.createToken(user.getUserId());
            user.setToken(tokenModel.getToken());
            userRepository.save(user);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(200, user);
    }

    @Override
    public synchronized ServerResponse registerUser(UserDTO userDTO, HttpServletRequest request) {
        if (StringUtils.isEmpty(userDTO.getCode())) {
            return ServerResponse.createByErrorMessage("用户注册必须有管理员邀请码");
        }
        if (inviteRepository.findByCode(userDTO.getCode()) == null) {
            return ServerResponse.createByErrorMessage("管理员邀请码输入错误");
        }
        if (StringUtils.isEmpty(userDTO.getUserName())) {
            return ServerResponse.createByErrorMessage("用户名不能为空");
        }
        if (StringUtils.isEmpty(userDTO.getUserPwd())) {
            return ServerResponse.createByErrorMessage("密码不能为空");
        }
        if (StringUtils.isEmpty(userDTO.getPhone())) {
            return ServerResponse.createByErrorMessage("手机号不能为空");
        }
        if (userRepository.findByUserName(userDTO.getUserName()) != null) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setUserPwd(userDTO.getUserPwd());
        user.setRegion(1);
        user.setState(1);
        user.setUnitPrice(BigDecimal.ONE);
        user.setUnitMoney(BigDecimal.ONE);
        user.setBalance(BigDecimal.ZERO);
        user.setCreateDate(new Date());
        user.setNotice(1);
        user.setPhone(userDTO.getPhone());
        Rate rate = rateRepository.findById(1).get();
        if (rate != null) {
            user.setRate(new BigDecimal(rate.getRate()));
        } else {
            user.setRate(new BigDecimal("50"));
        }
        return ServerResponse.createBySuccess(200, userRepository.save(user));
    }
}
