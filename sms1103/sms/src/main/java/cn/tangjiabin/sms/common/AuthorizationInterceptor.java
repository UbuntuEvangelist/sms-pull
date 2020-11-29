package cn.tangjiabin.sms.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 *
 * @author J.Tang
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManagerImpl manager;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 配置允许跨域， 如果未存在跨域的话，可以不用配置
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 配置允许添加的请求头
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Accept,X-Auth-Token");
        // 配置允许通过javascript调用的请求头， 如果不存在可以不用配置
        response.setHeader("Access-Control-Expose-Headers", "X-Auth-Token");
        // 配置允许的请求方式
        response.setHeader("Access-Control-Allow-Method", "GET,HEAD,POST,PUT,DELETE,OPTIONS,PATCH");
        // 配置允许通过javascript调用的请求头， 如果不存在可以不用配置
        response.setHeader("Access-Control-Expose-Headers", "*");


//        monitorService.annal(request);


        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authorization annotation = method.getAnnotation(Authorization.class);
        if (annotation == null) {
            return true;
        }
        String token = request.getHeader("X-Auth-Token");

        if (StringUtils.isEmpty(token)) {
            HttpErrorReturn.writeNoLogin(response.getOutputStream());
            return false;
        }
        try {
            if (manager.checkToken(token)) {
                request.setAttribute("user_id", manager.getToken(token).getUserId());
                return true;
            }
        } catch (Exception e) {
            HttpErrorReturn.writeNoLogin(response.getOutputStream());
            return false;
        }
        HttpErrorReturn.writeNoLogin(response.getOutputStream());
        return false;
    }




}
