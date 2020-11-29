package cn.tangjiabin.sms.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**保证序列化json的时候,如果是null的对象,key也会消失
 * @author tang*/
@SuppressWarnings("ALL")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }
    private ServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public static ServerResponse createMessageData(Integer key, Object val) {
        return new ServerResponse(key,val);
    }

    /**使之不在json序列化结果当中*/
    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }


    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

//    public static <T> ServerResponse<T> createBySuccess(T data){
//        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
//    }

    public static <T> ServerResponse<T> createBySuccess(Integer code,T data){
        return new ServerResponse<T>(code,data);
    }


    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }


    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }

    public static <T> ServerResponse<T> createMessage(int code ,String msg,T data){
        return new ServerResponse<T>(code,msg,data);
    }

//    public static <T> ServerResponse<T> createMessage(KVResult<T> result){
//        return createMessage(result.getKey(),result.getVal());
//    }

    public static <T> ServerResponse<T> createMessage(int code ,T data){

        if(data instanceof String){
            return new ServerResponse<T>(code,data.toString());
        }
        return new ServerResponse<T>(code,data);
    }








}
