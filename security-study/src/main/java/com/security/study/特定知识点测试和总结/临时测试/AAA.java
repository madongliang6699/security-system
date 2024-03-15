package com.security.study.特定知识点测试和总结.临时测试;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

public class AAA {

    public static void main(String[] args) {

        Result result = getResult("R(233)= L(d) * S(55,2)");
        System.out.println(JSON.toJSONString(result));

    }



    private static Result getResult(String evaluationBasis) {
        if(StringUtils.isBlank(evaluationBasis)){
            return new Result(null, null, null);
        }
        int rIndex = evaluationBasis.indexOf("R");
        //如果lIndex是-1,说明没有LSR的公式的辨识依据,这种情况是因为后期有人修改的了当前风险点的辨识评估方法,从其他改成了LS,因此这里的evaluationBasis是老辨识方法得出的老数据,因此直接作为结果填充到R属性上
        if(rIndex == -1) {
            return new Result(null, null, evaluationBasis);
        }


        int starIndex = evaluationBasis.indexOf("(");
        int endIndex = evaluationBasis.indexOf(")");
        String r = evaluationBasis.substring(starIndex + 1, endIndex);

        evaluationBasis = evaluationBasis.substring(endIndex + 1);
        starIndex = evaluationBasis.indexOf("(");
        endIndex = evaluationBasis.indexOf(")");
        String l = evaluationBasis.substring(starIndex + 1, endIndex);
        l = l.substring(l.lastIndexOf(",") + 1);

        evaluationBasis = evaluationBasis.substring(endIndex + 1);
        starIndex = evaluationBasis.indexOf("(");
        endIndex = evaluationBasis.indexOf(")");
        String s = evaluationBasis.substring(starIndex + 1, endIndex);
        s = s.substring(s.lastIndexOf(",") + 1);


        return new Result(l, s, r);
    }

    private static class Result {

        public final String l;
        public final String s;
        public final String r;

        public Result(String l, String s, String r) {
            this.l = l;
            this.s = s;
            this.r = r;
        }

    }


}
