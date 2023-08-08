package com.security.study.spring.aop.JDK_CGLIB.CGLIB.otherDemo;

import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.objenesis.instantiator.ObjectInstantiator;

/**
 * Objenesis: 实例化对象的一种方式
 *
 *
 *
 * @author A
 */
public class ObjenesisTest {
    
    /**
     * 先来看一段代码，User类只有一个有参构造函数
     */
    public static class User {
        private String name;
        
        public User(String name) {
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + '}';
        }
    }
    
    
    public static void main(String[] args) {
        /**
         * 大家来思考一个问题：针对上面的User类, 如果不使用这个有参构造函数的情况下，如何创建这个对象？
         * 通过反射？大家可以试试，如果不使用有参构造函数，是无法创建对象的。
         * cglib中提供了一个接口：Objenesis，通过这个接口可以解决上面这种问题，它专门用来创建对象，即使你没有空的构造函数，都木有问题，
         * 它不使用构造方法创建Java对象，所以即使你有空的构造方法，也是不会执行的。
         * 用法比较简单：
         */
    
        Objenesis objenesis = new ObjenesisStd();
        
        User user = objenesis.newInstance(User.class);
        
        System.out.println(user);
        user.setName("你好");
        System.out.println(user);
    
        /**
         * 运行结果:
         * User{name='null'}
         * User{name='你好'}
         *
         *
         * 大家可以在User类中加一个默认构造函数，来验证一下上面的代码会不会调用默认构造函数:
         * public User() {
         *     System.out.println("默认构造函数");
         * }
         *实际上是不会调用默认构造函数的.
         */
    
    
        /**
         * 如果需要多次创建User对象，可以写成下面方式重复利用:
         */
        Objenesis objenesis1 = new ObjenesisStd();
        ObjectInstantiator<User> userObjectInstantiator = objenesis1.getInstantiatorOf(User.class);
        User user1 = userObjectInstantiator.newInstance();
        System.out.println(user1);
        User user2 = userObjectInstantiator.newInstance();
        System.out.println(user2);
        System.out.println(user1 == user2);
    
    
    
    }
    
    
    
    
    
    
}
