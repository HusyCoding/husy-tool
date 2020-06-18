package cn.husytool.core.util;

import cn.husytool.web.api.ResponseCode;
import cn.husytool.web.api.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
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

    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //忽略值为默认值的属性
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
        // 设置时区为东八区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 所有的日期格式统一为：yyyy-MM-dd HH:mm:ss.SSS ,该配置有时区效果，等同GMT+8
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
         // 美化输出
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
        // 强制转义非ASCII字符
        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);

        // 允许序列化空的POJO类（否则会抛出异常）
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,true);
        // 把java.util.Date, Calendar输出为数字（时间戳）
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //序列化枚举是否以toString()来输出，默认false，即默认以name()来输出
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false);
        //序列化枚举是否以ordinal()来输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,false);
        //序列化单元素数组时不以数组来输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true);
        //序列化POJO领域对象按字母顺序排序属性，默认false,不能适用于java.util.Map系列化
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true);
        //序列化Map时对key进行排序操作，默认false
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
        //序列化char[]时以json数组输出，默认false,即包含数组所有字符的单个JSON字符串
        objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS,true);

        // 在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //在反序列化时该特性决定对于json浮点数，是否使用BigDecimal来序列化。如果不允许，则使用Double序列化。 默认为false
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, false);
        //在反序列化时该特性决定对于json整形（非浮点），是否使用BigInteger来序列化。如果不允许，则根据数值大小来确定 是使用Integer或者Long
        objectMapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, false);
        //在反序列化时该特性决定JSON ARRAY是映射为Object[]还是List<Object>。如果开启，都为Object[]，false时，则使用List  默认为false
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, false);
        //在反序列化时是否使用Enum.toString()的值对json string进行反序列化。这个的设置和WRITE_ENUMS_USING_TO_STRING需要一致。
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
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

    public static JsonNode parse(String content) {
        if (!isTrimEmpty(content)) {
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
        if (!isTrimEmpty(content) && clazz != null) {
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
     * <p>
     * 以集合对象为例
     * <pre>
     *  String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
     *  List<Car> cars = JacksonUtils.parse(jsonArray,new TypeReference<List<Car>>(){});
     * </pre>
     *
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
     *
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
     *
     * @return
     */
    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    /**
     * 生成ArrayNode对象
     *
     * @return
     */
    public static ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean isTrimEmpty(String str) {
        return isEmpty(str) || isEmpty(str.trim());
    }
}
