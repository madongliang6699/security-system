package com.security.study.hutool常用工具类;

import cn.hutool.core.collection.CollUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CollUtilTest240829 {

    public static void main(String[] args) {

        /**
         * CollUtil 这个工具主要增加了对数组、集合类的操作。
         */


        /**
         * join 方法
         */
        //这个方法的参数支持各种类型对象的集合，最后连接每个对象的时候调用其toString()方法。栗子如下：
        String[] col = new String[]{"a", "b", "c", "d", "e"};
        List<String> colList = CollUtil.newArrayList(col);
        String str = CollUtil.join(colList, "#"); //str -> a#b#c#d#e


        /**
         * sortPageAll方法
         * 这个方法其实是一个组合方法，功能是：将给定的多个集合放到一个列表（List）中，根据给定的Comparator对象排序，然后分页取数据。这个方法非常类似于数据库多表查询后排序分页，这个方法存在的意义也是在此。使用此方法，栗子如下：
         */
        //Integer比较器
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        //新建三个列表，CollUtil.newArrayList方法表示新建ArrayList并填充元素
        List<Integer> list1 = CollUtil.newArrayList(1, 2, 3);
        List<Integer> list2 = CollUtil.newArrayList(4, 5, 6);
        List<Integer> list3 = CollUtil.newArrayList(7, 8, 9);

        //参数表示把list1,list2,list3合并并按照从小到大排序后，取0~2个（包括第0个，不包括第2个），结果是[1,2]
        List<Integer> result = CollUtil.sortPageAll(0, 2, comparator, list1, list2, list3);
        System.out.println(result);     //输出 [1,2]

        /**
         * popPart方法
         * 这个方法传入一个栈对象，然后弹出指定数目的元素对象，弹出是指pop()方法，会从原栈中删掉
         *
         * append方法
         * 在给定数组里末尾加一个元素，其实List.add()也是这么实现的，这个方法存在的意义是只有少量的添加元素时使用，因为内部使用了System.arraycopy,每调用一次就要拷贝数组一次。这个方法也是为了在某些只能使用数组的情况下使用，省去了先要转成List，添加元素，再转成Array。
         */

        /**
         * zip方法
         *
         * 此方法也是来源于Python (opens new window)的一个语法糖，给定两个集合，然后两个集合中的元素一一对应，组成一个Map。此方法还有一个重载方法，可以传字符，然后给定分隔符，字符串会被split成列表。栗子：
         */
        Collection<String> keys = CollUtil.newArrayList("a", "b", "c", "d");
        Collection<Integer> values = CollUtil.newArrayList(1, 2, 3, 4);

        // {a=1,b=2,c=3,d=4}
        Map<String, Integer> map = CollUtil.zip(keys, values);
        System.out.println(map);

        Map<String, String> zipMap = CollUtil.zip("a,b,c,d", "1,2,3,4", ",");
        System.out.println(zipMap);

        Map<String, String> zipMap2 = CollUtil.zip("z,b,c,d", "5,2,3,4", ",", true);
        System.out.println(zipMap2);


    }

}
