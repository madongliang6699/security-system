package com.security.study.特定知识点测试和总结.杂项;

public class 数字测试 {

    public static void main(String[] args) {


        Integer a = 10010;
        Integer c = 10010;
        int b = 10010;

        System.out.println(a == b);

        System.out.println(a == c);
        System.out.println(a.equals(c));

        Integer d = 127;
        Integer e = 127;
        System.out.println(d == e);
        Integer f = 128;
        Integer g = 128;
        System.out.println(f == g);


        Integer h = -128;
        Integer i = -128;
        System.out.println(h == i);

        Integer j = -129;
        Integer k = -129;
        System.out.println(j == k);
        System.out.println(j.equals(k));
        /**
         * 从上面的结果看,使用"=="比较大小, 如果是int类型参与的比较,都没问题, 但是如果两个都是Integer比较大小,就有上限127和下限-128的限制, 不在这个范围内的就要使用equals,
         * 因此,只要是两个Integer的比较就要使用equals.
         */

    }

}
