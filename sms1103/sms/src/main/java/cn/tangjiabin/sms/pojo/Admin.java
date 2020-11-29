package cn.tangjiabin.sms.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * 管理员
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-14
 */
@Entity
@Data
@Table(name = "sms_admin")
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer adminId;

    private String adminName;

    private String adminPwd;

    private String token;

    private String phone;
}
