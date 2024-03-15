package com.security.study.特定知识点测试和总结.正则;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试
 */
public class RegularTest {


    public static void main(String[] args) {
        HashMap<String, List<Double>> score = getScore("E(2)-F(45,2))");
        System.out.println(score);
    }


    /**
     * 将basi字符串转成分数值
     * @param evaluationBasis R(#)=?(#)*?(#,#)*?(#,#)
     * @return 分数数组
     */
    public static HashMap<String, List<Double>> getScore(String evaluationBasis){

        HashMap<String,List<Double>> res = new HashMap<>();
        List<String> basis = new ArrayList<>();

        String regEx="[A-Z+](\\([^)]*\\))";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(evaluationBasis);
        while(matcher.find()){
            String group = matcher.group();
            basis.add(group);
        }

        pattern = Pattern.compile("[0-9.]+");
        for (String str : basis) {
            Matcher matcher1 = pattern.matcher(str);
            List<Double> scoreList = new ArrayList<>();
            while(matcher1.find()){
                Double group = Double.valueOf(matcher1.group());
                scoreList.add(group);
            }
            res.put(String.valueOf(str.charAt(0)),scoreList);
        }
        return res;
    }


}
