package com.husy.tool.core;


/**
 * @Description: 随机数生成工具
 * @author: husy
 * @date 2020/1/13
 */
public class RandomUtil extends cn.hutool.core.util.RandomUtil {
	/**
	 * 生成6位长度的数字字符串
	 *
	 * @return
	 */
	public static String randomNum6() {
		long bound = (long) Math.pow(10, 6);
		return String.format(String.format("%06d", getRandom().nextLong(bound)));
	}

	/**
	 * 生成4位长度的数字字符串
	 *
	 * @return
	 */
	public static String randomNum4() {
		long bound = (long) Math.pow(10, 4);
		return String.format(String.format("%04d", getRandom().nextLong(bound)));
	}

	/**
	 * 生成 N 位长度的数字字符串
	 *
	 * @param n 长度
	 * @return
	 */
	public static String randomNumN(int n) {
		long bound = (long) Math.pow(10, n);
		String format = "%0" + n + "d";
		return String.format(String.format(format, getRandom().nextLong(bound)));
	}
}
