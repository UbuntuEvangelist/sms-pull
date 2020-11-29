package cn.tangjiabin.sms.service;



import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.AdminDTO;

import javax.servlet.http.HttpServletRequest;


public interface AdminService {

    ServerResponse login(AdminDTO adminDTO, HttpServletRequest request);

    ServerResponse addAdmin(AdminDTO adminDTO,Integer adminId);

    ServerResponse update(AdminDTO adminDTO);
}
