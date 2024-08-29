package com.security.study.hutool常用工具类;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ListUtilTest240829 {

    public static void main(String[] args) {

        /**
         * List在集合中使用最为频繁，因此新版本的Hutool中针对List单独封装了工具方法。
         */



        /**
         * 获取满足指定规则所有的元素的位置
         *
         * */
        List<String> a = ListUtil.toLinkedList("1", "2", "3", "4", "3", "2", "1");
        // [1, 5]
        int[] indexArray = ListUtil.indexOfAll(a, "2"::equals);


    }

}
