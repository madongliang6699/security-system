package com.security.study.myBatis.E_多种查询详解.demo1;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class MyTest {
    public static void main(String[] args) throws IOException {
    
    
    
        //指定mybatis全局配置文件
        String resource = "mybatis-config.xml";
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true);) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            OrderModel orderModel = mapper.getById1(1);
            log.info("{}", orderModel);
        }
    
        /**
         * 重点待遇xml文件中的：
         * <!--todo 下面这两行是重点，这个地方使用到了级联赋值，多级之间用.进行引用，此处我们只有一级，可以有很多级。-->
         *         <result column="user_id" property="userModel.id"/>
         *         <result column="name" property="userModel.name"/>
         */
    
    }
}
