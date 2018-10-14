package com.atlp.jzfp.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.HOUR_OF_DAY;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-09-19 09:54
 * @Decription:
 */
public class DateTimeUtil extends DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
            "yyyyMMdd HH:mm:ss","yyyyMMdd HH:mm:ss"};

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * date转timestamp
     * @param date
     * @return
     */
    public static Timestamp date2Timestamp(Date date) {
        if (null == date) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * timestamp转date
     * @param date
     * @return
     */
    public static Date timestamp2Date(Timestamp date) {
        if (null == date) {
            return null;
        }
        return new Date(date.getTime());
    }

    /**
     * 计算两个日期之间的差
     * 00:00:00
     * @param minDate
     * @param maxDate
     * @return
     */
    public static int getDateBetween(Date minDate, Date maxDate) {
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(minDate);
        max.setTime(maxDate);

        //设置时间为0时
        max.set(HOUR_OF_DAY, 0);
        max.set(java.util.Calendar.MINUTE, 0);
        max.set(java.util.Calendar.SECOND, 0);
        min.set(HOUR_OF_DAY, 0);
        min.set(java.util.Calendar.MINUTE, 0);
        min.set(java.util.Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (max.getTime().getTime() / 1000) - (int) (min.getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }



}
