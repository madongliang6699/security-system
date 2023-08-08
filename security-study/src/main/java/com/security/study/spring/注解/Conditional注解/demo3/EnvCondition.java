package com.security.study.spring.注解.Conditional注解.demo3;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 案例：根据环境选择配置类
 * 平常我们做项目的时候，有开发环境、测试环境、线上环境，每个环境中有些信息是不一样的，比如数据库的配置信息，
 * 下面我们来模拟不同环境中使用不同的配置类来注册不同的bean。
 */
public class EnvCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    
        //当前需要使用的环境，可以在配置文件中配置，这里写死了
        EnvConditional.Env curEnv = EnvConditional.Env.PROD; //
        
        //获取使用条件的类上的EnvCondition注解中对应的环境
        EnvConditional.Env env = (EnvConditional.Env) metadata.getAllAnnotationAttributes(EnvConditional.class.getName()).get("value").get(0);
        
        //与需要的环境相匹配的，就返回true，对应的配置文件就会被解析注册。
        return env.equals(curEnv);
        
    }
}
