package cn.tangjiabin.sms.service;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.pojo.Admin;
import cn.tangjiabin.sms.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface ManagerService {

    ServerResponse addUser(Admin user, HttpServletRequest request);

    ServerResponse findUserPage(UserDTO userDTO);

    ServerResponse getUserById(Integer userId);
}
