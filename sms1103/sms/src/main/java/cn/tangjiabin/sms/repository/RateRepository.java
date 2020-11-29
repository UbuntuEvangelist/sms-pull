package cn.tangjiabin.sms.repository;

import cn.tangjiabin.sms.pojo.Rate;
import cn.tangjiabin.sms.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate,Integer>, JpaSpecificationExecutor<Rate> {
}
