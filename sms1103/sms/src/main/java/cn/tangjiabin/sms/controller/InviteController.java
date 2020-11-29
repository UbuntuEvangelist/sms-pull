package cn.tangjiabin.sms.controller;

import cn.tangjiabin.sms.common.Authorization;
import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.InviteDTO;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.pojo.Invite;
import cn.tangjiabin.sms.pojo.User;
import cn.tangjiabin.sms.service.InviteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Api(value = "邀请码", description = "invite")
@Authorization
@RestController
@RequestMapping("/invite")
public class InviteController {

    @Autowired
    private InviteService inviteService;

    @ApiOperation(value = "添加邀请码")
    @RequestMapping(value = "addInvite", method = RequestMethod.POST)
    public ServerResponse addInvite(@RequestBody Invite invite, @Autowired HttpServletRequest request) {
        ServerResponse response = inviteService.addInvite(invite, request);
        return ServerResponse.createMessage(200, response);
    }

    @Authorization
    @RequestMapping(value = "findInvitePage", method = RequestMethod.POST)
    public ServerResponse findInvitePage(@RequestBody InviteDTO inviteDTO) {
        return inviteService.findInvitePage(inviteDTO);
    }

    @Authorization
    @RequestMapping(value = "getInviteById", method = RequestMethod.GET)
    public ServerResponse getInviteById(@RequestParam Integer inviteId) {
        return inviteService.getInviteById(inviteId);
    }

    @Authorization
    @RequestMapping(value = "deleteInviteById", method = RequestMethod.POST)
    public ServerResponse deleteInviteById(@RequestBody Invite invite) {
        return inviteService.deleteInviteById(invite.getInviteId());
    }

}
