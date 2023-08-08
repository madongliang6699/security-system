package com.security.study.spring.注解.Import.demo2;

/**
 *本地缓存实现类
 */
public class LocalCacheService implements CacheService{
    @Override
    public void set(String key, String value) {
        System.out.println("LocalCacheService  setDate==>"+value);
    }
    
    @Override
    public String get(String key) {
        System.out.println("LocalCacheService   get value111");
        return "LocalCacheService   get value111";
    }
}
