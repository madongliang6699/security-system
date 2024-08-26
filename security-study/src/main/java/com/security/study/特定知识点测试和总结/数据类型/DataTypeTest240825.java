package com.security.study.特定知识点测试和总结.数据类型;


import java.util.ArrayList;
import java.util.List;

public class DataTypeTest240825 {
    public static void main(String[] args) {

        // 使用基本数据类型 byte
        byte a = 100;
        System.out.println("Primitive byte: " + a);  // 输出: Primitive byte: 100

        // 使用包装类 Byte
        Byte b = new Byte(a); // 手动装箱
        System.out.println("Wrapper Byte: " + b);   // 输出: Wrapper Byte: 100

        // 自动装箱与拆箱
        Byte c = a; // 自动装箱
        byte d = c; // 自动拆箱
        System.out.println("Auto-boxed Byte: " + c); // 输出: Auto-boxed Byte: 100
        System.out.println("Unboxed byte: " + d);    // 输出: Unboxed byte: 100

        List<Byte> list = new ArrayList<>();

    }
}
