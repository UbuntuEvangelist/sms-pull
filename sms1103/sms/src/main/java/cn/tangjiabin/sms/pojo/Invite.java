package cn.tangjiabin.sms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Entity
@Data
@Table(name = "sms_invite")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inviteId;
    /**
     * 邀请码
     */
    private String code;
    /**
     * 状态
     * 1-正常
     * 2-禁用
     */
    private Integer state;
}
