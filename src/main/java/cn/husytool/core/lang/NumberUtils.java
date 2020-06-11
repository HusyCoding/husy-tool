package cn.husytool.core.lang;

import java.math.BigDecimal;

/**
 * @description: 数值操作对象
 * @author: husy
 * @date 2020/5/20
 */
public class NumberUtils {
    public static boolean equals(Integer num1, Integer num2) {
        return num1 != null ? num1.equals(num2) : num2 == null;
    }

    /**
     * Add big decimal.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the big decimal
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2);
    }

    /**
     * Sub big decimal.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the big decimal
     */
    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2);
    }


    /**
     * Mul big decimal.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the big decimal
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2);
    }

    /**
     * Div big decimal.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the big decimal
     */
    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 以0 填充数字到指定长度
     * @param num 填充数字
     * @param len 字符串长度
     * @return 填充之后的数字字符串
     */
    public static String fillNumByZero(long num, int len) {
        StringBuffer sb = new StringBuffer();
        int flen = len - (num + "").length();
        for (int i = 0; i <flen; i++) {
            sb.append("0");
        }
        return sb.append(num).toString();
    }

    public static void main(String[] args) {
        System.out.println(fillNumByZero(102,6));
    }
}
