package com.security.study.spring.注解.Configration和Bean注解.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class MyTest {
    public static void main(String[] args) {
    
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean.class);
    
        System.out.println("==============================================================");
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
            String[] aliases = context.getAliases(beanName);
            System.out.println(String.format("bean名称:%s,别名:%s,bean对象:%s", beanName, Arrays.asList(aliases), context.getBean(beanName)));
        }
        /**
         * 输出：看主要的下面的信息：
         * configBean
         * bean名称:configBean,别名:[],bean对象:com.mdl.注解.Configration和Bean注解.demo1.ConfigBean$$EnhancerBySpringCGLIB$$3dfff6be@501edcf1
         * user1
         * bean名称:user1,别名:[],bean对象:com.mdl.注解.Configration和Bean注解.demo1.User@78b729e6
         * user2Bean
         * bean名称:user2Bean,别名:[],bean对象:com.mdl.注解.Configration和Bean注解.demo1.User@6b4a4e18
         * user3Bean
         * bean名称:user3Bean,别名:[user3BeanAlias2, user3BeanAlias1],bean对象:com.mdl.注解.Configration和Bean注解.demo1.User@27c86f2d
         *
         * 可以看出：
         * configBean的bean，说明被@Configuration修饰的类ConfigBean本身也作为bean注册到容器中了。
         *
         *
         *
         */
        
        
        
    }
}
