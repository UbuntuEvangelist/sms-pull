package cn.tangjiabin.sms.repository;

import cn.tangjiabin.sms.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Repository
public interface SMSRepository extends JpaRepository<Message,Integer>, JpaSpecificationExecutor<Message> {
}
