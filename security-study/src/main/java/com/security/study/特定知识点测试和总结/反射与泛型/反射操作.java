package com.security.study.特定知识点测试和总结.反射与泛型;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.List;

public class 反射操作 {

    public static void main(String[] args) {








    }


    /**
     * 使用带L S R字段的模板, 并加上前面几个字段
     */
    private <T> void setLSR(List<Class<T>> voLsrList) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        for (Class<T> voLsr : voLsrList) {
            T t = voLsr.newInstance();
            Field assessmentEvaluationBasis = voLsr.getDeclaredField("assessmentEvaluationBasis");
            assessmentEvaluationBasis.setAccessible(true);
            assessmentEvaluationBasis.get(t);

            //Result result = getResult(String.valueOf(assessmentEvaluationBasis));
            //voLsr.setL1(result.l);
            //voLsr.setS1(result.s);
            //voLsr.setR1(result.r);
            //result = getResult(voLsr.getEvaluationBasis());
            //voLsr.setL2(result.l);
            //voLsr.setS2(result.s);
            //voLsr.setR2(result.r);
            //result = getResult(voLsr.getEvaluationBasis2());
            //voLsr.setL3(result.l);
            //voLsr.setS3(result.s);
            //voLsr.setR3(result.r);
        }
    }


    @NotNull
    private static Result getResult(String evaluationBasis) {
        int lIndex = evaluationBasis.indexOf(")=L(");
        //如果lIndex是-1,说明没有LSR的公式的辨识依据,这种情况是因为后期有人修改的了当前风险点的辨识评估方法,从其他改成了LS,因此这里的evaluationBasis是老辨识方法得出的老数据,因此直接作为结果填充到R属性上
        if(lIndex == -1) {
            return new Result(null, null, evaluationBasis);
        }
        int sIndex = evaluationBasis.indexOf(") * S(");
        String l = evaluationBasis.substring(lIndex + 4, sIndex);
        String s = evaluationBasis.substring(sIndex + 6, evaluationBasis.lastIndexOf(")"));
        String r = evaluationBasis.substring(evaluationBasis.indexOf("(") + 1, lIndex);
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
