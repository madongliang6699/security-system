package com.security.multisupport.utils;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * 集合工具类
 *
 * @Author zeng
 * @DateTime 2020-08-14 14:00
 * @FileName CollectionUtil.java
 */
public class CollectionUtil {


    /**
     * 判读List是否为空
     *
     * @param list list对象
     * @return true:为空 false:不为空
     */
    public static boolean listIsEmpty(List<?> list) {
        if (list == null || list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判读List是否为空
     *
     * @param map list对象
     * @return true:为空 false:不为空
     */
    public static boolean mapIsEmpty(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判读List是否为空
     *
     * @param set list对象
     * @return true:为空 false:不为空
     */
    public static boolean setIsEmpty(Set<?> set) {
        if (set == null || set.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 是否不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return (collection != null && !collection.isEmpty());
    }

    /**
     * 是否不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return (map != null && !map.isEmpty());
    }

    /**
     * 列表去重
     * 唯一标识使用对象的equals函数进行比较
     *
     * @param list
     * @return
     */
    public static <T> List<T> uniqueList(List<T> list) {
        if (isNotEmpty(list)) {
            return list.stream().distinct().collect(Collectors.toList());
        } else {
            return list;
        }
    }

    /**
     * 对列表进行去重
     *
     * @param list 源数据集
     * @param predicate 如果两个数据相同，返回true
     * @param <T>       泛型声明
     * @return 去重之后的数据集
     */
    public static <T> List<T> uniqueList(List<T> list, BiPredicate<T, T> predicate) {
        if (isNotEmpty(list)) {
            List<T> result = new ArrayList<>();
            for (T value : list) {
                boolean existed = false;

                for (T inner : result) {
                    // 避免自己和自己比
                    if (value != inner && predicate.test(value, inner)) {
                        existed = true;
                        break;
                    }
                }
                if (!existed) {
                    result.add(value);
                }
            }
            return result;
        } else {
            return list;
        }
    }

}
