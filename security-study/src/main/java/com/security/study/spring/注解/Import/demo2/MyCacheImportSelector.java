package com.security.study.spring.注解.Import.demo2;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.text.MessageFormat;
import java.util.Map;

public class MyCacheImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    
        /**
         * 控制动态注入spring容器的bean的类型，主要的逻辑就在下面的方法里，同一个功能，通过不同的type参数，注入不同bean。
         *
         * MyCacheImportController里使用缓存的时候，只使用CacheService接口，不必关注使用的哪个实现类，实现类由@EnableMyCacheService注解
         * 的type参数控制。
         * 这样，比如项目中的缓存需要统一切换的话，就很容易了。
         *
         *
         * 总之，向这种还不能决定去注入哪个处理器（如果你能决定，那就直接@Import那个类好了，没必要实现接口了），
         * 就可以实现此接口，写出一些判断逻辑，不同的配置情况注入不同的处理类。
         */
    
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableMyCacheService.class.getName());
        //region 通过 不同type注入不同的缓存到容器中
        CacheType type = (CacheType) annotationAttributes.get("type");
        switch (type){
            case LOCAL: return new String[]{LocalCacheService.class.getName()};
            case REDIS: return new String[]{RedisCacheService.class.getName()};
            default: throw new RuntimeException(MessageFormat.format("不支持的缓存type {0}", type.name()));
        }
        //endregion
        
    }
}
