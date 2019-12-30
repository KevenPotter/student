package com.kevenpotter.student.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-30 11:47:52
 * @description 配置文件工具类
 */
public class PropertyUtils {

    /*定义日志记录器，用来记录必要信息*/
    private static Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

    /**
     * @param propertyName 属性名称
     * @return 返回指定[属性名称]的[属性值]
     * @authro KevenPotter
     * @date 2019-12-30 11:50:50
     * @description 读取配置文件, 返回指定[属性名称]的[属性值]
     */
    public static String getPropertyValue(String propertyName) {
        Resource resource = new ClassPathResource("application.properties");
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
            String propertyValue = properties.getProperty("spring.profiles.active");
            Resource childResource = new ClassPathResource("application-" + propertyValue + ".properties");
            properties = PropertiesLoaderUtils.loadProperties(childResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == properties) {
            logger.warn("未获取到配置文件属性");
            return "";
        }
        return properties.getProperty(propertyName);
    }
}
