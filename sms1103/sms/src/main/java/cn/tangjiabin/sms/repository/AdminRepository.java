package cn.tangjiabin.sms.repository;

import cn.tangjiabin.sms.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findAllByAdminNameAndAdminPwd(String adminName, String adminPwd);

    Admin findByAdminName(String phone);

    Admin findByAdminId(Integer id);

}
