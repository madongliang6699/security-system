package com.security.study.spring.注解.java注解预备知识.demo2;

import org.springframework.core.annotation.AnnotatedElementUtils;

public class MyTest {
    
    
    public static void main(String[] args) {
    
        //AnnotatedElementUtils是spring提供的一个查找注解的工具类
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseAnnotationTest1.class, Ann2.class));
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseAnnotationTest1.class, Ann1.class));
    
        /**
         * 输出：
         * @com.mdl.java注解预备知识.demo2.Ann2(value=bbb, ann1Value=aaa)
         * @com.mdl.java注解预备知识.demo2.Ann1(value=aaa)
         *
         *
         *
         * 可以看出，注解Ann1的属性值被改成aaa了，是通过注解Ann2的ann1Value属性改的，这就是@AliasFor注解的作用，可以间接的改其他注解的值。
         *
         * 这个相当于给某个注解指定别名，即将Ann2注解中ann1Value参数作为Ann1中value参数的别名，当给Ann2的ann1Value设置值的时候，
         * 就相当于给Ann1的value设置值，
         * 有个前提是@AliasFor注解的annotation参数指定的注解（@Ann1）需要使用在当前注解（@Ann2）上面。
         *
         *
         *
         */
    
    
    }
}
