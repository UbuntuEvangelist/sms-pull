package cn.tangjiabin.sms.controller;

import cn.tangjiabin.sms.common.Authorization;
import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.AdminDTO;
import cn.tangjiabin.sms.dto.SMSDTO;
import cn.tangjiabin.sms.pojo.Invite;
import cn.tangjiabin.sms.pojo.Rate;
import cn.tangjiabin.sms.pojo.TwilioPhone;
import cn.tangjiabin.sms.service.AdminService;
import cn.tangjiabin.sms.service.SMSService;
import com.twilio.Twilio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * 短信
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Api(value = "短信", description = "sms")
@Authorization
@RestController
@RequestMapping("/sms")
public class SMSController {

    @Autowired
    private SMSService smsService;

    @Authorization
    @RequestMapping(value = "findSMSPage", method = RequestMethod.POST)
    public ServerResponse findSMSPage(@RequestBody SMSDTO smsdto, @ApiIgnore @RequestAttribute Integer user_id) {
        return smsService.findSMSPage(smsdto);
    }

    @Authorization
    @RequestMapping(value = "sendSMS", method = RequestMethod.POST)
    public synchronized ServerResponse sendSMS(@RequestBody SMSDTO smsdto, @ApiIgnore @RequestAttribute Integer user_id) {
        if(smsdto.getType() == 0){
            smsdto.setSender("用户");
        }else{
            smsdto.setSender("管理员");
        }
        return smsService.sendSMS(smsdto);
    }

    @Authorization
    @RequestMapping(value = "findTwilioPage", method = RequestMethod.POST)
    public ServerResponse findTwilioPage(@RequestBody SMSDTO smsdto) {
        return smsService.findTwilioPage(smsdto);
    }

    @Authorization
    @RequestMapping(value = "findTwilioById", method = RequestMethod.GET)
    public ServerResponse findTwilioById(Integer id) {
        return smsService.findTwilioById(id);
    }

    @Authorization
    @RequestMapping(value = "addTwilioPhone", method = RequestMethod.POST)
    public ServerResponse addTwilioPhone(@RequestBody TwilioPhone phone) {
        return smsService.addTwilioPhone(phone);
    }

    @Authorization
    @RequestMapping(value = "findRateById", method = RequestMethod.GET)
    public ServerResponse findRateById(Integer id) {
        return smsService.findRateById(id);
    }

    @Authorization
    @RequestMapping(value = "addRate", method = RequestMethod.POST)
    public ServerResponse addRate(@RequestBody Rate rate) {
        return smsService.addRate(rate);
    }

    @Authorization
    @RequestMapping(value = "deleteTwilioById", method = RequestMethod.POST)
    public ServerResponse deleteTwilioById(@RequestBody TwilioPhone phone) {
        return smsService.deleteTwilioById(phone.getId());
    }
}
