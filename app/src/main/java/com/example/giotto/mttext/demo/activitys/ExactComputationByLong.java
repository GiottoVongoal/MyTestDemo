package com.example.giotto.mttext.demo.activitys;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author yly
 * @Data 2018/6/28 10:19
 * @PackageName com.example.giotto.mttext.demo.activitys
 * @Description  
 */
public class ExactComputationByLong {
    /**
     * +
     */
    public static String add(String strA, String strB) {
        BigDecimal b1 = new BigDecimal(strA);
        BigDecimal b2 = new BigDecimal(strB);
        long value = b1.add(b2).longValue();
        String result = String.valueOf(value);
        return result;
    }

    /**
     * -
     */
    public static String subtract(String strA, String strB) {
        BigDecimal b1 = new BigDecimal(strA);
        BigDecimal b2 = new BigDecimal(strB);
        long value = b1.subtract(b2).longValue();
        String result = String.valueOf(value);
        return result;
    }

    /**
     * *
     */
    public static String multiply(String strA, String strB) {
        BigDecimal b1 = new BigDecimal(strA);
        BigDecimal b2 = new BigDecimal(strB);
        long value = b1.multiply(b2).longValue();
        String result = String.valueOf(value);
        return result;
    }

    /**
     * /
     */
    public static String divide(String strA, String strB) {
        if (strB.equals("0")) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(strA);
        BigDecimal b2 = new BigDecimal(strB);
        float value = b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).floatValue();
        DecimalFormat df = new DecimalFormat("0.00%");
        String result = df.format(value);
        return result.substring(0, result.length() - 1);
    }
}