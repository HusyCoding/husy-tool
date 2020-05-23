package cn.husytool.core.util;

import java.util.regex.Pattern;

/**
 * @Description: 常用正则校验工具
 * @author: husy
 * @date 2020/1/13
 */
public class RegExUtils {
	/**
	 * 判断一个字符是否是汉字
	 * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
	 *
	 * @param c 需要判断的字符
	 * @return 是汉字(true), 不是汉字(false)
	 */
	public static boolean isChineseChar(char c) {
		return String.valueOf(c).matches("[\u4e00-\u9fa5]");
	}

	/**
	 * 手机号号段验证
	 * @param mobile
	 * @param isExact 手机号是否精确匹配，true:是,false:否(默认)
	 * @return
	 */
	public static boolean isMobile(String mobile,boolean isExact){
		RegEx regEx = RegEx.MOBILE_INEXACT;
		if (isExact){
			regEx = RegEx.MOBILE_EXACT;
		}
		return Pattern.matches(regEx.regex,mobile);
	}

	/**
	 * 车牌号码验证
	 * @param number
	 * @param isNewEnergy 是否为新能源车，true:是,false:否(默认)
	 * @return
	 */
	public boolean isLicensePlate(String number, boolean isNewEnergy) {
		RegEx regEx = RegEx.LICENSE_PLATE_COMMON;
		if (isNewEnergy) {
			regEx = RegEx.LICENSE_PLATE_NEW_ENERGY;
		}
		return Pattern.matches(regEx.regex, number);
	}

	public static boolean isIDCard(String number){
		return false;
	}
	public static boolean isEmail(String number){
		return false;
	}
	public static boolean isTelephone(String telephone){
		return false;
	}
	public static boolean isIP(String ip){
		return false;
	}
	public static boolean isURL(String url){
		return false;
	}


	/**
	 * 常用正则表达式规则
	 */
	public enum RegEx{
		/*精确匹配的手机号规则*/
		MOBILE_EXACT("^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|(66)|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$"),
		/*不精确匹配的手机号规则*/
		MOBILE_INEXACT("^[1]([3-9])[0-9]{9}$"),
		/*普通汽车:
		 * 车牌号格式：省份简称（1位汉字） +发牌机关代号（1位字母A-Z + 5位A-Z或0-9( 车牌号不存在字母I和O防止和1、0混淆)（只包括了普通车牌号，教练车，警等车牌号 ,部分部队车，新能源不包括在内）
		 * 京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼军空海北沈兰济南广成使领
		 * */
		LICENSE_PLATE_COMMON("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1}&"),
		/*新能源车:
		 * 车牌号格式：省份简称（1位汉字）+发牌机关代号（1位字母A-Z）+序号（6位），总计8个字符，( 车牌号不存在字母I和O防止和1、0混淆)
		 * 序号位：
		 * ---小型车，第一位：只能用字母D或字母F，第二位：字母或者数字，后四位：必须使用数字. -->>([DF][A-HJ-NP-Z0-9][0-9]{4})
		 * ---大型车，前五位：必须使用数字，第六位：只能用字母D或字母F. -->>([0-9]{5}[DF])
		 *  */
		LICENSE_PLATE_NEW_ENERGY("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF][A-HJ-NP-Z0-9][0-9]{4}))&");
		private String regex;
		RegEx(String regex) {
			this.regex = regex;
		}
	}
}
