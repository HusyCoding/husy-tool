package cn.husytool.core.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
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
			swap(beforeDate,afterDate);
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
		boolean isFullYear = bMonth > aMonth || (bMonth == aMonth && bDay > aDay);
		if (isFullYear) {
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
			swap(beforeDate,afterDate);
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
		boolean isFullYear = bMonth > aMonth || (bMonth == aMonth && bDay > aDay);
		if (isFullYear) {
			yearInterval -= 1;
			monthInterval = (aMonth + 12) - bMonth;
		}
		if (bDay > aDay) {
			monthInterval--;
		}
		return (yearInterval * 12 + monthInterval) * (isNegative ? -1 : 1);
	}
	/**
	 * 获取当前时间。默认模板：yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String getNow() {
		return getNow(Pattern.FORMAT_TIME);
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static String getNow(Pattern pattern) {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.pattern);
		return localDateTime.format(formatter);
	}

	/**
	 * 获取当前日期，默认模板：yyyy-MM-dd
	 *
	 * @return
	 */
	public static String getToday() {
		return getNow(Pattern.FORMAT_DATE);
	}

	/**
	 * 获取本月第一天日期
	 *
	 * @return
	 */
	public static LocalDate getFristDay() {
		return getFristDay(0);
	}

	/**
	 * 获取n月后所在月份的第一天日期
	 *
	 * @param n 可为负数，表示n 月前
	 * @return
	 */
	public static LocalDate getFristDay(int n) {
		LocalDate today = LocalDate.now();
		today = today.plusMonths(n);
		LocalDate firstday = today.with(TemporalAdjusters.firstDayOfMonth());
		return firstday;
	}

	/**
	 * 获取本月最后一天日期
	 *
	 * @return
	 */
	public static LocalDate getLastDay() {
		return getLastDay(0);
	}

	/**
	 * 获取n月后所在月份的最后一天日期，
	 *
	 * @param n 可为负数，表示n 月前
	 * @return
	 */
	public static LocalDate getLastDay(int n) {
		LocalDate today = LocalDate.now();
		today = today.plusMonths(n);
		LocalDate firstday = today.with(TemporalAdjusters.lastDayOfMonth());
		return firstday;
	}

	/**
	 * 获取本周第一天的日期
	 *
	 * @return
	 */
	public static LocalDate getFirstDayOfWeek() {
		LocalDate localDate = LocalDate.now();
		int weekDay = localDate.getDayOfWeek().getValue() - 1;
		LocalDate firstDay = localDate.minusDays(weekDay);
		return firstDay;
	}



	/**
	 * 获取 n年或n月或n天后的日期，
	 *
	 * @param n 为负数表示未来的日期
	 * @return
	 */
	public static LocalDate getDate(int n, ChronoUnit chronoUnit) {
		LocalDate today = LocalDate.now();
		return today.plus(n, chronoUnit);
	}


	/**
	 * 获取毫秒级时间戳
	 *
	 * @return
	 */
	public static Long getTimeStampMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取毫秒级时间戳
	 *
	 * @return
	 */
	public static Long getTimeStamp() {
		return getTimeStampMillis() / 1000L;
	}

	/**
	 * 格式化日期为字符串
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, Pattern pattern) {
		return format(transform(date), pattern);
	}

	/**
	 * 格式化日期为字符串
	 *
	 * @param localDateTime
	 * @param pattern
	 * @return
	 */
	public static String format(LocalDateTime localDateTime, Pattern pattern) {
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern.pattern));
	}

	/**
	 * 格式化日期为字符串
	 *
	 * @param localDate
	 * @param pattern
	 * @return
	 */
	public static String format(LocalDate localDate, Pattern pattern) {
		return localDate.format(DateTimeFormatter.ofPattern(pattern.pattern));
	}

	/**
	 * 解析字符串为LocalDateTime
	 *
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static LocalDateTime parseToLocalDateTime(String dateStr, Pattern pattern) {
		return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern.pattern));
	}

	/**
	 * 解析字符串为LocalDate
	 *
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static LocalDate parseToLocalDate(String dateStr, Pattern pattern) {
		return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern.pattern));
	}

	/**
	 * 解析字符串为LocalTime
	 *
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static LocalTime parseToLocalTime(String dateStr, Pattern pattern) {
		return LocalTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern.pattern));
	}


	/**
	 * 解析字符串为Date
	 *
	 * @param dateStr       必须是日期+时间方式，如："2019-09-09 02:02:23"
	 * @param pattern
	 * @return
	 */
	public static Date parseToDate(String dateStr, Pattern pattern) {
		LocalDateTime localDateTime = parseToLocalDateTime(dateStr, pattern);
		return transform(localDateTime);
	}

	/**
	 * 获取年
	 *
	 * @return
	 */
	public static int getYear() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.get(ChronoField.YEAR);
	}

	/**
	 * 获取月份
	 *
	 * @return
	 */
	public static int getMonth() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.get(ChronoField.MONTH_OF_YEAR);
	}

	/**
	 * 获取本周在今年为第几周
	 *
	 * @return
	 */
	public static int getWeekofYear() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
	}

	/**
	 * 获取本周在本月为第几周，1至7号为第1 周
	 *
	 * @return
	 */
	public static int getWeekofMonth() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
	}

	/**
	 * 获取今天为本月的第几天
	 *
	 * @return
	 */
	public static int getDayOfMonth() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.get(ChronoField.DAY_OF_MONTH);
	}

	/**
	 * Duration 适合精细的时差计算场景。
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Duration between(LocalDateTime d1, LocalDateTime d2) {
		return Duration.between(d1, d2);
	}

	/**
	 * ChronoUnit 既可以计算年、月、日的差值，也可以计算时、分、秒的差值。
	 * 但是有一些需要注意的地方：在上面列举的例子中，在计算年、月、日的差值时参数既可以传 LocalDate 也可以传 LocalDateTime，
	 * 但是在计算时、分、秒的差值时只能使用 LocalDateTime 类型的参数。
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long between(Temporal d1, Temporal d2, ChronoUnit chronoUnit) {
		long interval = 0;
		switch (chronoUnit) {
			case YEARS:
				interval = ChronoUnit.YEARS.between(d1, d2);
				break;
			case MONTHS:
				interval = ChronoUnit.MONTHS.between(d1, d2);
				break;
			case DAYS:
				interval = ChronoUnit.DAYS.between(d1, d2);
				break;
			case HOURS:
				interval = ChronoUnit.HOURS.between(d1, d2);
				break;
			case MINUTES:
				interval = ChronoUnit.MINUTES.between(d1, d2);
				break;
			case SECONDS:
				interval = ChronoUnit.SECONDS.between(d1, d2);
				break;
			case MILLIS:
				interval = ChronoUnit.MILLIS.between(d1, d2);
				break;
		}
		return interval;
	}

	/**
	 * 转换日期类型 Date -> LocalDateTime
	 *
	 * @param date
	 * @return
	 */
	public static LocalDateTime transform(Date date) {
		Instant instant = date.toInstant();
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

	/**
	 * 转换日期类型
	 *
	 * @param localDateTime
	 * @return
	 */
	public static Date transform(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}

	/**
	 * 日期和时间，格式化模板
	 */
	public enum Pattern {
		/**
		 * 秒级别 默认时间
		 */
		FORMAT_TIME("yyyy-MM-dd HH:mm:ss"),
		/**
		 * 毫秒级别
		 */
		FORMAT_TIME_MSELVES("yyyy-MM-dd HH:mm:ss.SSS"),
		/**
		 * 分钟级别
		 */
		FORMAT_TIME_MIN("yyyy-MM-dd HH:mm"),
		/**
		 * 小时级别
		 */
		FORMAT_TIME_HOUR("yyyy-MM-dd HH"),
		/**
		 * 日级别 默认日期
		 */
		FORMAT_DATE("yyyy-MM-dd"),
		/**
		 * 月级别
		 */
		FORMAT_DATE_MONTH("yyyy-MM"),
		/**
		 * 年级别
		 */
		FORMAT_DATE_YEAR("yyyy"),
		/**
		 * 毫秒级别
		 */
		FORMAT_ABBR_TIME_MSELVES("yyyyMMddHHmmssSSS"),
		/**
		 * 秒级别 默认时间
		 */
		FORMAT_ABBR_TIME("yyyyMMddHHmmss"),
		/**
		 * 分钟级别
		 */
		FORMAT_ABBR_TIME_MIN("yyyyMMddHHmm"),
		/**
		 * 小时级别
		 */
		FORMAT_ABBR_TIME_HOUR("yyyyMMddHH"),
		/**
		 * 日级别 默认日期
		 */
		FORMAT_ABBR_DATE("yyyyMMdd"),
		/**
		 * 月级别
		 */
		FORMAT_ABBR_DATE_MONTH("yyyyMM"),
		;

		private String pattern;

		Pattern(String pattern) {
			this.pattern = pattern;
		}
	}
	private static void swap(Date beforeDate, Date afterDate){
		//交换
		Date temp = beforeDate;
		beforeDate = afterDate;
		afterDate = temp;
	}
}
