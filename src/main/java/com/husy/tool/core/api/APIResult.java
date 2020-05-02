package com.husy.tool.core.api;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: API响应对象
 * @author: husy
 * @date 2020/4/29
 */
public class APIResult extends HashMap {
	private static final String RESULT_KEY_CODE = "code";
	private static final String RESULT_KEY_MESSAGE = "message";
	private static final String RESULT_KEY_DATA = "data";
	private static final String PAGE_KEY_RECORDS = "records";
	private static final String PAGE_KEY_TOTAL = "total";
	private static final String PAGE_KEY_SIZE = "size";
	private static final String PAGE_KEY_CURRENT= "current";

	public APIResult() {
		put(RESULT_KEY_CODE, APICode.SUCCESS.getCode());
		put(RESULT_KEY_MESSAGE, APICode.SUCCESS.getMessage());
	}
	public APIResult(APICode apiCode) {
		put(RESULT_KEY_CODE, apiCode.getCode());
		put(RESULT_KEY_MESSAGE, apiCode.getMessage());
	}
	public static APIResult success() {
		return new APIResult();
	}
	public static APIResult fail(APICode apiCode) {
		return new APIResult(apiCode);
	}
	public static APIResult error() {
		return new APIResult(APICode.SYSTEM_ERROR);
	}
	public static APIResult data(Object object) {
		APIResult resultObj = new APIResult();
		resultObj.put(RESULT_KEY_DATA, object);
		return resultObj;
	}
	public static APIResult page(Page page) {
		if (page == null) {
			throw new RuntimeException("The page must not be null");
		}
		Map<String,Object> pageObj = new HashMap<>(4);
		pageObj.put(PAGE_KEY_TOTAL, page.getTotal());
		pageObj.put(PAGE_KEY_RECORDS, page.getRecords());
		pageObj.put(PAGE_KEY_SIZE, page.getSize());
		pageObj.put(PAGE_KEY_CURRENT, page.getCurrent());
		return APIResult.data(pageObj);
	}
}
