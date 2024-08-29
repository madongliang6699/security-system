package com.security.study.hutool常用工具类;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

public class TimeIntervalTest240829 {

    public static void main(String[] args) {

        //Hutool通过封装TimeInterval实现计时器功能，即可以计算方法或过程执行的时间。

        TimeInterval timer = DateUtil.timer();

        //---------------------------------
        //-------这是执行过程
        //---------------------------------

        timer.interval();//花费毫秒数
        timer.intervalRestart();//返回花费时间，并重置开始时间
        timer.intervalMinute();//花费分钟数

        //也可以实现分组计时：
        final TimeInterval timer2 = new TimeInterval();

        // 分组1
        timer2.start("1");
        ThreadUtil.sleep(800);

        // 分组2
        timer2.start("2");
        ThreadUtil.sleep(900);

        Console.log("Timer 1 took {} ms", timer2.intervalMs("1"));
        Console.log("Timer 2 took {} ms", timer2.intervalMs("2"));


    }

}
