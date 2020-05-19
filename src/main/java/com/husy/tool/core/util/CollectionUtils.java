package com.husy.tool.core.util;

import java.util.Collection;

/**
 * @description: Collection操作工具类
 * @author: hsy
 * @date; 2020/5/19
 */
public class CollectionUtils {

	/**
	 * 集合是否为空
	 *
	 * @param collection 集合
	 * @return 是否为空
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}
}
