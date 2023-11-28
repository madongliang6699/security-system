package com.security.multisupport.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
//import org.slf4j.//logger;
//import org.slf4j.//loggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * Json支持工具类
 *
 * @author zeng
 * @File JsonUtil.java
 * @Date 2018-12-06
 * @Time 上午11:11
 * @Encoding UTF-8
 * @Description
 */
public class JsonUtil {
//    private static final //logger //logger = //loggerFactory.get//logger(JsonUtil.class);
    // private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    private JsonUtil() {

    }

    /**
     * Pojo对象转换为Json字符串 (转换错误不抛出错误)
     *
     * @param obj 待转换Pojo对象
     * @return 转换完毕后的Json字符串
     */
    public static String obj2jsonStr(Object obj) {
        return pojo2Str(obj);
    }

    /**
     * Json字符串转换为Pojo对象
     *
     * @param jsonStr Json字符串
     * @param clazz   目标Pojo对象类型
     * @param <T>     泛型Class类型
     * @return 转换完毕Pojo对象
     * @throws Exception
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        return str2Pojo(jsonStr, clazz);
    }

    /**
     * Json字符串转换为Map对象 Map<String, Object>
     *
     * @param jsonStr Json字符串
     * @return
     * @throws Exception
     */
    public static <T, K> Map<T, K> json2Map(String jsonStr, Class<T> key, Class<K> entity) {
        return JSON.parseObject(jsonStr, new TypeReference<Map<T, K>>(key, entity) {
        });
    }


    /**
     * Json字符串转换为List对象 List<T>
     *
     * @param jsonArrayStr Json字符串
     * @param clazz        目标Pojo对象类型
     * @param <T>          泛型Class类型
     * @return 转换完毕Pojo的List对象
     * @throws Exception
     */
    public static <T> List<T> json2List(String jsonArrayStr, Class<T> clazz) {
        List<T> list = JSON.parseArray(jsonArrayStr, clazz);
        return list;
    }


    /**
     * 切割JSON字符串转换为HashMap
     *
     * @param s JSON字符串
     * @return HashMap对象
     * @throws Exception
     */
    public static HashMap<String, String> splitJson(String s) throws Exception {
        if (StringUtil.isEmpty(s)) {
            return new HashMap<String, String>(0);
        }
        s = s.replace("\n", StringUtil.EMPTY).replace("\t", StringUtil.EMPTY);
        // 允许出现特殊字符和转义符
        HashMap<String, String> rtn = new HashMap<String, String>(0);
        HashMap<String, String> jobj = null;
        try {
            jobj = JsonUtil.str2Pojo(s, HashMap.class);
            for (Map.Entry<String, String> entrySet : jobj.entrySet()) {
                String key = entrySet.getKey();
                Object value = entrySet.getValue();
                if (String.class.equals(value.getClass())) {
                    rtn.put(key, (String) value);
                } else {
                    rtn.put(key, JsonUtil.obj2jsonStr(value));
                }
            }
        } catch (Exception ex) {
            throw new Exception("Json格式化错误: " + s);
        }
        return rtn;
    }


    /**
     * Json字符串转换成Pojo对象
     *
     * @param jsonStr Json字符串
     * @param clazz   pojo类型
     * @param <T>     泛型Class类型
     * @return 转换完毕Pojo的List对象
     */
    public static <T> T str2Pojo(String jsonStr, Class<T> clazz) {
        if (JSONObject.class.equals(clazz)) {
            return (T) JSONObject.parseObject(jsonStr);
        }
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * Pojo对象转换成JSON字符串
     *
     * @param object pojo对象
     * @return Json字符串
     */
    public static String pojo2Str(Object object) {
        if (object == null) {
            return "";
        }
        if (String.class.equals(object.getClass())) {
            return (String) object;
        }
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }


    /**
     * 字符串数组转换成Pojo对象列表
     *
     * @param arrStr 字符串对象集合
     * @return Pojo对象列表
     */
    public static <T> List<T> arrStr2ArrPojo(Collection<String> arrStr, Class<T> clazz) {
        if (arrStr == null) {
            return null;
        }
        List<T> tmpPojoList = new ArrayList<>(arrStr.size());
        for (String tmp : arrStr) {
            tmpPojoList.add(str2Pojo(tmp, clazz));
        }
        return tmpPojoList;
    }

    /**
     * 安全的ob转为List
     *
     * @param obj 待转换对象
     * @param <T> 目录类型
     * @return list<T>
     */
    public static <T> List<T> objToList(Object obj, Class<T> tClass) {
        if (obj == null || tClass == null) {
            //logger.warn("obj || tClass is null");
            return null;
        }
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            if (CollectionUtils.isEmpty(list)) {
                return new ArrayList<>();
            }
            List<T> result = new ArrayList<>();
            for (Object item : list) {
                if (tClass.isInstance(item)) {
                    T cast = tClass.cast(item);
                    result.add(cast);
                }
            }
            return result;
        } else {
            //logger.warn("obj is not List !");
            return null;
        }
    }


    /**
     * 安全的对象转目标类对象
     *
     * @param obj    待转换对象
     * @param tClass 目录类型
     * @param <T>    泛型声明
     * @return T
     */
    public static <T> T objToObj(Object obj, Class<T> tClass) {
        if (obj == null || tClass == null) {
            //logger.warn("obj || tClass is null");
            return null;
        }
        if (tClass.isInstance(obj)) {
            return tClass.cast(obj);
        }
        if (obj instanceof String) {
            return JsonUtil.str2Pojo(JsonUtil.pojo2Str(obj), tClass);
        }
        return null;
    }


    /**
     * Pojo对象转换为Json字符串 (漂亮格式)
     *
     * @param obj 待转换Pojo对象
     * @return 转换完毕后的Json字符串
     */
    public static String jsonPrettyFormat(Object obj) {
        if (obj == null) {
            return "";
        }
        if (String.class.equals(obj.getClass())) {
            return (String) obj;
        }
        return JSON.toJSONString(obj,SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect);
    }


}
