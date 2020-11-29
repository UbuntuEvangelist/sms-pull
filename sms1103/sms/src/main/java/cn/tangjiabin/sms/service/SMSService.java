package cn.tangjiabin.sms.service;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.SMSDTO;
import cn.tangjiabin.sms.pojo.Rate;
import cn.tangjiabin.sms.pojo.TwilioPhone;
import com.twilio.Twilio;
import io.swagger.models.auth.In;

public interface SMSService {

    ServerResponse findSMSPage(SMSDTO smsdto);

    ServerResponse sendSMS(SMSDTO smsdto);

    ServerResponse findTwilioPage(SMSDTO smsdto);

    ServerResponse findTwilioById(Integer id);

    ServerResponse addTwilioPhone(TwilioPhone phone);

    ServerResponse findRateById(Integer id);

    ServerResponse addRate(Rate rate);

    ServerResponse deleteTwilioById(Integer id);

}
