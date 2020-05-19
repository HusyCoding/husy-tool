package com.husy.tool.core.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 基于Cglib实现的bean拷贝，其性能要比Spring的BeanUtils，Apache的BeanUtils和PropertyUtils要好很多，尤其是数据量比较大的情况下。
 * @author: hsy
 * @date; 2020/5/2
 */
public class BeanCopierUtils {
	/**
	 * 把创建过的BeanCopier实例放到缓存中，避免反复创建，消耗资源。
	 */
	private static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

	/**
	 * 属性复制
	 * 注意：
	 * 1、属性名称相同类型相同的属性，拷贝OK
	 * 2、属性名称相同而类型不同的属性，不会被拷贝。
	 *
	 * @param source 资源类
	 * @param target 目标类
	 */
	public static void copyProperties(Object source, Object target) {
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier;
		if (!BEAN_COPIERS.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			BEAN_COPIERS.put(beanKey, copier);
		} else {
			copier = BEAN_COPIERS.get(beanKey);
		}
		copier.copy(source, target, null);
	}


	private static String generateKey(Class<?> srcClazz, Class<?> destClazz) {
		return srcClazz.getName() + destClazz.getName();
	}
}
