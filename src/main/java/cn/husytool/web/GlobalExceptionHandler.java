package cn.husytool.web;

import cn.husytool.core.api.APIResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * API全局异常处理器，负责统一处理控制层异常信息
 * 优点：将 Controller 层的异常和数据校验的异常进行统一处理，减少模板代码，减少编码量，提升扩展性和可维护性。
 * 缺点：只能处理 Controller  层未捕获（往外抛）的异常，对于 Interceptor（拦截器）层的异常，Spring 框架层的异常，就无能为力了。
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 捕获自定义异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(WebException.class)
	public APIResult handleApiException(WebException e) {
		return new APIResult(e.getAPICode());
	}

	/**
	 * 捕获所有异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public APIResult handleException(Exception e) {
		return APIResult.error();
	}
}
