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
}
