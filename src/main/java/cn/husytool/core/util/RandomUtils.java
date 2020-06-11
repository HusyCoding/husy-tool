package cn.husytool.core.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: 随机工具类
 * @author: hsy
 * @date; 2020/5/19
 */
public class RandomUtils {
	/**
	 * 获取随机数生成器对象<br>
	 * ThreadLocalRandom是JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。
	 *
	 * @return {@link ThreadLocalRandom}
	 * @since 3.1.2
	 */
	public static ThreadLocalRandom getRandom() {
		return ThreadLocalRandom.current();
	}


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
