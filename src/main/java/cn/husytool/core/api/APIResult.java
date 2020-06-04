package cn.husytool.core.api;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * @description: API响应对象
 * @author: husy
 * @date 2020/4/29
 */
public class APIResult extends LinkedHashMap implements Serializable {
    private static final String RESULT_KEY_CODE = "code";
    private static final String RESULT_KEY_MESSAGE = "message";
    private static final String RESULT_KEY_DATA = "data";
    private static final String PAGE_KEY_RECORDS = "records";
    private static final String PAGE_KEY_TOTAL = "total";
    private static final String PAGE_KEY_SIZE = "size";
    private static final String PAGE_KEY_CURRENT = "current";

    public APIResult() {
        put(RESULT_KEY_CODE, APICode.SUCCESS.getCode());
        put(RESULT_KEY_MESSAGE, APICode.SUCCESS.getMessage());
    }

    public APIResult(APICode APICode) {
        put(RESULT_KEY_CODE, APICode.getCode());
        put(RESULT_KEY_MESSAGE, APICode.getMessage());
    }

    public static APIResult success() {
        return new APIResult();
    }

    public static APIResult fail() {
        return new APIResult(APICode.FAILED);
    }

    public static APIResult fail(APICode APICode) {
        return new APIResult(APICode);
    }

    public static APIResult error(APICode APICode) {
        return new APIResult(APICode);
    }

    public static APIResult error() {
        return new APIResult(APICode.UNKNOWN_ERROR);
    }

    public static APIResult data(Object object) {
        APIResult apiResult = APIResult.success();
        apiResult.put(RESULT_KEY_DATA, object);
        return apiResult;
    }

    public static APIResult page(Page page) {
        if (page == null) {
            throw new RuntimeException("The page must not be null");
        }
        APIResult apiResult = APIResult.success();
        apiResult.put(PAGE_KEY_CURRENT, page.getCurrent());
        apiResult.put(PAGE_KEY_SIZE, page.getSize());
        apiResult.put(PAGE_KEY_TOTAL, page.getTotal());
        apiResult.put(PAGE_KEY_RECORDS, page.getRecords());
        return apiResult;
    }
}
