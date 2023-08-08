package com.security.study.spring.注解.Import.demo4.mapper;


import com.security.study.spring.注解.Import.demo4.MyMapper;

/**
 * 这里使用interface接口，是没法注册到容器中的，而mybatis可以通过接口注入其实现类，背后肯定做了特殊处理。
 */
@MyMapper
public interface OrgMapper {
    
    void addOrg();
}
