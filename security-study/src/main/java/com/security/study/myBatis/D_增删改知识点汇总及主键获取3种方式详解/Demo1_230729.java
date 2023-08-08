package com.security.study.myBatis.D_增删改知识点汇总及主键获取3种方式详解;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Demo1_230729 {
    public static void main(String[] args) {
         String jdbcDriver = "com.mysql.jdbc.Driver";
         String jdbcUrl = "jdbc:mysql://localhost:3306/javacode2018?characterEncoding=UTF-8";
         String jdbcUserName = "root";
         String jdbcPassword = "root123";
        
        
        // Connection connection = null;
        // PreparedStatement preparedStatement = null;
        // ResultSet generatedKeys = null;
        // try {
        //     UserModel_0729 userModel = UserModel_0729.builder().name("黎明").age(30).salary(50000D).sex(1).build();
        //     //执行jdbc插入数据操作
        //     Class.forName(jdbcDriver);
        //     connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
        //     //注意创建PreparedStatement的时候，使用prepareStatement方法的第二个参数需要指定Statement.RETURN_GENERATED_KEYS
        //     preparedStatement = connection.prepareStatement("INSERT INTO t_user (name,age,salary,sex) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        //     int parameterIndex = 1;
        //     preparedStatement.setString(parameterIndex++, userModel.getName());
        //     preparedStatement.setInt(parameterIndex++, userModel.getAge());
        //     preparedStatement.setDouble(parameterIndex++, userModel.getSalary());
        //     preparedStatement.setInt(parameterIndex++, userModel.getSex());
        //     int count = preparedStatement.executeUpdate();
        //     log.info("影响行数：{}", count);
        //     //获取自增值
        //     generatedKeys = preparedStatement.getGeneratedKeys();
        //     if (generatedKeys != null && generatedKeys.next()) {
        //         log.info("自增值为：{}", generatedKeys.getInt(1));
        //     }
        // } finally {
        //     if (generatedKeys != null && generatedKeys.isClosed()) {
        //         generatedKeys.close();
        //     }
        //     if (preparedStatement != null && preparedStatement.isClosed()) {
        //         preparedStatement.close();
        //     }
        //     if (connection != null && connection.isClosed()) {
        //         connection.close();
        //     }
        // }
        //
        
        
        
    }
}
