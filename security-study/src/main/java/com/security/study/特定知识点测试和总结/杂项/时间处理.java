package com.security.study.特定知识点测试和总结.杂项;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class 时间处理 {

    public static void main(String[] args) throws ParseException {
        //System.out.println(null == 1);
        System.out.println(null == null);
        System.out.println(null == new 时间处理());
        System.out.println(null + "");

        String format = "HH:mm:ss";
        Calendar calendar = Calendar.getInstance();
//获取当前时间
//        Date nowTime = new SimpleDateFormat(format).parse(Tools.getTimeString(calendar, format));
        Date nowTime = new SimpleDateFormat(format).parse("17:20:00");
//范围开始时间
        Date startTime = new SimpleDateFormat(format).parse("17:30:00");
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(nowTime); // 将时分秒,毫秒域清零
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Date time = cal1.getTime();

//范围结束时间
        Date endTime = new SimpleDateFormat(format).parse("23:59:59");
        System.out.println();

    }

}
