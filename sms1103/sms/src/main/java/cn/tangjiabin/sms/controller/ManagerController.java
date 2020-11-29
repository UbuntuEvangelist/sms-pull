package cn.tangjiabin.sms.controller;

import cn.tangjiabin.sms.common.Authorization;
import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.pojo.Admin;
import cn.tangjiabin.sms.pojo.User;
import cn.tangjiabin.sms.service.ManagerService;
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
@Api(value = "管理员", description = "manager")
@Authorization
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @ApiOperation(value = "添加管理员")
    @RequestMapping(value = "addManager", method = RequestMethod.POST)
    public ServerResponse addManager(@RequestBody Admin user, @Autowired HttpServletRequest request) {
        ServerResponse response = managerService.addUser(user, request);
        return ServerResponse.createMessage(200, response);
    }

    @Authorization
    @RequestMapping(value = "findManagerPage", method = RequestMethod.POST)
    public ServerResponse findManagerPage(@RequestBody UserDTO userDTO) {
        return managerService.findUserPage(userDTO);
    }

    @Authorization
    @RequestMapping(value = "getManagerById", method = RequestMethod.GET)
    public ServerResponse getManagerById(@RequestParam Integer userId) {
        return managerService.getUserById(userId);
    }
}
