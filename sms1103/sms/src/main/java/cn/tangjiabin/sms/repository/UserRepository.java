package cn.tangjiabin.sms.repository;

import cn.tangjiabin.sms.pojo.Admin;
import cn.tangjiabin.sms.pojo.Message;
import cn.tangjiabin.sms.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findByUserId(Integer userId);
    User findAllByUserNameAndUserPwd(String userName, String userPwd);
    User findByUserName(String userName);
}
