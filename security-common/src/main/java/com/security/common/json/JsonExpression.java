package com.security.common.json;

/**
 * json表达式接口
 *
 * @author madongliang
 */
public interface JsonExpression {

    /**
     * 解释表达式
     *
     * @param context 上下文
     * @return 结果
     */
    Object interpret(JsonExpressionContext context);

}
