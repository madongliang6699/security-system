package com.security.study.spring.注解.Import.demo1;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.mdl.注解.Import.demo1.MyImportTestUser"};
    }
}
