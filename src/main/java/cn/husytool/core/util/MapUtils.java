package cn.husytool.core.util;

import java.util.Map;

/**
 * @description: map操作工具类
 * @author: hsy
 * @date; 2020/5/19
 */
public class MapUtils {

	/**
	 * Map是否为空
	 *
	 * @param map 集合
	 * @return 是否为空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return null == map || map.isEmpty();
	}
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
}
