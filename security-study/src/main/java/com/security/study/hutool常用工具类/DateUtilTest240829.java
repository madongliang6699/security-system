package com.security.study.hutool常用工具类;

import cn.hutool.core.date.*;
import cn.hutool.core.lang.Console;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtilTest240829 {

    public static void main(String[] args) {


        /**
         * Date、long、Calendar之间的相互转换
         */
        //当前时间
        Date date = DateUtil.date();
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();


        DateTime dateTime = DateUtil.nextWeek();//下周今天的此刻,
        System.out.println(dateTime);

        /**
         * 字符串转日期
         *
         * DateUtil.parse方法会自动识别一些常用格式，包括：
         *
         * yyyy-MM-dd HH:mm:ss
         * yyyy/MM/dd HH:mm:ss
         * yyyy.MM.dd HH:mm:ss
         * yyyy年MM月dd日 HH时mm分ss秒
         * yyyy-MM-dd
         * yyyy/MM/dd
         * yyyy.MM.dd
         * HH:mm:ss
         * HH时mm分ss秒
         * yyyy-MM-dd HH:mm
         * yyyy-MM-dd HH:mm:ss.SSS
         * yyyyMMddHHmmss
         * yyyyMMddHHmmssSSS
         * yyyyMMdd
         * EEE, dd MMM yyyy HH:mm:ss z
         * EEE MMM dd HH:mm:ss zzz yyyy
         * yyyy-MM-dd'T'HH:mm:ss'Z'
         * yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
         * yyyy-MM-dd'T'HH:mm:ssZ
         * yyyy-MM-dd'T'HH:mm:ss.SSSZ
         */
        String dateStr = "2017-03-01";
        Date date22 = DateUtil.parse(dateStr);


        /**
         * 格式化日期输出
         */
        String dateStr2 = "2017-03-01";
        Date date1 = DateUtil.parse(dateStr2);

        //结果 2017/03/01
        String format = DateUtil.format(date1, "yyyy/MM/dd");
        //常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date1);
        //结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date1);
        //结果：00:00:00
        String formatTime = DateUtil.formatTime(date1);


        /**
         * 获取Date对象的某个部分
         */
        Date dated = DateUtil.date();
        //获得年的部分
        DateUtil.year(dated);
        //获得月份，从0开始计数
        DateUtil.month(dated);
        //获得月份枚举
        DateUtil.monthEnum(dated);
        //.....


        /**
         * 开始和结束时间
         */
        String dateStr3 = "2017-03-01 22:33:23";
        Date date4 = DateUtil.parse(dateStr3);

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date4);
        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date4);
        Date endOfDay222 = DateUtil.endOfMonth(date4);//月
        Date endOf222Day = DateUtil.endOfMinute(date4);//分钟

        /**
         * 日期时间偏移
         */
        String dateStr5 = "2017-03-01 22:33:23";
        Date date5 = DateUtil.parse(dateStr5);

        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date5, DateField.DAY_OF_MONTH, 2);
        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date5, 3);
        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date5, -3);

        //昨天
        DateUtil.yesterday();
        //明天
        DateUtil.tomorrow();
        //上周
        DateUtil.lastWeek();
        //下周
        DateUtil.nextWeek();
        //上个月
        DateUtil.lastMonth();
        //下个月
        DateUtil.nextMonth();
        //等


        /**
         * 日期时间差, 和 格式化时间差
         */
        String dateStr1 = "2017-03-01 22:33:23";
        Date date6 = DateUtil.parse(dateStr1);

        String dateStr6 = "2017-04-01 23:33:23";
        Date date7 = DateUtil.parse(dateStr6);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date6, date7, DateUnit.DAY);

        int i = DateUtil.ageOfNow(date7);
        int i2 = DateUtil.ageOfNow("1991-3-4 12:12:12");
        long l = DateUtil.betweenDay(new Date(), new Date(), true);
        //DateUtil.betweenYear()

        //有时候我们希望看到易读的时间差，比如XX天XX小时XX分XX秒，此时使用DateUtil.formatBetween方法：
        //Level.MINUTE表示精确到分
        String dateStr8 = "2024-08-27 23:33:23";
        Date date8 = DateUtil.parse(dateStr8);
        String formatBetween = DateUtil.formatBetween(date8.getTime(), BetweenFormatter.Level.MINUTE);//这个是从1970.1.1开始算的
        Console.log(formatBetween);
        String formatBetween2 = DateUtil.formatBetween(date8, new Date(), BetweenFormatter.Level.SECOND);//两个日期的时间差
        Console.log(formatBetween2);

        /**
         * 星座和属相
         */
        // "摩羯座"
        String zodiac = DateUtil.getZodiac(Month.JANUARY.getValue(), 19);
        // "狗"
        String chineseZodiac = DateUtil.getChineseZodiac(1994);

        /**
         * 时间范围
         */
        // 创建日期范围生成器
        DateTime start = DateUtil.parse("2021-01-31");
        DateTime end = DateUtil.parse("2021-03-31");
        DateRange range = DateUtil.range(start, end, DateField.MONTH);

        // 简单使用
        // 开始时间
        DateRange startRange = DateUtil.range(DateUtil.parse("2017-01-01"), DateUtil.parse("2017-01-31"), DateField.DAY_OF_YEAR);
        // 结束时间
        DateRange endRange = DateUtil.range(DateUtil.parse("2017-01-31"), DateUtil.parse("2017-02-02"), DateField.DAY_OF_YEAR);
        // 交集 返回 [2017-01-31 00:00:00]
        List<DateTime> dateTimes = DateUtil.rangeContains(startRange, endRange);
        // 差集 返回 [2017-02-01 00:00:00, 2017-02-02 00:00:00]
        List<DateTime> dateNotTimes = DateUtil.rangeNotContains(startRange, endRange);
        // 区间 返回[2017-01-01 00:00:00, 2017-01-02 00:00:00, 2017-01-03 00:00:00]
        List<DateTime> rangeToList = DateUtil.rangeToList(DateUtil.parse("2017-01-01"), DateUtil.parse("2017-01-03"), DateField.DAY_OF_YEAR);


        /**
         * 其他
         */
        //年龄
        DateUtil.ageOfNow("1990-01-30");
        //是否闰年
        DateUtil.isLeapYear(2017);


        // hutool 还有 LocalDateTimeUtil 工具类
    }

}
