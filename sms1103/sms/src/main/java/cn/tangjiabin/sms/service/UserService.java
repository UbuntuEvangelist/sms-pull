package cn.tangjiabin.sms.service;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ServerResponse addUser(User user, HttpServletRequest request);

    ServerResponse findUserPage(UserDTO userDTO);

    ServerResponse getUserById(Integer userId);

    ServerResponse findUserNumber();

    ServerResponse login(UserDTO userDTO, HttpServletRequest request);

    ServerResponse registerUser(UserDTO userDTO, HttpServletRequest request);
}
