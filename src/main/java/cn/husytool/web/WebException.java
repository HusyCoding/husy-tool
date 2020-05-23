package cn.husytool.web;


import cn.husytool.core.api.APICode;

/**
 * @description: API 接口异常类
 * @author: husy
 * @date 2020/4/27
 */
public class WebException extends RuntimeException{
	private APICode apiCode;

	public WebException(APICode apiCode) {
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

	public APICode getAPICode() {
		return apiCode;
	}

	@Override
	public String toString() {
		return "APIException:-->>" + apiCode.toString();
	}
}