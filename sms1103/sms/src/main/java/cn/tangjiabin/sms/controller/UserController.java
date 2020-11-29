package cn.tangjiabin.sms.controller;

import cn.tangjiabin.sms.common.Authorization;
import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.AdminDTO;
import cn.tangjiabin.sms.dto.SMSDTO;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.pojo.User;
import cn.tangjiabin.sms.service.UserService;
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
@Api(value = "用户", description = "user")
@Authorization
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public ServerResponse addUser(@RequestBody User user, @Autowired HttpServletRequest request) {
        ServerResponse response = userService.addUser(user, request);
        return ServerResponse.createMessage(200, response);
    }

    @ApiOperation(value = "注册用户")
    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    public ServerResponse registerUser(@RequestBody UserDTO userDTO, @Autowired HttpServletRequest request) {
        ServerResponse response = userService.registerUser(userDTO, request);
        return ServerResponse.createMessage(200, response);
    }

    @Authorization
    @RequestMapping(value = "findUserPage", method = RequestMethod.POST)
    public ServerResponse findUserPage(@RequestBody UserDTO userDTO) {
        return userService.findUserPage(userDTO);
    }

    @Authorization
    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    public ServerResponse getUserById(@RequestParam Integer userId) {
        return userService.getUserById(userId);
    }

    @Authorization
    @RequestMapping(value = "findUserNumber", method = RequestMethod.GET)
    public ServerResponse findUserNumber() {
        return userService.findUserNumber();
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public ServerResponse signIn(@RequestBody UserDTO userDTO, @Autowired HttpServletRequest request) {
        ServerResponse login = userService.login(userDTO, request);
        return ServerResponse.createMessage(200,login);
    }
}
