package com.security.study.spring.注解.Import.demo2;

/**
 * Redis缓存实现类
 */
public class RedisCacheService implements CacheService{
    @Override
    public void set(String key, String value) {
        System.out.println("RedisCacheService  setDate==>"+value);
    }
    
    @Override
    public String get(String key) {
        System.out.println("RedisCacheService   get value111");
        return "RedisCacheService   get value111";
    }
}
