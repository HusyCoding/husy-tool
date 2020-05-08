package com.husy.tool.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 日期工具类
 * @author: husy
 * @date 2020/5/8
 */
public class DateUtils {
	/**
	 * 计算2个时间间隔（年）
	 *
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 */
	public static int yearInterval(Date beforeDate, Date afterDate) {
		// 如果 beforeDate 比 afterDate 大，则计算负数
		boolean isNegative = beforeDate.compareTo(afterDate) > 0;
		if (isNegative) {
			//交换
			Date temp = beforeDate;
			beforeDate = afterDate;
			afterDate = temp;
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(beforeDate);
		int bYear = calendar.get(Calendar.YEAR);
		int bMonth = calendar.get(Calendar.MONTH);
		int bDay = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(afterDate);
		int aYear = calendar.get(Calendar.YEAR);
		int aMonth = calendar.get(Calendar.MONTH);
		int aDay = calendar.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值
		int yearInterval = aYear - bYear;
		// 如果 afterDate 的月-日 小于 的 beforeDate 月-日 ，则不足年，那么 yearInterval-- 这样就得到了相差的年数
		if (bMonth > aMonth || (bMonth == aMonth && bDay > aDay)) {
			yearInterval--;
		}
		return yearInterval * (isNegative ? -1 : 1);
	}

	/**
	 * 计算2个时间间隔（月）
	 *
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 */
	public static int monthInterval(Date beforeDate, Date afterDate) {
		// 如果 beforeDate 比 afterDate 大，则计算负数
		boolean isNegative = beforeDate.compareTo(afterDate) > 0;
		if (isNegative) {
			//交换
			Date temp = beforeDate;
			beforeDate = afterDate;
			afterDate = temp;
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(beforeDate);
		int bYear = calendar.get(Calendar.YEAR);
		int bMonth = calendar.get(Calendar.MONTH);
		int bDay = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(afterDate);
		int aYear = calendar.get(Calendar.YEAR);
		int aMonth = calendar.get(Calendar.MONTH);
		int aDay = calendar.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值
		int yearInterval = aYear - bYear;
		int monthInterval = aMonth - bMonth;
		// 如果 afterDate 的月-日 小于 的 beforeDate 月-日 ，那么就是不足年，需要减去1年
		if (bMonth > aMonth || (bMonth == aMonth && bDay > aDay)) {
			yearInterval -= 1;
			monthInterval = (aMonth + 12) - bMonth;
		}
		if (bDay > aDay) {
			monthInterval--;
		}
		return (yearInterval * 12 + monthInterval) * (isNegative ? -1 : 1);
	}


	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdf.parse("2008-08-02");
		Date d2 = sdf.parse("2009-08-01");

//		System.out.println(d2.compareTo(d1));
		System.out.println(monthInterval(d1, d2));
	}
}
