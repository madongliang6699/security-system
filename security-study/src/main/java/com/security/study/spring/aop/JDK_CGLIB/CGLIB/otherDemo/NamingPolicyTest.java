package com.security.study.spring.aop.JDK_CGLIB.CGLIB.otherDemo;


import org.springframework.cglib.core.DefaultNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

/**
 * cglib中的 NamingPolicy 接口
 * <p>
 * 接口NamingPolicy表示生成代理类的名字的策略，通过Enhancer.setNamingPolicy方法设置命名策略。
 * 默认的实现类：DefaultNamingPolicy， 具体cglib动态生成类的命名控制。
 * DefaultNamingPolicy中有个getTag方法。
 * DefaultNamingPolicy生成的代理类的类名命名规则：
 *    被代理class name + "$$" + 使用cglib处理的class name + "ByCGLIB" + "$$" + key的hashcode
 * <p>
 *
 *自定义NamingPolicy，通常会继承DefaultNamingPolicy来实现，spring中默认就提供了一个，如下:
 * <p>
 * public class SpringNamingPolicy extends DefaultNamingPolicy {
 *     public static final SpringNamingPolicy INSTANCE = new SpringNamingPolicy();
 *     @Override
 *     protected String getTag() {
 *         return "BySpringCGLIB";
 *     }
 * }
 *
 * @author A
 */
public class NamingPolicyTest {
    
    
    
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(NamingPolicyTest.class);
        enhancer.setCallback(NoOp.INSTANCE);
        
        
        //通过Enhancer.setNamingPolicy来设置代理类的命名策略
        enhancer.setNamingPolicy(new DefaultNamingPolicy() {
            @Override
            protected String getTag() {
                return "_哈哈_";
            }
        });
        
        
        Object proxy = enhancer.create();
        System.out.println(proxy.getClass());
    
    
        /**
         * 运行结果:
         * class com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.NamingPolicyTest$$Enhancer_哈哈_$$640ba68a
         */
    
    
    }
    
    
}
