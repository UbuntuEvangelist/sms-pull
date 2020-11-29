package cn.tangjiabin.sms.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2020-09-17
 */
@Data
public class UserNumberDTO {

    private BigDecimal balance;

    private Integer count;
}
