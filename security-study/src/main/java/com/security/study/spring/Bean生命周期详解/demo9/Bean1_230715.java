package com.security.study.spring.Bean生命周期详解.demo9;

import javax.annotation.PostConstruct;

public class Bean1_230715 {
    
    private String name = "xiaoming";
    
    private Integer age = 23;
    
    /**
     * 这里三个方法的执行顺序是不一定的，有空可以研究一下内部的源码实现，是怎么做先后调用的，难道是异步的吗，
     * 自己写类似BeanPostProcessor#postProcessBeforeInitialization实现的逻辑的时候，可以借鉴一下。
     * <p>
     * 其实BeanPostProcessor还有postProcessAfterInitialization方法等。是在bean周期的另一个阶段执行（Bean初始化后阶段）。
     */
    
    @PostConstruct
    public void aa() {
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        System.out.println("aa被执行。。。。。。。");
    }
    
    @PostConstruct
    public void cc() {
        System.out.println("cc被执行。。。。。。。");
    }
    
    
    @PostConstruct
    public void bb() {
        System.out.println("bb被执行。。。。。。。");
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
}
