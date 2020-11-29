package cn.tangjiabin.sms.service.impl;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.common.TokenManager;
import cn.tangjiabin.sms.common.TokenModel;
import cn.tangjiabin.sms.dto.AdminDTO;
import cn.tangjiabin.sms.pojo.Admin;
import cn.tangjiabin.sms.repository.AdminRepository;
import cn.tangjiabin.sms.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Slf4j
@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TokenManager tokenManager;

    @Override
    public ServerResponse login(AdminDTO adminDTO, HttpServletRequest request) {
        Admin admin = adminRepository.findAllByAdminNameAndAdminPwd(adminDTO.getAdminName(), adminDTO.getAdminPwd());
        if (admin == null) {
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
        if (!admin.getAdminName().equals(adminDTO.getAdminName()) || !admin.getAdminPwd().equals(adminDTO.getAdminPwd())) {
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }

        String token = "";
        TokenModel tokenModel = null;
        try {
            tokenModel = tokenManager.createToken(admin.getAdminId());
            token = tokenModel.getToken();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }

        admin.setToken(token);
        Admin admin1 = adminRepository.save(admin);
        return ServerResponse.createBySuccess(200, admin1);
    }

    @Override
    public ServerResponse addAdmin(AdminDTO adminDTO, Integer adminId) {
        Admin admin = new Admin();
        admin.setAdminName(adminDTO.getAdminName());
        admin.setAdminPwd(adminDTO.getAdminPwd());
        admin.setToken("");
        adminRepository.save(admin);
        return ServerResponse.createBySuccess(200, "创建成功");
    }

    @Override
    public ServerResponse update(AdminDTO adminDTO) {
        if (!StringUtils.isEmpty(adminDTO.getAdminPwd())) {
            Admin admin = adminRepository.findByAdminId(adminDTO.getAdminId());
            admin.setAdminPwd(adminDTO.getAdminPwd());
            admin = adminRepository.save(admin);
            return ServerResponse.createMessage(200, "修改成功");
        }
        return ServerResponse.createMessage(411, "修改失败");
    }


}
