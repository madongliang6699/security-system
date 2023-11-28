package com.security.multisupport.utils;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
//import org.apache.logging.log4j.message.FormattedMessage;
//import org.apache.logging.log4j.message.Message;
//import org.slf4j.//logger;
//import org.slf4j.//loggerFactory;
import org.springframework.util.Assert;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author zeng
 * @FileName StringUtil.java
 * @Date 2013-05-22
 * @Time 上午11:11
 * @Encoding UTF-8
 * @Description
 */
public class StringUtil extends StringUtils {

//    private static final //logger //logger = //loggerFactory.get//logger(StringUtil.class);
    private static final char SEPARATOR = '_';
    private static final char KEBAB_SEPARATOR = '-';
    public static final String CHARSET_NAME = "UTF-8";
    private static final Pattern P = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
    public static final String EMPTY = "";
    //    public static final String DIGITAL_PAT_STR = "^[-]?\\d+$";
    public static final String DIGITAL_PAT_STR = "^[-]?\\d+((\\.\\d+E-?\\d+)?|(\\.\\d+))$";
    public static final Pattern DIGITAL_PAT = Pattern.compile(DIGITAL_PAT_STR);
    //    public static final String FLOAT_PAT_STR = "^[-]?\\d+\\.?\\d*$";
    public static final String FLOAT_PAT_STR = "^[-]?\\d+((\\.\\d+E-?\\d+)?|(\\.\\d+))$";
    public static final Pattern FLOAT_PAT = Pattern.compile(FLOAT_PAT_STR);


    private static final ScriptEngineManager manager = new ScriptEngineManager();

    public static final Map<String, Class<?>> BASE_TYPE_MAP = new HashMap<String, Class<?>>() {{
        this.put(Boolean.class.getName(), Boolean.class);
        this.put(Byte.class.getName(), Byte.class);
        this.put(Short.class.getName(), Short.class);
        this.put(Integer.class.getName(), Integer.class);
        this.put(Long.class.getName(), Long.class);
        this.put(Float.class.getName(), Float.class);
        this.put(Double.class.getName(), Double.class);
        this.put(Date.class.getName(), Date.class);
        this.put(String.class.getName(), String.class);
    }};


    /**
     * 转换为字节数组
     *
     * @param str 待转换字符串
     * @return 转换后字节数组
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为字节数组
     *
     * @param bytes 待转换的字节数组
     * @return 转换后的字符串
     */
    public static String byteToString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return StringUtil.EMPTY;
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        return m.replaceAll(StringUtil.EMPTY);
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param html Html文本内容
     * @return 替换以后Html文本内容
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return StringUtil.EMPTY;
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }


    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return StringUtil.EMPTY;
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            //logger.error("[{}]长度[{}] 未支持的字符集格式", str, length, e);
        }
        return StringUtil.EMPTY;
    }

    public static String abbr2(String param, int length) {
        if (param == null) {
            return StringUtil.EMPTY;
        }
        StringBuffer result = new StringBuffer();
        int n = 0;
        char temp;
        // 是不是HTML代码
        boolean isCode = false;
        // 是不是HTML特殊字符,如&nbsp;
        boolean isHTML = false;
        for (int i = 0; i < param.length(); i++) {
            temp = param.charAt(i);
            if (temp == '<') {
                isCode = true;
            } else if (temp == '&') {
                isHTML = true;
            } else if (temp == '>' && isCode) {
                n = n - 1;
                isCode = false;
            } else if (temp == ';' && isHTML) {
                isHTML = false;
            }
            try {
                if (!isCode && !isHTML) {
                    n += String.valueOf(temp).getBytes("GBK").length;
                }
            } catch (UnsupportedEncodingException e) {
                //logger.error("[{}]长度[{}] 未支持的字符集格式", param, length, e);
            }

            if (n <= length - 3) {
                result.append(temp);
            } else {
                result.append("...");
                break;
            }
        }
        // 取出截取字符串中的HTML标记
        String tempResult = result.toString().replaceAll("(>)[^<>]*(<?)",
                "$1$2");
        // 去掉不需要结素标记的HTML标记
        tempResult = tempResult
                .replaceAll(
                        "</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
                        StringUtil.EMPTY);
        // 去掉成对的HTML标记
        tempResult = tempResult.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
                "$2");
        // 用正则表达式取出标记

        Matcher m = P.matcher(tempResult);
        List<String> endHTML = new ArrayList<String>(length);
        while (m.find()) {
            endHTML.add(m.group(1));
        }
        // 补全不成对的HTML标记
        for (int i = endHTML.size() - 1; i >= 0; i--) {
            result.append("</");
            result.append(endHTML.get(i));
            result.append(">");
        }
        return result.toString();
    }


    public static Object toDigital(String str, Class<?> targetClass) {
        if (StringUtil.isEmpty(str) || "null".equals(str) || targetClass == null) {
            return null;
        }
        switch (targetClass.getSimpleName().toLowerCase()) {
            case "byte":
                return toByte(str);
            case "short":
                return toShort(str);
            case "integer":
                return toInteger(str);
            case "long":
                return toLong(str);
            case "float":
                return toFloat(str);
            case "double":
                return toDouble(str);
            case "boolean":
                return toBoolean(str);
            case "date":
                return new Date(toLong(str));
        }
        return null;
    }


    /**
     * 转换为Float类型
     */
    public static Boolean toBoolean(Object val) {
        if (val == null) {
            return null;
        }
        try {
            return Boolean.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        if (val == null || !RegularUtil.regMatched(FLOAT_PAT, val.toString())) {
            return null;
        }
        try {
            return Float.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null || !RegularUtil.regMatched(FLOAT_PAT, val.toString())) {
            return null;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转换为Integer类型
     */
    public static Byte toByte(Object val) {
        if (val == null || !RegularUtil.regMatched(DIGITAL_PAT, val.toString())) {
            return null;
        }
        try {
            return Byte.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return null;

        }
    }

    /**
     * 转换为Integer类型
     */
    public static Short toShort(Object val) {
        if (val == null || !RegularUtil.regMatched(DIGITAL_PAT, val.toString())) {
            return null;
        }
        try {
            return Short.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        if (val == null || !RegularUtil.regMatched(DIGITAL_PAT, val.toString())) {
            return null;
        }
        try {
            return Integer.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        if (val == null || !RegularUtil.regMatched(DIGITAL_PAT, val.toString())) {
            return null;
        }
        try {
            return Long.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转换为Long类型
     */
    public static String toString(Object val) {
        if (val == null) {
            return null;
        }
        try {
            return val.toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 驼峰命名法工具
     *
     * toCamelCase(" hello_world ") == "helloWorld"
     * kebabToCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * kebabToCapitalizeCamelCase("hello-world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * toKebabScoreCase("helloWorld") = "hello-world"
     *
     * @return
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == SEPARATOR || c == KEBAB_SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * toCamelCase(" hello_world ") == "helloWorld"
     * kebabToCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * kebabToCapitalizeCamelCase("hello-world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * toKebabScoreCase("helloWorld") = "hello-world"
     *
     * @return
     */
    public static String kebabToCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == KEBAB_SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * toCamelCase(" hello_world ") == "helloWorld"
     * kebabToCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * kebabToCapitalizeCamelCase("hello-world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * toKebabScoreCase("helloWorld") = "hello-world"
     *
     * @return
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * toCamelCase(" hello_world ") == "helloWorld"
     * kebabToCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * kebabToCapitalizeCamelCase("hello-world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * toKebabScoreCase("helloWorld") = "hello-world"
     *
     * @return
     */
    public static String kebabToCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = kebabToCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     * toCamelCase(" hello_world ") == "helloWorld"
     * kebabToCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * kebabToCapitalizeCamelCase("hello-world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * toKebabScoreCase("helloWorld") = "hello-world"
     *
     * @return
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }
    /**
     * 驼峰命名法工具
     * toCamelCase(" hello_world ") == "helloWorld"
     * kebabToCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * kebabToCapitalizeCamelCase("hello-world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     * toKebabScoreCase("helloWorld") = "hello-world"
     *
     * @return
     */
    public static String toKebabScoreCase(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUpperCase = true;
            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }
            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(KEBAB_SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }


    /**
     * trim最左边的特定字符串
     *
     * @param value
     * @param trimStr
     * @return
     */
    public static String trimLeft(String value, String trimStr) {
        Assert.notNull(value, "value is null");
        Assert.notNull(trimStr, "trimStr is null");
        if (value.startsWith(trimStr)) {
            return value.substring(trimStr.length());
        }

        return value;
    }

    /**
     * trim最右边的特定字符串
     *
     * @param value
     * @param trimStr
     * @return
     */
    public static String trimRight(String value, String trimStr) {
        Assert.notNull(value, "value is null");
        Assert.notNull(trimStr, "trimStr is null");
        if (value.endsWith(trimStr)) {
            return value.substring(0, value.length() - trimStr.length());
        }
        return value;
    }


    /**
     * 去除字符串中的全部trimStr
     * case  "xxxxABCxxx"  "ABxxxxx" "xxxxAB"
     *
     * @param value
     * @param trimStr
     * @return
     */
    public static String trimAll(String value, String trimStr) {
        return value.replace(trimStr, "");
    }


    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     *
     * @param objectString 对象串
     *                     例如：row.user.id
     *                     返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }


    /**
     * 根据Unicode编码完美的判断中文汉字和符号
     *
     * @param c
     * @return
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    /**
     * 完整的判断中文汉字和符号
     *
     * @param strName
     * @return
     */
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    public static int getStringByteCount(String str) {
        int tmpCount = 0;
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                tmpCount += 3;
            } else {
                tmpCount++;
            }
        }
        return tmpCount;
    }


    /**
     * Check that the given {@code CharSequence} is neither {@code null} nor
     * of length 0.
     * <P>Note: this method returns {@code true} for a {@code CharSequence}
     * that purely consists of whitespace.
     * <P><pre class="code">
     * StringUtil.hasLength(null) = false
     * StringUtil.hasLength(StringUtil.EMPTY) = false
     * StringUtil.hasLength(" ") = true
     * StringUtil.hasLength("Hello") = true
     * </pre>
     *
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null} and has length
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check that the given {@code String} is neither {@code null} nor of length 0.
     * <P>Note: this method returns {@code true} for a {@code String} that
     * purely consists of whitespace.
     *
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not {@code null} and has length
     * @see #hasLength(CharSequence)
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }


    /**
     * 是否为基础数据类型
     *
     * @param typeClass 基础数据类型
     * @param <T>       泛型支持(基础数据类型)
     * @return true:是基础数据类型 false:不是基础数据类型
     */
    public static <T> boolean isBaseType(Class<T> typeClass) {
        if (typeClass == null) {
            return false;
        }
        return BASE_TYPE_MAP.containsKey(typeClass.getName());
    }


    /**
     * 从字符串转换至基础数据类型
     *
     * @param typeClass 基础数据类型
     * @param str       待转换数据
     * @param <T>       泛型支持(基础数据类型)
     * @return 基础数据类型对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T baseTypeConvert(Class<T> typeClass, String str) {
        T t = null;
        switch (typeClass.getTypeName()) {
            case "java.lang.Boolean":
                t = (T) toBoolean(str);
                break;
            case "java.lang.Byte":
                t = (T) toByte(str);
                break;
            case "java.lang.Short":
                t = (T) toShort(str);
                break;
            case "java.lang.Integer":
                t = (T) toInteger(str);
                break;
            case "java.lang.Long":
                t = (T) toLong(str);
                break;
            case "java.lang.String":
                t = (T) str;
                break;
            case "java.util.Date":
                t = (T) DateUtil.parseDate(str);
                break;
            case "java.lang.Float":
                t = (T) toFloat(str);
                break;
            case "java.lang.Double":
                t = (T) toDouble(str);
                break;
            default:
                break;
        }
        return t;
    }

    /**
     * 根据表达式计算Double值
     *
     * @param str 计算表达式
     * @return Double值
     */
    public static Double stringEvalDouble(String str) {
        if (isEmpty(str)) {
            return null;
        }
        ScriptEngine engine = manager.getEngineByName("js");
        Double eval = null;
        try {
            eval = toDouble(engine.eval(str).toString());
        } catch (ScriptException e) {
            //logger.error("表达式计算出错：{} ", str);
        }
        return eval;
    }

    /**
     * @param format "xxx{0}xxx{1}"
     * @param args   xxx,xxx
     * @return String
     */
    public static String format4Index(String format, Object... args) {
        return MessageFormat.format(format, args);
    }


    /**
     * @param format "xxx{}xxx{}"
     * @param args   xxx,xxx
     * @return String
     */
    public static String format(String format, Object... args) {
        return StringFormat.format(format, args);
    }

    /**
     * 格式化浮点型数据精度
     *
     * @param numStr    数值字符串
     * @param precision 数字精度
     * @return
     */
    public static String formatDoublePrecision(String numStr, int precision) {
        Double tmpNum = toDouble(numStr);
        if (tmpNum == null) {
            return EMPTY;
        }
        return formatDoublePrecision(tmpNum.doubleValue(), precision);
    }

    /**
     * 格式化浮点型数据精度
     *
     * @param num       数值
     * @param precision 数字精度
     * @return
     */
    public static String formatDoublePrecision(double num, int precision) {
        return String.format("%." + precision + "f", num);
    }


    /**
     * 格式化字符串
     * "abc{},{}" ,"12","34" ==> "abc12,34"
     *
     * @param messagePattern 字符串模板
     * @param arguments      字符串数据
     * @return
     */
//    public static String formatString(String messagePattern, Object... arguments) {
//        Message msg = new FormattedMessage(messagePattern, arguments);
//        return msg.getFormattedMessage();
//    }



    /**
     * 对字符串进行切分
     *
     * @param value
     * @param splitter
     * @return
     */
    public static List<String> splitStr(String value, String splitter) {
        Assert.notNull(value, "value is null");
        Assert.notNull(splitter, "splitter is null");

        List<String> list = new ArrayList<>();

        int start = 0;

        for (int i = 0; i < value.length(); i++) {

            int j = i;
            for (int ii = 0; ii < splitter.length(); ii++) {
                if (j >= value.length()) {
                    break;
                }
                if (value.charAt(j) != splitter.charAt(ii)) {
                    break;
                } else {
                    if (ii == splitter.length() - 1) {
                        //捕获到一个字符串
                        int end = j - splitter.length() + 1;
                        if (start < end) {
                            list.add(value.substring(start, end));
                        }
                        start = j + 1;
                        i = i + splitter.length() - 1;
                    }
                    ++j;
                }
            }
        }
        if (start > 0 && start < (value.length() - 1)) {
            list.add(value.substring(start));
        }
        return list;
    }
}
