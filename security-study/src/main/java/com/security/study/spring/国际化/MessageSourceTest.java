package com.security.study.spring.国际化;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class MessageSourceTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig_0727.class);
        context.refresh();
        //未指定Locale，此时系统会取默认的locale对象，本地默认的值中文【中国】，即：zh_CN
        System.out.println(context.getMessage("name", null, null));
        System.out.println(context.getMessage("name", null, Locale.CHINA)); //CHINA对应：zh_CN
        System.out.println(context.getMessage("name", null, Locale.UK)); //UK对应en_GB
    }
}
