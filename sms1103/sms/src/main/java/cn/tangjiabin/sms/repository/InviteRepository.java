package cn.tangjiabin.sms.repository;

import cn.tangjiabin.sms.pojo.Invite;
import cn.tangjiabin.sms.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<Invite,Integer>, JpaSpecificationExecutor<Invite> {
    Invite findByInviteId(Integer id);
    Invite findByCode(String code);
    void deleteByInviteId(Integer id);
}
