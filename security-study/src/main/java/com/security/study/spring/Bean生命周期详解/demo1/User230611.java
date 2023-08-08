package com.security.study.spring.Bean生命周期详解.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User230611 {
    
    private String name;
    private Car230611 car;
    
    @Autowired
    private Car230611 car230611;
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Car230611 getCar() {
        return car;
    }
    
    public void setCar(Car230611 car) {
        this.car = car;
    }
    
    public Car230611 getCar230611() {
        return car230611;
    }
    
    public void setCar230611(Car230611 car230611) {
        this.car230611 = car230611;
    }
    
    
    @Override
    public String toString() {
        return "User230611{" +
                "name='" + name + '\'' +
                ", car=" + car +
                ", car230611=" + car230611 +
                '}';
    }
}
