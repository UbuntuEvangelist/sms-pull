package cn.tangjiabin.sms.common;

import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class HttpErrorReturn {

    public static void writeNoLogin(OutputStream outputStream) throws IOException {
        outputStream.write(GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.LOGIN_ERROR, "Not login")).getBytes("UTF-8"));
    }

    public static void writeLackAuthority(OutputStream outputStream) throws IOException {
        outputStream.write(GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.ILLEGAL_VISIT, "Illegal visit")).getBytes("UTF-8"));
    }

    public static void writeDataError(OutputStream outputStream, Exception ex) throws IOException {
        outputStream.write(GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.DATA_ERROR, "Param error:" + ex.getMessage())).getBytes("UTF-8"));
    }

    public static String writeDataTypeMissingError(TypeMismatchException ex) {
        return GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.DATA_ERROR, "Param type mismatch,Param " + ex.getPropertyName() + "Type is: " + ex.getRequiredType()));
    }

    public static String writeDataParamsMissingError(MissingServletRequestParameterException ex) {
        return GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.DATA_ERROR, "Param missing: " + ex.getParameterName()));
    }

    public static String writeDataParamsMissingError(ServletRequestBindingException ex) {
        return GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.DATA_ERROR, ex.getMessage()));
    }

    public static String writeDataActionMissingError(NoHandlerFoundException ex) {
        return GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.ACTION_ERROR, "Action not find " + ex.getRequestURL()));
    }

    public static String writeDataUnknownError(Exception ex) {
        return GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.SERVER_ERROR, ex.getMessage()));
    }

    public static String writeDataUnknownError(int status, String message) {
        return GsonUtils.create().toJson(new HttpReturnModel(status, message));
    }

    public static void writeServerError(OutputStream outputStream, Exception ex) throws IOException {
        outputStream.write(GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.SERVER_ERROR, "Service error:" + ex.getMessage())).getBytes("UTF-8"));
    }

    public static String writeServerError(Exception ex) {
        return GsonUtils.create().toJson(new HttpReturnModel(HttpStatus.SERVER_ERROR, "Service error:" + ex.getMessage()));
    }

}
