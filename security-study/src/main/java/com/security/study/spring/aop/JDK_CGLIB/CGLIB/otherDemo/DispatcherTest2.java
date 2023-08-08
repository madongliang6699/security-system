package com.security.study.spring.aop.JDK_CGLIB.CGLIB.otherDemo;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通过Dispathcer对类扩展一些接口
 *
 *下面有个UserService类，我们需要对这个类创建一个代理。
 * 代码中还定义了一个接口：IMethodInfo，用来统计被代理类的一些方法信息，有个实现类：DefaultMethodInfo。
 * 通过cglib创建一个代理类，父类为UserService，并且实现IMethodInfo接口，将接口IMethodInfo所有方法的转发给DefaultMethodInfo处理，代理类中的其他方法，转发给其父类UserService处理。
 * 这个代码相当于对UserService这个类进行了增强，使其具有了IMethodInfo接口中的功能。
 *
 */
public class DispatcherTest2 {
    
    
    public static class UserService {
        public void add() {
            System.out.println("新增用户");
        }
        public void update() {
            System.out.println("更新用户信息");
        }
    }
    
    
    
    //用来获取方法信息的接口
    public interface IMethodInfo {
        //获取方法数量
        int methodCount();
        //获取被代理的对象中方法名称列表
        List<String> methodNames();
    }
    
    
    
    //IMethodInfo的默认实现
    public static class DefaultMethodInfo implements IMethodInfo {
        private Class<?> targetClass;
        public DefaultMethodInfo(Class<?> targetClass) {
            this.targetClass = targetClass;
        }
        @Override
        public int methodCount() {
            return targetClass.getDeclaredMethods().length;
        }
        @Override
        public List<String> methodNames() {
            return Arrays.stream(targetClass.getDeclaredMethods()).
                    map(Method::getName).
                    collect(Collectors.toList());
        }
    }
    
    
    
    
    
    public static void main(String[] args) {
        Class<?> targetClass = UserService.class;
        
        Enhancer enhancer = new Enhancer();
        //设置代理的父类
        enhancer.setSuperclass(targetClass);
        //设置代理需要实现的接口列表
        enhancer.setInterfaces(new Class[]{IMethodInfo.class});

        //创建一个方法统计器
        IMethodInfo methodInfo = new DefaultMethodInfo(targetClass);
        
        
        //创建调用器列表，此处定义了2个，第1个用于处理UserService中所有的方法，第2个用来处理IMethodInfo接口中的方法
        Callback[] callbacks = {
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, objects);
                    }
                },
                new Dispatcher() {
                    @Override
                    public Object loadObject() throws Exception {
                        /**
                         * 用来处理代理对象中IMethodInfo接口中的所有方法
                         * 所以此处返回的为IMethodInfo类型的对象，
                         * 将由这个对象来处理代理对象中IMethodInfo接口中的所有方法
                         */
                        return methodInfo;
                    }
                }
        };
        
        enhancer.setCallbacks(callbacks);
        
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                //当方法在IMethodInfo中定义的时候，返回callbacks中的第二个元素
                return method.getDeclaringClass() == IMethodInfo.class ? 1 : 0;
            }
        });
        
        Object proxy = enhancer.create();
        
        //代理的父类是UserService
        UserService userService = (UserService) proxy;
        // 这里根据调用的方法,通过上面的[method.getDeclaringClass() == IMethodInfo.class ? 1 : 0;]这行代码,判断返回的是哪一个被代理对象.
        userService.add();
        
        //代理实现了IMethodInfo接口
        IMethodInfo mf = (IMethodInfo) proxy;
        System.out.println(mf.methodCount());
        System.out.println(mf.methodNames());
    
    
        /**
         * 上面这样,可以使得代理对象: 既有UserService类的功能,也有接口IMethodInfo的实现类DefaultMethodInfo的功能.
         */
    
    
    }
    
    
    
    
    
    
    
    
    
}
