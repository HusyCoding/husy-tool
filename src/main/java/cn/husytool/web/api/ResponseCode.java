package cn.husytool.web.api;

/**
 * API统一响应码
 * 全平台系统统一响应码（长度8位），具体编码设计规则如下：
 * 第1位：固定位,用x标识，没有特殊设计含义，只是为了方便存储
 * 第2位：错误级别，1:非系统异常（BUG修复不了）,2:系统异常（BUG必须修复）
 * 第3-4位：功能模块：
 * ==========错误级别1：包含（01:网络错误，02:外部系统错误）
 * ==========错误级别2：包含（03:参数错误，04:权限错误，05:业务错误）
 * 第5-8位（错误内容，每类模块都从0001开始递增）
 * 实例：x1010001
 * @author: husy
 * @date 2020/4/27
 */
public enum ResponseCode {
    //特殊响应码
    SUCCESS("0000", "请求成功"),
    FAILED("1000", "请求失败"),
    //未知异常，友好提示处理
    UNKNOWN_ERROR("x9999999","服务器繁忙，请稍后重试"),

    // 01:网络错误
    TIMEOUT_NETWORK("x1010001","连接超时"),

    // 02:外部系统错误
    SYSTEM_API("x1020001","API接口异常"),
    NOT_FOUND_API("x1020002","API接口地址错误"),
    DENIED_API("x1020003","API拒绝访问"),

    // 03:参数错误
    FAILED_VALID_PARAM("x2030001", "参数校验失败"),
    NULL_PARAM("x2030002", "参数不能为空"),
    INVALID_PARAM("x2030003", "无效请求参数"),
    DATA_EXISTED("x2030004","数据已存在"),
    DATA_NOT_FOUND("x2030005","数据未找到"),

    // 04:权限错误
    UNKNOWN_IP_AUTHORIZATION("x2040001", "未授权IP"),
    BLACK_IP_AUTHORIZATION("x2040002", "黑名单IP"),
    LOGIN_OVERTIME_AUTHORIZATION("x2040003", "登录失效"),
    LOGIN_FAILED_AUTHORIZATION("x2040004", "用户名或密码错误"),
    PERMISSION_DENIED_AUTHORIZATION("x2040005", "访问权限不足"),

    // 05:业务错误



    ;
    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
