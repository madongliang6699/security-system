package com.security.multisupport.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {


    private static Map<String, String[]> getParameterMap(String requestURL) {
        HashMap<String, String[]> tmpParameterMap = new HashMap<>(16);
        String tmpReg = "(?<=(\\?)|(\\&))(\\S+?\\=\\S+?)(?=(&)|($))";
        Pattern pattern = Pattern.compile(tmpReg);

        String tmpKeyReg = "(?<=(^)|(&))(\\S+?)(?=\\=)";
        String tmpValueReg = "(?<=\\=)(\\S+?)(?=(&)|($))";
        Matcher matcher = pattern.matcher(requestURL);
        while (matcher.find()) {
            String tmpKey = regSubstr(tmpKeyReg, matcher.group());
            String tmpValue = regSubstr(tmpValueReg, matcher.group());
            tmpParameterMap.put(tmpKey, new String[]{tmpValue});
        }
        return tmpParameterMap;
    }


    /**
     * 正则表达式获取子串
     *
     * @param regStr 正则表达式
     * @param text   待匹配字符串
     * @return 匹配到的第一个字符串
     */
    public static String regSubstr(String regStr, String text) {
        if(StringUtil.isEmpty(regStr)){
            return StringUtil.EMPTY;
        }
        Pattern pattern = Pattern.compile(regStr);
        return regSubstr(pattern, text);
    }

    /**
     * 正则表达式获取子串
     *
     * @param pattern 正则表达式匹配器
     * @param text    待匹配字符串
     * @return 匹配到的第一个字符串
     */
    public static String regSubstr(Pattern pattern, String text) {
        if(pattern == null || StringUtil.isEmpty(text)){
            return StringUtil.EMPTY;
        }
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return StringUtil.EMPTY;
    }

    /**
     * 获取正则表达式匹配次数
     *
     * @param regStr 正则表达式
     * @param text   待匹配字符串
     * @return 匹配次数
     */
    public static int regMatcherCount(String regStr, String text) {
        Pattern pattern = Pattern.compile(regStr);
        return regMatcherCount(pattern, text);
    }

    /**
     * 获取正则表达式匹配次数
     *
     * @param pattern 正则表达式匹配器
     * @param text    待匹配字符串
     * @return 匹配次数
     */
    public static int regMatcherCount(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        int tmpCount = 0;
        while (matcher.find()) {
            tmpCount++;
        }
        return tmpCount;
    }

    /**
     * 获取正则表达式匹配次数
     *
     * @param patternStr 正则表达式字符串
     * @param text       待匹配字符串
     * @return 匹配次数
     */
    public static boolean regMatched(String patternStr, String text) {
        return regMatched(Pattern.compile(patternStr), text);
    }


    /**
     * 获取正则表达式匹配次数
     *
     * @param pattern 正则表达式匹配器
     * @param text    待匹配字符串
     * @return 匹配次数
     */
    public static boolean regMatched(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 获取正则表达式匹配次数
     *
     * @param patternStr 正则表达式字符串
     * @param text       待匹配字符串
     * @return 匹配次数
     */
    public static String regReplace(String patternStr, String text,String targetStr) {
        return regReplace(Pattern.compile(patternStr), text,targetStr);
    }


    /**
     * 获取正则表达式匹配次数
     *
     * @param pattern 正则表达式匹配器
     * @param text    待匹配字符串
     * @return 匹配次数
     */
    public static String regReplace(Pattern pattern, String text,String targetStr) {
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(targetStr);
    }

}
