package cn.tangjiabin.sms.controller;

import cn.tangjiabin.sms.common.Authorization;
import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.AdminDTO;
import cn.tangjiabin.sms.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Api(value = "管理员",description = "admin")
@Authorization
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "管理员登录")
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public ServerResponse signIn(@RequestBody AdminDTO adminDTO, @Autowired HttpServletRequest request) {
        ServerResponse login = adminService.login(adminDTO, request);
        return ServerResponse.createMessage(200,login);
    }

    @Authorization
    @ApiOperation(value = "更新管理员")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse update(@RequestBody AdminDTO adminDTO, @Autowired HttpServletRequest request) {
        Integer adminId = (Integer) request.getAttribute("user_id");
        adminDTO.setAdminId(adminId);
        ServerResponse login = adminService.update(adminDTO);

        return ServerResponse.createMessage(200,login);
    }

}
