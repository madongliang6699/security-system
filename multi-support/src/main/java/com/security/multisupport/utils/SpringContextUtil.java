package com.security.multisupport.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static Environment env;

    public SpringContextUtil() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
        env = applicationContext.getEnvironment();
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return StringUtils.isEmpty(name) ? null : getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return clazz == null ? null : getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return !StringUtils.isEmpty(name) && clazz != null ? getApplicationContext().getBean(name, clazz) : null;
    }

    public static String getConfig(String propName) {
        return !StringUtils.isEmpty(propName) && env != null ? env.getProperty(propName) : null;
    }
}
