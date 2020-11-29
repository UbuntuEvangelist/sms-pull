package cn.tangjiabin.sms.service;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.InviteDTO;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.pojo.Invite;
import cn.tangjiabin.sms.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface InviteService {
    ServerResponse addInvite(Invite invite, HttpServletRequest request);

    ServerResponse findInvitePage(InviteDTO inviteDTO);

    ServerResponse getInviteById(Integer id);

    ServerResponse deleteInviteById(Integer inviteId);
}
