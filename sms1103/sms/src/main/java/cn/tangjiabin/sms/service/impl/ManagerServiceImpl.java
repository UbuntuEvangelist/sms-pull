package cn.tangjiabin.sms.service.impl;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.dto.UserNumberDTO;
import cn.tangjiabin.sms.pojo.Admin;
import cn.tangjiabin.sms.pojo.User;
import cn.tangjiabin.sms.repository.ManagerRepository;
import cn.tangjiabin.sms.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Slf4j
@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public ServerResponse addUser(Admin saveUser, HttpServletRequest request) {
        Admin user;
        if (saveUser.getAdminId() != null && saveUser.getAdminId() != 0) {
            user = managerRepository.findByAdminId(saveUser.getAdminId());
            if (user == null) {
                return ServerResponse.createByErrorMessage("管理员不存在");
            }
        } else {
            user = new Admin();
        }
        if (!StringUtils.isEmpty(saveUser.getAdminName())) {
            user.setAdminName(saveUser.getAdminName());
        }
        if (!StringUtils.isEmpty(saveUser.getAdminPwd())) {
            user.setAdminPwd(saveUser.getAdminPwd());
        }
        if (!StringUtils.isEmpty(saveUser.getPhone())) {
            user.setPhone(saveUser.getPhone());
        }
        Admin save = managerRepository.save(user);
        return ServerResponse.createMessage(200, save);
    }

    @Override
    public ServerResponse findUserPage(UserDTO userDTO) {
        Integer page = userDTO.getPage();
        Integer size = userDTO.getSize();
        size = 10;

        if (page > 0) {
            page = page - 1;
        } else {
            page = 0;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "adminId");
        Page<Admin> userPage = managerRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            if (!StringUtils.isEmpty(userDTO.getUserName())) {
                list.add(criteriaBuilder.equal(root.get("adminName").as(String.class), userDTO.getUserName()));
            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
        return ServerResponse.createMessage(200, userPage);
    }

    @Override
    public ServerResponse getUserById(Integer userId) {
        if (userId != null && userId != 0) {
            return ServerResponse.createMessage(200, managerRepository.findByAdminId(userId));
        }
        return ServerResponse.createByErrorCodeMessage(401, "管理员不存在");
    }
}
