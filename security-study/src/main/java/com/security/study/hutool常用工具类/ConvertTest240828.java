package com.security.study.hutool常用工具类;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import com.security.study.hutool常用工具类.model.User240828;
import com.security.study.hutool常用工具类.model.UserTo240828;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConvertTest240828 {


    public static void main(String[] args) {

        //转换为字符串
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);
        long[] b = {1, 2, 3, 4, 5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);

        // 转换为指定类型数组：
        String[] bb = {"1", "2", "3", "4"};
        Integer[] intArray = Convert.toIntArray(bb);
        long[] c = {1, 2, 3, 4, 5};
        Integer[] intArray2 = Convert.toIntArray(c);


        //转换为日期对象：
        String date = "2017-05-06";
        Date value = Convert.toDate(date);
        LocalDateTime valueL = Convert.toLocalDateTime(date);


        //转换为集合
        Object[] lsitStr = {"a", "你", "好", "", 1};
        List<?> list1 = Convert.convert(List.class, lsitStr);
        //从4.1.11开始可以这么用
        List<?> list2 = Convert.toList(lsitStr);


        //其它类型转换: 1标准类型,
        User240828 user240828 = new User240828();
        user240828.setName("山东");
        user240828.setAge(1212);

        User240828 user240828Children = new User240828();
        user240828Children.setName("山东青岛");
        user240828Children.setAge(13333);
        user240828.setChildren(user240828Children);


        UserTo240828 convert = Convert.convert(UserTo240828.class, user240828);//数字类型的age转成了String类型的
        System.out.println(convert);

        UserTo240828 userTo240828 = new UserTo240828();
        BeanUtil.copyProperties(user240828, userTo240828);//和上面的效果好像一样
        System.out.println(userTo240828);


        List<String> strings = ListUtil.of("1", "2", "3");
        Integer[] intArray1 = Convert.convert(Integer[].class, strings);
        for (int i : intArray1) {
            System.out.println(i);  // 输出: 1 2 3
        }



        //其它类型转换: 2:泛型类型
        String jsonStr = "{\"name\":\"Tom\",\"age\":25}";
        //JSONObject jsonObject = Convert.convert(JSONObject.class, jsonStr);
        //System.out.println(jsonObject);  // 输出: {"name":"Tom","age":25}


        //半角和全角转换
        String banjiao = "123456789";
        //结果为："１２３４５６７８９"
        String sbc = Convert.toSBC(banjiao);
        System.out.println(sbc);

        String quanjiao = "１２３４５６７８９";
        //结果为"123456789"
        String dbc = Convert.toDBC(quanjiao);
        System.out.println(dbc);


        //16进制（Hex）
        String a16 = "我是一个小小的可爱的字符串";
        //结果："e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2"
        String hex = Convert.toHex(a16, CharsetUtil.CHARSET_UTF_8);
        String hex1 = "e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2";

        //结果为："我是一个小小的可爱的字符串"
        //String raw = Convert.hexStrToStr(hex, CharsetUtil.CHARSET_UTF_8);
        //注意：在4.1.11之后hexStrToStr将改名为hexToStr
        String raw = Convert.hexToStr(hex1, CharsetUtil.CHARSET_UTF_8);
        byte[] bytes = Convert.hexToBytes(hex1);



        //Unicode和字符串转换
        //与16进制类似，Convert类同样可以在字符串和Unicode之间轻松转换：
        String auc = "我是一个小小的可爱的字符串";
        //结果为："\\u6211\\u662f\\u4e00\\u4e2a\\u5c0f\\u5c0f\\u7684\\u53ef\\u7231\\u7684\\u5b57\\u7b26\\u4e32"
        String unicode = Convert.strToUnicode(auc);
        System.out.println(unicode);
        //结果为："我是一个小小的可爱的字符串"
        String aucStr = Convert.unicodeToStr(unicode);
        System.out.println(aucStr);


        //编码转换
        //在接收表单的时候，我们常常被中文乱码所困扰，其实大多数原因是使用了不正确的编码方式解码了数据。于是Convert.convertCharset方法便派上用场了，它可以把乱码转为正确的编码方式：
        String luanma = "我不是乱码";
        //转换后result为乱码
        String result = Convert.convertCharset(luanma, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        System.out.println(result);
        String rawluanma = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        System.out.println(rawluanma);
        Assert.assertEquals(rawluanma, luanma);

        //时间单位转换
        long l = 4535345;
        //结果为：75
        long minutes = Convert.convertTime(l, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);


        //数字转换
        //数字转中文,数字转中文方法中，只保留两位小数
        String f1 = Convert.numberToChinese(10889.72356, false);// 一万零八百八十九点七二
        System.out.println(f1);
        String f12 = Convert.numberToChinese(12653.34, true);// 使用金额大写 // 壹万贰仟陆佰伍拾叁
        System.out.println(f12);
        int f13 = Convert.chineseToNumber("陆佰万一千零一十二");// 6001012
        System.out.println(f13);
        int f134 = Convert.chineseToNumber("六百万一千零一十二");// 6001012
        System.out.println(f134);
        int f1345 = Convert.chineseToNumber("六百万一千零十二");// 零十二的写法不行
        System.out.println(f1345);


        // 原始类和包装类转换,有的时候，我们需要将包装类和原始类相互转换（比如Integer.class 和 int.class），这时候我们可以：
        //去包装
        Class<?> wrapClass = Integer.class;
        Class<?> unWraped = Convert.unWrap(wrapClass);//结果为：int.class
        //包装
        Class<?> primitiveClass = long.class;
        Class<?> wraped = Convert.wrap(primitiveClass);//结果为：Long.class


    }


}
