package cn.tangjiabin.sms.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Twilio手机号
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-17
 */
@Entity
@Data
@Table(name = "sms_twilio_phone")
public class TwilioPhone {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String sid;

    private String token;

    private String phone;

    private Integer state;

}
