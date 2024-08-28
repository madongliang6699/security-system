package com.security.study.hutool常用工具类.model;

/**
 * 周期类型
 */
public enum CycleType {

    NATURAL_TIME(1, "自然时间"),
    RUN_TIME(2, "运行时间"),

    ;

    //状态码
    private final Integer key;
    //状态名称
    private final String name;


    CycleType(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public static CycleType getByKey(Integer key) {
        for (CycleType element : CycleType.values()) {
            if (key.equals(element.getKey())) {
                return element;
            }
        }
        return null;
    }


}
