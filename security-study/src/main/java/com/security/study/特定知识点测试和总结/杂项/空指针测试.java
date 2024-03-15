package com.security.study.特定知识点测试和总结.杂项;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class 空指针测试 {

    public static void main(String[] args) {

        List<String> strList = new ArrayList<>();

        //这里给集合中添加了一个元素, 但是是null, 这会给后续的集合操作带来意想不到的bug.
        strList.add(null);

        //例如: 这些测试可以看出, 集合的判断既不是空的, size也不是0
        System.out.println(strList);
        System.out.println(strList.size());
        System.out.println(CollectionUtils.isEmpty(strList));
        System.out.println(strList.isEmpty());

        //但是当遍历这个集合的元素操作的时候, 就会报空指针.
        strList.forEach(str -> {
            //if (str.startsWith("aa")) { //报空指针
            //    System.out.println(str);
            //}
        });

        strList.add("1");
        System.out.println(strList);

        //工作中, 应该把为空的元素去除掉, 防止空指针
        strList = strList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(strList);


        User0304 user = new User0304();
        //类似这样,工作当中的集合中就可能被添加进了null元素
        strList.add(user.getName());
        System.out.println(strList);

    }

}
