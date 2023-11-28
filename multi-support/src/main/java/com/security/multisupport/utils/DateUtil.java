package com.security.multisupport.utils;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */

import org.apache.commons.lang3.time.DateFormatUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author ThinkGem
 * @File DateUtil.java
 * @Date 2014-4-15
 * @Time 上午11:11
 * @Encoding UTF-8
 * @Description
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {
//    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String[] PARSE_PATTERNS = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


    /**
     * 得到当前日期对象 格式（yyyy-MM-dd）
     */
    public static Date getNowDate() {
        return parseDate(getDate());
    }

    /**
     * 得到当前时间 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static Date getNowDateTime() {
        return parseDate(getDateTime());
    }


    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期字符串
     *
     * @param date 时间
     * @return 格式化字符串
     */
    public static String formatT(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(date);
    }

//    /**
//     * 得到日期字符串
//     *
//     * @param date 时间
//     * @return 格式化字符串
//     */
//    public static Date parseFormatT(String date) {
//        final String format = "yyyy-MM-dd'T'HH:mm:ss";
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        Date parse = null;
//        try {
//            parse = sdf.parse(date);
//        } catch (ParseException ex) {
//            logger.error("字符串[{}]日期转换[{}]异常！", date, format, ex);
//        }
//        return parse;
//    }

    /**
     * 获取FRC1123 日期输出
     *
     * @param date 日期对象
     * @return 格式化后的日期对象
     */
//    public static String getFRC1123Date(Date date) {
//        final String formatStr = "EEE, dd MMM yyyy HH:mm:ss z";
//        String format = "";
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr, Locale.US);
//            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//            format = simpleDateFormat.format(date);
//        } catch (Exception ex) {
//            logger.error("获取FRC1123 日期输出异常 format:[{}] Date:[{}]", formatStr, date);
//        }
//        return format;
//
//    }

    /**
     * 获取FRC1123 日期输出
     *
     * @param dateLong long 类型时间对象
     * @return 格式化后的日期对象
     */
//    public static String getFRC1123Date(long dateLong) {
//        Date date = new Date(dateLong);
//        return getFRC1123Date(date);
//    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

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
            return parseDate(str.toString(), PARSE_PATTERNS);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : StringUtil.EMPTY) + hour + ":" + min + ":" + s + "." + String.format("%03d", sss);
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }


    /**
     * 获取当月的第一天
     *
     * @return
     */
    public static Date getCurrMonthFirst() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取当月的最后一天
     *
     * @return
     */
    public static Date getCurrMonthLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     *
     * @param year  年份
     * @param month 月份
     * @return 指定年月的天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     *
     * @param beginDate
     * @param endDate
     * @return List
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        if (beginDate.compareTo(endDate) == 0) {
            lDate.add(beginDate);
            return lDate;
        }
        // 把开始时间加入集合
        lDate.add(beginDate);
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        // 把结束时间加入集合
        lDate.add(endDate);
        return lDate;
    }

    /**
     * 天数 +N天
     *
     * @param date
     * @return
     */
    public static Date dateGoDay(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 加n天
        c.add(Calendar.DAY_OF_MONTH, n);
        Date tomorrow = c.getTime();
        return tomorrow;
    }

    /**
     * 天数 -n天
     *
     * @param date
     * @return
     */
    public static Date dateRmDay(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 今天-n天
        c.add(Calendar.DAY_OF_MONTH, -n);
        Date tomorrow = c.getTime();
        return tomorrow;
    }

    /**
     * 判断是否为同一天
     *
     * @param date1 日期对象1
     * @param date2 日期对象2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);

        if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }

        return false;
    }

    /**
     * 间隔天数（xx天xx时xx分xx秒）
     *
     * @param startTimeMillis 开始时间
     * @param endTimeMillis   结束时间
     * @return 间隔天数
     */
    public static String formatDateTime(long startTimeMillis, long endTimeMillis) {
        long durationTimeMillis = endTimeMillis - startTimeMillis;
        long day = durationTimeMillis / (24 * 60 * 60 * 1000);
        long hour = (durationTimeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((durationTimeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (durationTimeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return (day > 0 ? day + "天" : StringUtil.EMPTY) + hour + "时" + min + "分" + s + "秒";
    }

    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date getTodayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 999);
        return cal.getTime();
    }
}
