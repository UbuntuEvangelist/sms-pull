package cn.tangjiabin.sms.common;


public class HttpStatus {
    /**
     * 参数错误
     */
    public static final int DATA_ERROR = 400;
    /**
     * Action未找到
     */
    public static final int ACTION_ERROR = 404;
    /**
     * 服务器发生错误
     */
    public static final int SERVER_ERROR = 500;
    /**
     * 未登录
     */
    public static final int LOGIN_ERROR = 401;
    /**
     * 请求成功
     */
    public static final int SUCCESS = 200;
    /**
     * 操作失败
     */
    public static final int ERROR = 201;


    public static final int ILLEGAL_VISIT = 403;


//    200	请求成功
//    201	创建成功
//    400	错误的请求
//    401	未验证
//    403	被拒绝
//    404	无法找到
//    409	资源冲突
//    500	服务器内部错误
}
