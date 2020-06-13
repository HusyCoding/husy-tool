package cn.husytool.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
/**
 * 配置文件工具类
 *
 * @author husy
 * @date 2020/6/11
 */
public class PropertiesUtils {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    private static final String DEFAULT_PROPERTIES = "spring.properties";
    private static Properties props = new Properties();

    static {
        try(InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES) ) {
            props.load(new InputStreamReader(in, DEFAULT_ENCODING));
        } catch (IOException ex) {
            logger.info("Could not load properties from classpath:" + DEFAULT_PROPERTIES + ": " + ex.getMessage());
        }
    }

    /**
     * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入.
     * 文件路径使用Spring Resource格式, 文件编码使用UTF-8.
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
     */
    public static Properties loadProperties(String... resourcesPaths) {
        for (String location : resourcesPaths) {
            logger.debug("Loading properties file from:" + location);
            Resource resource = resourceLoader.getResource(location);
            try(InputStream in = resource.getInputStream()) {
                propertiesPersister.load(props, new InputStreamReader(in, DEFAULT_ENCODING));
            } catch (IOException ex) {
                logger.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
            }
        }
        return props;
    }
    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static void updateProperties(String key, String value) {
        props.setProperty(key, value);
    }
}
