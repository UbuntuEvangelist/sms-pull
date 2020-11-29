package cn.tangjiabin.sms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Entity
@Data
@Table(name = "sms_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer smsId;

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 发件人
     */
    private String sender;
    /**
     * 收件人
     */
    private String receiver;
    /**
     * 发送日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    /**
     * 状态
     * 1-成功
     * 2-失败
     */
    private Integer state;
    /**
     * 地区
     * 1-新加坡
     * 2-马来西亚
     */
    private Integer region;
    /**
     * 单价
     */
    private Double unitPrice;

    private String messageId;
}
