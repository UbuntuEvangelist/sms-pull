package cn.tangjiabin.sms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpReturnModel {

    private int status = HttpStatus.SUCCESS;
    private String message = "SUCCESS";

}
