package com.husy.tool.core.util;

/**
 * @description: 数值操作对象
 * @author: husy
 * @date 2020/5/20
 */
public class NumberUtils {
	public static boolean equals(Integer num1, Integer num2) {
		return num1 != null ? num1.equals(num2) : num2 == null;
	}
}
