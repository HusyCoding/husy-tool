package cn.husytool.web.api;


/**
 * @description: API 接口异常类
 * @author: husy
 * @date 2020/4/27
 */
public class WebException extends RuntimeException {
    private ResponseCode apiCode;

    public WebException(ResponseCode apiCode) {
        super(apiCode.getMessage());
        this.apiCode = apiCode;
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    public WebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResponseCode getResponseCode() {
        return apiCode;
    }

    @Override
    public String toString() {
        return "APIException:-->>" + apiCode.toString();
    }
}
