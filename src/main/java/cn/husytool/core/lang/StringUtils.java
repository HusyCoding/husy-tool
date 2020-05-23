package cn.husytool.core.lang;

import cn.hutool.core.util.ArrayUtil;

import java.util.Objects;

/**
 * @description: 用于对字符串的操作
 * @author: hsy
 * @date; 2020/5/19
 */
public class StringUtils {
	/**
	 * 字符串是否为空，空定义如下:<br>
	 * 1、为null<br>
	 * 2、为""<br>
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 *
	 * @param str 被检测的字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}


	/**
	 * 字符串是否不为空
	 *
	 * @param str 被检测的字符串
	 * @return 是否为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 字符串是否为空白，空定义如下:<br>
	 * 1、为null<br>
	 * 2、为""<br>
	 * 3、为"   "<br>
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param str 被检测的字符串
	 * @return 是否为空
	 */
	public static boolean isTrimEmpty(String str) {
		return isEmpty(str) || isEmpty(str.trim());
	}

	/**
	 * 字符串是否为非空白，
	 *
	 * @param str
	 * @return
	 */
	public static boolean isTrimNotEmpty(String str) {
		return !isTrimEmpty(str);
	}
	/**
	 * 是否包含空字符串
	 *
	 * @param strs 字符串列表
	 * @return 是否包含空字符串
	 */
	public static boolean hasEmpty(String... strs) {
		if (ArrayUtil.isEmpty(strs)) {
			return true;
		}

		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 是否包含空白字符串
	 *
	 * @param strs 字符串列表
	 * @return 是否包含空白字符串
	 */
	public static boolean hasTrimEmpty(String... strs) {
		if (ArrayUtil.isEmpty(strs)) {
			return true;
		}

		for (String str : strs) {
			if (isTrimEmpty(str)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 比较两个字符串是否相等
	 *
	 * @param str1 要比较的字符串1
	 * @param str2 要比较的字符串2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		return Objects.equals(str1,str2);
	}

	/**
	 * 比较两个字符串是否相等（大小写不敏感）
	 *
	 * @param str1 要比较的字符串1
	 * @param str2 要比较的字符串2
	 * @return
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return (str1 == str2) || (str1 != null && str1.equalsIgnoreCase(str2));
	}
}
