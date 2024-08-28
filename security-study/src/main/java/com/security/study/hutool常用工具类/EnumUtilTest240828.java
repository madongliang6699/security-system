package com.security.study.hutool常用工具类;

import cn.hutool.core.util.EnumUtil;
import com.security.study.hutool常用工具类.model.CycleType;

import java.util.List;
import java.util.Map;

public class EnumUtilTest240828 {

    public static void main(String[] args) {


        List<String> names = EnumUtil.getNames(CycleType.class);
        System.out.println(names);
        List<Object> names22 = EnumUtil.getFieldValues(CycleType.class, "name");
        System.out.println(names22);

        int ordinal = CycleType.RUN_TIME.ordinal();
        System.out.println(ordinal);

        CycleType testEnum = EnumUtil.getBy(CycleType::ordinal, 1);
        System.out.println(testEnum);
        String name = EnumUtil.getFieldBy(CycleType::getName, Enum::ordinal, 1);


        Map<String, Object> enumMap = EnumUtil.getNameFieldMap(CycleType.class, "key");

        //EnumUtil.
    }

}
