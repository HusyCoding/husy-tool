package cn.husytool.web.api;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * @description: API响应对象
 * @author: husy
 * @date 2020/4/29
 */
public class ResponseEntity extends LinkedHashMap implements Serializable {
    private static final String RESULT_KEY_CODE = "code";
    private static final String RESULT_KEY_MESSAGE = "message";
    private static final String RESULT_KEY_DATA = "data";
    private static final String PAGE_KEY_RECORDS = "records";
    private static final String PAGE_KEY_TOTAL = "total";
    private static final String PAGE_KEY_SIZE = "size";
    private static final String PAGE_KEY_CURRENT = "current";

    public ResponseEntity() {
        put(RESULT_KEY_CODE, ResponseCode.SUCCESS.getCode());
        put(RESULT_KEY_MESSAGE, ResponseCode.SUCCESS.getMessage());
    }

    public ResponseEntity(ResponseCode ResponseCode) {
        put(RESULT_KEY_CODE, ResponseCode.getCode());
        put(RESULT_KEY_MESSAGE, ResponseCode.getMessage());
    }

    public ResponseEntity(ResponseCode ResponseCode,String message) {
        put(RESULT_KEY_CODE, ResponseCode.getCode());
        put(RESULT_KEY_MESSAGE, message);
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

    public static ResponseEntity fail(ResponseCode ResponseCode,String message) {
        return new ResponseEntity(ResponseCode,message);
    }

    public static ResponseEntity error() {
        return new ResponseEntity(ResponseCode.UNKNOWN_ERROR);
    }

    public static ResponseEntity data(T object) {
        ResponseEntity apiResult = ResponseEntity.success();
        apiResult.put(RESULT_KEY_DATA, object);
        return apiResult;
    }

    public static ResponseEntity page(Page page) {
        if (page == null) {
            throw new RuntimeException("The page must not be null");
        }
        ResponseEntity apiResult = ResponseEntity.success();
        apiResult.put(PAGE_KEY_CURRENT, page.getCurrent());
        apiResult.put(PAGE_KEY_SIZE, page.getSize());
        apiResult.put(PAGE_KEY_TOTAL, page.getTotal());
        apiResult.put(PAGE_KEY_RECORDS, page.getRecords());
        return apiResult;
    }
}
