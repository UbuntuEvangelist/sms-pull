package cn.tangjiabin.sms.service.impl;

import cn.tangjiabin.sms.common.ServerResponse;
import cn.tangjiabin.sms.dto.InviteDTO;
import cn.tangjiabin.sms.dto.UserDTO;
import cn.tangjiabin.sms.dto.UserNumberDTO;
import cn.tangjiabin.sms.pojo.Invite;
import cn.tangjiabin.sms.pojo.User;
import cn.tangjiabin.sms.repository.InviteRepository;
import cn.tangjiabin.sms.service.InviteService;
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
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 用户
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Slf4j
@Service("InviteService")
@Transactional
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Override
    public synchronized ServerResponse addInvite(Invite saveInvite, HttpServletRequest request) {
        Invite invite = null;
        if (saveInvite.getInviteId() != null && saveInvite.getInviteId() != 0) {
            invite = inviteRepository.findByInviteId(saveInvite.getInviteId());
        }
        if (invite == null) {
            invite = new Invite();
        }
        if (!StringUtils.isEmpty(saveInvite.getCode())) {
            invite.setCode(saveInvite.getCode());

        }
        if (!StringUtils.isEmpty(saveInvite.getState())) {
            invite.setState(saveInvite.getState());
        }
        Invite db = inviteRepository.findByCode(saveInvite.getCode());
        if (db != null && (invite.getInviteId() == null || !invite.getInviteId().equals(db.getInviteId()))) {
            return ServerResponse.createByErrorMessage("邀请码已存在");
        }
        return ServerResponse.createMessage(200, inviteRepository.save(invite));
    }

    @Override
    public ServerResponse findInvitePage(InviteDTO inviteDTO) {
        Integer page = inviteDTO.getPage();
        Integer size = inviteDTO.getSize();
        size = 10;
        if (page > 0) {
            page = page - 1;
        } else {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "inviteId");
        Page<Invite> invitePage = inviteRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(inviteDTO.getCode())) {
                list.add(criteriaBuilder.equal(root.get("code").as(String.class), inviteDTO.getCode()));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
        return ServerResponse.createMessage(200, invitePage);
    }

    @Override
    public ServerResponse getInviteById(Integer id) {
        if (id != null && id != 0) {
            return ServerResponse.createMessage(200, inviteRepository.findByInviteId(id));
        }
        return ServerResponse.createByErrorCodeMessage(401, "邀请码不存在");
    }

    @Override
    public synchronized ServerResponse deleteInviteById(Integer inviteId) {
        if (inviteRepository.findByInviteId(inviteId) == null) {
            return ServerResponse.createByErrorCodeMessage(401, "删除失败");
        }
        inviteRepository.deleteByInviteId(inviteId);
        return ServerResponse.createBySuccess();
    }
}
