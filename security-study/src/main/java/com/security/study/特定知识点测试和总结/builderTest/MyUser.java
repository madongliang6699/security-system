package com.security.study.特定知识点测试和总结.builderTest;

/**
 * @author A
 */
public class MyUser {
    
    private String name;
    private String address;
    private Integer age;
    private Double money;
    
    
    
    
    //public MyUser builder(){
    //    this.
    //}
    
    
    {
        MyUser address1 = new MyUser()
                .name("asd")
                .address("asd");
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public MyUser name(String name) {
        this.name = name;
        return this;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public MyUser address(String address) {
        this.address = address;
        return this;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Double getMoney() {
        return money;
    }
    
    public void setMoney(Double money) {
        this.money = money;
    }
    
    
    
    
}
