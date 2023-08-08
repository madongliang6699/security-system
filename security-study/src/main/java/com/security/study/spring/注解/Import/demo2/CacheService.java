package com.security.study.spring.注解.Import.demo2;

/**
 * 缓存service
 * 有两个实现类，一个是本地缓存LocalCacheService，一个是Redis缓存RedisCacheService
 */
public interface CacheService {

    void set(String key, String value);
    
    String get(String key);

}
