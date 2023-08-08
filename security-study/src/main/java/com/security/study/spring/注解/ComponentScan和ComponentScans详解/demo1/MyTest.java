package com.security.study.spring.注解.ComponentScan和ComponentScans详解.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    
    public static void main(String[] args) {
    
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig0509.class);
    
        OrgService0509 bean = context.getBean(OrgService0509.class);
        bean.aaa();
    
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName + "-->" + context.getBean(beanDefinitionName));
        }
    
    
        /**
         * 输出：
         * myConfig0509-->com.mdl.注解.ComponentScan和ComponentScans详解.demo1.MyConfig0509@3d3fcdb0
         * orgService0509-->com.mdl.注解.ComponentScan和ComponentScans详解.demo1.OrgService0509@641147d0
         * user0509-->User0509{name='xiaoming'}
         * userService0509-->com.mdl.注解.ComponentScan和ComponentScans详解.demo1.UserService0509@6e38921c
         *
         *
         *
         * 标注了@MyBean注解，被注册到容器了，但是OrgService0509上没有标注@MyBean啊，怎么也被注册到容器了？
         * 原因：OrgService0509上标注了@Compontent注解，而@CompontentScan注解中的useDefaultFilters默认是true，
         * 表示也会启用默认的过滤器，而默认的过滤器会将标注有@Component、@Repository、@Service、@Controller这几个注解的类也注册到容器中
         *
         * 如果我们只想将标注有@MyBean注解的bean注册到容器，需要将默认过滤器关闭，即：useDefaultFilters=false
         */
    
    }
    
}
