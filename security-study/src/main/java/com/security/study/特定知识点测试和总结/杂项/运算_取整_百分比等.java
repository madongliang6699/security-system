package com.security.study.特定知识点测试和总结.杂项;

import java.text.NumberFormat;

public class 运算_取整_百分比等 {

    public static void main(String[] args) {


        /**
         * 所有介于25和26(不包括25)之间的数值，Math.cei()始终返回26，因为它执行的是向上舍入。
         * Math.round()方法只在数值大于等于25.5时返回26;否则返回25.
         * Math.floor()对所有介于25和26(不包括26)之间的数值都返回25.
         */

        Double totalPages = 23 / 10.0;
        System.out.println(totalPages);
        int ceil = (int) Math.ceil(totalPages);//小数向上取整
        System.out.println(ceil);
        int i = (int) Math.nextDown(totalPages);//小数向上取整
        System.out.println(i);
        int j = (int) Math.nextUp(totalPages);//小数向上取整
        System.out.println(j);



        double aDouble = 0.42851142857142855;
        System.out.println(aDouble);
        System.out.println(Math.round(aDouble * 100) * 0.01d);//四色五入保留两位小数
        System.out.println(Math.round(aDouble * 10000) * 0.01d);//百分比, 四色五入保留两位小数

        NumberFormat nt = NumberFormat.getPercentInstance();
        System.out.println(nt.format(aDouble));//四色五入百分比没有小数
        nt.setMinimumFractionDigits(2);
        System.out.println(nt.format(aDouble));//四色五入百分比两位小数


    }

}
