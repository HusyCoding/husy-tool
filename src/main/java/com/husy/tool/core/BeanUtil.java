package com.husy.tool.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: Spring Bean 对象工具
 * @author: husy
 * @date 2020/1/13
 */
@Component
public class BeanUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanUtil.applicationContext=applicationContext;
	}

	public static <T> T getBean(String beanName) {
		if(applicationContext.containsBean(beanName)){
			return (T) applicationContext.getBean(beanName);
		}else{
			return null;
		}
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> baseType){
		return applicationContext.getBeansOfType(baseType);
	}
}
