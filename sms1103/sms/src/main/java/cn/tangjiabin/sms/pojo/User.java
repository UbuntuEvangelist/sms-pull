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
@Table(name = "sms_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPwd;
    /**
     * 地区
     * 1-新加坡
     * 2-马来西亚
     */
    private Integer region;
    /**
     * 新加坡单价
     */
    private BigDecimal unitPrice;
    /**
     * 马来西亚单价
     */
    private BigDecimal unitMoney;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 状态
     * 1-正常
     * 2-禁用
     */
    private Integer state;
    /**
     * 通知
     * 1-通知
     * 2-不通知
     */
    private Integer notice;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 授权验证
     */
    private String token;
    /**
     * 成功率
     */
    private BigDecimal rate;
}
