package com.security.multisupport.appswitch;

import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.security.multisupport.appswitch.aop.Appid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class AppSwitchRequestMappingHandler extends RequestMappingHandlerMapping {
//    private static final Logger logger = LoggerFactory.getLogger(AppSwitchRequestMappingHandler.class);

    public AppSwitchRequestMappingHandler() {
    }

    protected RequestCondition<AppSwitchCondition> getCustomTypeCondition(Class<?> handlerType) {
//        logger.info("getCustomTypeCondition:{}", handlerType.getName());
        Appid appid = (Appid)AnnotationUtils.findAnnotation(handlerType, Appid.class);
        return this.createCondition(appid);
    }

    private RequestCondition<AppSwitchCondition> createCondition(Appid appid) {
        return appid == null ? null : new AppSwitchCondition(appid.value());
    }

    @Nullable
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> tmpHandlerMethods = this.getHandlerMethods();
        Integer tmpAppid = AppSwitchCondition.matchingAppid(request);
        HandlerMethod tmpDefaultMethod = null;
        HandlerMethod tmpServiceIsNotFoundMethod = null;
        String tmpServiceIsNotFound = "/multiController/notFoundApi";
        Iterator var8 = tmpHandlerMethods.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry tmp = (Map.Entry)var8.next();
            RequestMappingInfo tmpKey = (RequestMappingInfo)tmp.getKey();
            HandlerMethod tmpValue = (HandlerMethod)tmp.getValue();
            AppSwitchCondition tmpCondition = (AppSwitchCondition)tmpKey.getCustomCondition();
            PatternsRequestCondition patternsRequestCondition = tmpKey.getPatternsCondition().getMatchingCondition(request);
            if (patternsRequestCondition != null && patternsRequestCondition.getPatterns().contains(lookupPath) && tmpCondition != null) {
                if (tmpCondition.getAppId() == tmpAppid) {
//                    if (logger.isDebugEnabled()) {
//                        logger.debug("url: [ {} ] appid: [ {} ] 命中自定义控制器: [ {} ]", new Object[]{lookupPath, tmpAppid, tmpValue.getShortLogMessage()});
//                    }

                    return tmpValue;
                }

                if (tmpCondition.getAppId() == 0) {
                    tmpDefaultMethod = tmpValue;
                }
            }

            if (tmpKey.getPatternsCondition().getPatterns().contains(tmpServiceIsNotFound)) {
                tmpServiceIsNotFoundMethod = tmpValue;
            }
        }

        if (tmpDefaultMethod != null) {
            if (logger.isDebugEnabled()) {
                System.out.println("url: [ "+lookupPath+" ]  appid: [ "+tmpAppid+" ] 未命中自定义控制器，使用默认控制器对象: [ "+tmpDefaultMethod.getShortLogMessage()+" ]");
//                logger.debug("url: [ {} ]  appid: [ {} ] 未命中自定义控制器，使用默认控制器对象: [ {} ]", new Object[]{lookupPath, tmpAppid, tmpDefaultMethod.getShortLogMessage()});
            }

            return tmpDefaultMethod;
        } else {
            System.out.println("url: [ "+lookupPath+" ]  未命中任何控制器");
//            logger.warn("url: [ {} ]  未命中任何控制器", lookupPath);
            return tmpServiceIsNotFoundMethod;
        }
    }
}
