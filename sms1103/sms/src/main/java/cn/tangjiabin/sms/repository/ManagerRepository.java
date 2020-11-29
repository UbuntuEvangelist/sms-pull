package cn.tangjiabin.sms.repository;

import cn.tangjiabin.sms.pojo.Admin;
import cn.tangjiabin.sms.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Admin,Integer>, JpaSpecificationExecutor<Admin> {
    Admin findByAdminId(Integer userId);
}
