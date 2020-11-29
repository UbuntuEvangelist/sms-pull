package cn.tangjiabin.sms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Data
public class SMSDTO {

    private int page;

    private int size;

    private String title;

    /**
     * 指定发送者的名字
     */
    private String senderId;

    private String sender;

    private String receiver;

    private String content;

    private Integer region;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    private Integer type;

    private String token;
}
