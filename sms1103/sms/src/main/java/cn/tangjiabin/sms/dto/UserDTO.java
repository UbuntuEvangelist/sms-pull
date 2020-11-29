package cn.tangjiabin.sms.dto;

import lombok.Data;

/**
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-15
 */
@Data
public class UserDTO {
    private int page;

    private int size;

    private String userName;

    private String userPwd;

    private Integer userId;

    private String code;

    private String phone;
}
