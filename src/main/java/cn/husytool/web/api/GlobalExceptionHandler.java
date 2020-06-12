package cn.husytool.web.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * API全局异常处理器，负责统一处理控制层异常信息
 * 优点：将 Controller 层的异常和数据校验的异常进行统一处理，减少模板代码，减少编码量，提升扩展性和可维护性。
 * 缺点：只能处理 Controller  层未捕获（往外抛）的异常，对于 Interceptor（拦截器）层的异常，Spring 框架层的异常，就无能为力了。
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * 捕获自定义异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(WebException.class)
	public ResponseEntity handleApiException(WebException e) {
        logger.debug("自定义异常捕获：WebException ==>> ",e.getMessage(),e);
		return ResponseEntity.fail(e.getResponseCode());
	}

    /**
     * 方法参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleApiException(MethodArgumentNotValidException e) {
        logger.debug("方法参数校验异常捕获：MethodArgumentNotValidException ==>> ",e.getMessage(),e);
        return ResponseEntity.fail(ResponseCode.FAILED_VALID_PARAM,e.getBindingResult().getFieldError().getDefaultMessage());
    }

	/**
	 * 捕获所有异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
        logger.debug("全局异常捕获：Exception ==>> ",e.getMessage(),e);
		return ResponseEntity.error();
	}
}
