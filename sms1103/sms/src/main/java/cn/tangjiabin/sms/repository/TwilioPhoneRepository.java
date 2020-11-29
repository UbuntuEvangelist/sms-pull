package cn.tangjiabin.sms.repository;

import cn.tangjiabin.sms.pojo.Message;
import cn.tangjiabin.sms.pojo.TwilioPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwilioPhoneRepository extends JpaRepository<TwilioPhone,Integer>, JpaSpecificationExecutor<TwilioPhone> {
    List<TwilioPhone> findAllByState(Integer state);
}
