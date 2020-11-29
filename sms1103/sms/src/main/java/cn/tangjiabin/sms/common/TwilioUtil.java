package cn.tangjiabin.sms.common;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.messaging.v1.Service;
import com.twilio.rest.messaging.v1.service.AlphaSender;

/**
 * Twilio
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-17
 */

public class TwilioUtil {

    //    public static final String ACCOUNT_SID = "AC851ae75c0f9633b8ec613bc8b00cce19";
    //    public static final String AUTH_TOKEN = "0eac5c4594759c2834d51631bde65a33";

    public String send(String sid, String token, String to, String from, String body) {


        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(from),
                body)
                .create();


        return message.getSid();
    }

}
