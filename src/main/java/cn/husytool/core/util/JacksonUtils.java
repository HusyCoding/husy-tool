package cn.husytool.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * 基于Jackson 封装
 *
 * @author husy
 * @date 2020/6/4
 */
public class JacksonUtils {
    private static Logger logger = LoggerFactory.getLogger(JacksonUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 日期格式化
     */
    public static final String DATE_FROMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //忽略值为默认值的属性
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
        // 设置时区为东八区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 所有的日期格式统一为：yyyy-MM-dd HH:mm:ss.SSS ,该配置有时区效果，等同GMT+8
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_FROMAT));
        // 取消默认转换 timestamps 形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略空 Bean 转 json 的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转换为json字符串
     *
     * @param obj
     * @return
     */
    public static <T> String toJSONString(T obj) {
        if (obj != null && !(obj instanceof String)) {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                logger.warn("Parse Object to String error : {}", e.getMessage());
            }
        }
        return (String) obj;
    }

    /**
     * 对象转换为json字符串，并进行美化
     *
     * @param obj
     * @return
     */
    public static <T> String toJSONStringPretty(T obj) {
        if (obj != null && !(obj instanceof String)) {
            try {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                logger.warn("Parse Object to String error : {}", e.getMessage());
            }
        }
        return (String) obj;
    }

    public static JsonNode parse(String content){
        if (!isTrimEmpty(content)){
            try {
                return objectMapper.readValue(content, JsonNode.class);
            } catch (Exception e) {
                logger.warn("Parse String to JsonNode error : {}", e.getMessage());
            }
        }
        return null;
    }
    /**
     * 反序列化：解析json字符串为指定对象
     *
     * @param content
     * @param clazz
     * @return
     */
    public static <T> T parse(String content, Class<T> clazz) {
        if (!isTrimEmpty(content) && clazz != null ) {
            try {
                return clazz.equals(String.class) ? (T) content : objectMapper.readValue(content, clazz);
            } catch (JsonProcessingException e) {
                logger.warn("Parse String to Object error : {}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 反序列化：解析json字符串为指定对象
     *
     * 以集合对象为例
     * <pre>
     *  String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
     *  List<Car> cars = JacksonUtils.parse(jsonArray,new TypeReference<List<Car>>(){});
     * </pre>
     * @param content
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T parse(String content, TypeReference<T> typeReference) {
        if (!isTrimEmpty(content) && typeReference != null) {
            try {
                return (T) (typeReference.getType().equals(String.class) ? content : objectMapper.readValue(content, typeReference));
            } catch (Exception e) {
                logger.warn("Parse String to Object error : {}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 反序列化：解析json字符串为指定集合对象
     * @param text
     * @param collectionClazz
     * @param elementClazz
     * @param <T>
     * @return
     */
    public static <T> T parse(String text, Class<?> collectionClazz, Class<?> elementClazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazz);
        try {
            return objectMapper.readValue(text, javaType);
        } catch (Exception e) {
            logger.warn("Parse String to Object error : {}", e.getMessage());
        }
        return null;
    }
    /**
     * 生成ObjectNode对象
     * @return
     */
    public static ObjectNode createObjectNode(){
        return objectMapper.createObjectNode();
    }

    /**
     * 生成ArrayNode对象
     * @return
     */
    public static ArrayNode createArrayNode(){
        return objectMapper.createArrayNode();
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    private static boolean isTrimEmpty(String str) {
        return isEmpty(str) || isEmpty(str.trim());
    }


    public static void main(String[] args) {
        ObjectNode node = JacksonUtils.createObjectNode();
        node.putPOJO("date",DateUtils.parseToDate("2020-06-08 00:00:00", DateUtils.Pattern.FORMAT_TIME));
        System.out.println(JacksonUtils.toJSONString(node));
    }
}
