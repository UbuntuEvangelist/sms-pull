package cn.tangjiabin.sms;

import cn.tangjiabin.sms.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 全局异常处理
 *
 * @author J.Tang
 * @version V1.0
 * @email seven_tjb@163.com
 * @date 2018-11-14
 */
@ControllerAdvice
public class GlobalExceptionsHandling {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandling.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServerResponse exceptionsHandle(Exception e) {

        StringBuilder sb = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sb.append("CONSOLE:");
        sb.append(sdf.format(new Date()));
        sb.append("<br/> \n ");
        sb.append(e.getMessage());
        sb.append("<br/> \n ");

        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append("<br/> \n ");
        }

        logger.error(sb.toString());

        return ServerResponse.createMessage(500, "系统错误");
    }


}
