package com.security.study.特定知识点测试和总结.JSON数据解析;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Map;

public class JSONJieXiTest {

    public static void main(String[] args) {

        SerializeConfig config = new SerializeConfig();
        config.setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCase);
        //todo 测试 config 有哪些作用
        //config.

        UserBase240806 user = new UserBase240806();
        user.setUserName("小明");
        user.setUserAge(12);
        user.setAddress("杭州");

        String jsonString1 = JSON.toJSONString(user);
        System.out.println(jsonString1);

        String jsonString2 = JSON.toJSONString(user, config);
        System.out.println(jsonString2);



        Map<String, Object> baseInfoMap1 = JSON.parseObject(jsonString1, Map.class);
        Map<String, Object> baseInfoMap2 = JSON.parseObject(jsonString2, Map.class);

        System.out.println(baseInfoMap1);
        System.out.println(baseInfoMap2);



        JSON.toJSONString(jsonString1, SerializerFeature.PrettyFormat);
        /**
         * SerializerFeature 是 Fastjson 库中用于定义序列化行为的枚举类型。下面是一些常用的 SerializerFeature 枚举值及其说明：
         *
         * QuoteFieldNames - 输出 key 时是否使用双引号，默认为 true。
         *
         * UseSingleQuotes - 文本字段使用单引号而不是双引号，默认为 false。
         *
         * WriteMapNullValue - 是否输出值为 null 的字段，默认为 false。
         *
         * WriteNullListAsEmpty - 列表字段如果为 null 时，输出为 [] 而非 null，默认为 false。
         *
         * WriteNullStringAsEmpty - 字符类型字段如果为 null 时，输出为 "" 而非 null，默认为 false。
         *
         * WriteDateUseDateFormat - 日期字段如果为 null 时，输出为 "" 而非 null，默认为 false。
         *
         * PrettyFormat - 对输出的 json 格式进行缩进美化，默认为 false。
         *
         * SortField - 对字段进行排序，默认为 false。
         *
         * WriteEnumUsingToString - 枚举类型输出 name() 或者 ordinal 还是使用 toString() 方法，默认为 true。
         *
         * SkipTransientField - 如果是 transient 字段，则跳过该字段，默认为 true。
         *
         * 使用示例：
         */



    }

}
