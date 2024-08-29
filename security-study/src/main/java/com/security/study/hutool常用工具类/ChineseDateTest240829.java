package com.security.study.hutool常用工具类;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;

public class ChineseDateTest240829 {

    public static void main(String[] args) {

        /**
         * ChineseDate表示了农历的对象，构建此对象既可以使用公历的日期，也可以使用农历的日期。
         */
        //通过农历构建
        ChineseDate chineseDate = new ChineseDate(1991, 11, 17);
        //通过公历构建
        ChineseDate chineseDate2 = new ChineseDate(DateUtil.parseDate("1991-11-06 06:12:12"));


        // 一月
        chineseDate2.getChineseMonth();
        // 正月
        chineseDate2.getChineseMonthName();
        // 初一
        chineseDate2.getChineseDay();
        // 庚子
        chineseDate2.getCyclical();
        // 生肖：鼠
        chineseDate2.getChineseZodiac();
        // 传统节日（部分支持，逗号分隔）：春节
        chineseDate2.getFestivals();
        // 庚子鼠年 正月初一
        chineseDate2.toString();
        // 获取天干地支: 庚子年甲申月癸卯日
        String cyclicalYMD = chineseDate2.getCyclicalYMD();
        System.out.println(cyclicalYMD);


    }

}
