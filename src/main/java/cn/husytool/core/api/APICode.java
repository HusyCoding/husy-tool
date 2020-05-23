package cn.husytool.core.api;

/**
 * @description: API统一响应码
 * 具体编码设计
 * 第1位（固定,用x标识，没有特殊设计含义，只是为了方便存储））
 * 第2位（错误级别，1为非系统bug,2为系统bug需要改代码）
 * 第3-4位（功能模块,从01开始，依次顺延）
 * 第5-8位（错误编码，从0001开始，依次顺延）
 * 实例：x1010001
 * @author: husy
 * @date 2020/4/27
 */
public enum APICode {
	//基本响应码
	SUCCESS("00", "请求成功"),
	FAILED("01", "请求失败"),
	SYSTEM_ERROR("-9999", "服务器繁忙,请稍后再试"),
	// 01权限验证模块
	AUTHORIZATION_UNKNOWN_IP("x1010001","未授权IP"),
	AUTHORIZATION_BLACK_IP("x1010002","黑名单IP"),
	AUTHORIZATION_LOGIN_OVERTIME("x1010003","TOKEN失效"),
	AUTHORIZATION_LOGIN_FAILED("x1010004","用户名或密码错误"),
	AUTHORIZATION_PERMISSION_DENIED("x1010005","访问权限不足"),

	// 02报表查询模块
	STATISTIC_PARAM_NULL("x1020001","参数不能为空"),
	STATISTIC_PARAM_INVALID("x1020002","无效请求参数"),
	;
	private String code;
	private String message;

	APICode(String code, String message) {
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
