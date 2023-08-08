package com.security.study.特定知识点测试和总结.user;

import java.util.Random;

/**
 * @author A
 */
public class My1 {
    public static void main(String[] a) throws Exception{
        System.out.println("enter............");


        for (int i = 0; i < 1000; i++) {
            Random r = new Random();
            double v = r.nextDouble();
            String s = i + "_" + v;
            System.out.println(s);
    
            Thread.sleep(1000);
        }

    }

}
