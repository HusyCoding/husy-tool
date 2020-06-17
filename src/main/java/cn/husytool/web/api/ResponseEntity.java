package cn.husytool.web.api;

import java.io.Serializable;

/**
 * 自定义接口响应对象
 *
 * @author: husy
 * @date: 2020/6/18 01:43
 */
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = 5123047701998541284L;

    private String code;
    private String message;
    private T data;

    public ResponseEntity() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
    }

    public ResponseEntity(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public ResponseEntity(ResponseCode responseCode, String message) {
        this.code = responseCode.getCode();
        this.message = message;
    }

    public static ResponseEntity success() {
        return new ResponseEntity();
    }

    public static ResponseEntity fail() {
        return new ResponseEntity(ResponseCode.FAILED);
    }

    public static ResponseEntity fail(ResponseCode ResponseCode) {
        return new ResponseEntity(ResponseCode);
    }

    public static ResponseEntity fail(ResponseCode ResponseCode, String message) {
        return new ResponseEntity(ResponseCode, message);
    }

    public static ResponseEntity error() {
        return new ResponseEntity(ResponseCode.UNKNOWN_ERROR);
    }

    public static <T> ResponseEntity data(T obj) {
        ResponseEntity apiResult = ResponseEntity.success();
        apiResult.data = obj;
        return apiResult;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
