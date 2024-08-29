package com.security.study.hutool常用工具类;

import cn.hutool.core.lang.Validator;

public class ValidatorTest240829 {

    public static void main(String[] args) {

        /**
         * Validator方法很多,使用时候查找
         */

        boolean isEmail = Validator.isEmail("loolly@gmail.com");
        boolean b = Validator.hasChinese("ssss地方");

        //类似 断言, 抛出异常
        Validator.validateChinese("我是一段zhongwen", "内容中包含非中文");

        boolean matchRegex = Validator.isMatchRegex("需要验证字段的正则表达式", "被验证内容");

    }

}
