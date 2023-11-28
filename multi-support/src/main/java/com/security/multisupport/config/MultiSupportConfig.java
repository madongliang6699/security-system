package com.security.multisupport.config;

import com.security.multisupport.appswitch.AppSwitchRequestMappingHandler;
//import com.security.multisupport.multipojo.MultiPojoHandleAdapter;
import com.security.multisupport.utils.SpringContextUtil;
import com.security.multisupport.utils.StringUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class MultiSupportConfig implements WebMvcRegistrations {
//    private static final Logger logger = LoggerFactory.getLogger(MultiSupportConfig.class);
    private final String APP_SWITCH_MAPPING_PROP_NAME = "multi-support.AppSwitchRequestMappingHandler";
    private final String MULTI_POJO_ADAPTER_PROP_NAME = "multi-support.MultiPojoHandleAdapter";

    public MultiSupportConfig() {
        System.out.println("------------多版本控制器配置 MultiSupportConfig 实例化了。。。。。。");
    }

    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping tmpMapping = null;
        String tmpAppSwitchHandlerName = SpringContextUtil.getConfig("multi-support.AppSwitchRequestMappingHandler");
        if (tmpAppSwitchHandlerName == null) {
            tmpMapping = new AppSwitchRequestMappingHandler();
            System.out.println("应用请求选择器在配置文件中不能找到 [ {} ] 配置 使用默认[ AppSwitchRequestMappingHandler ] 自动配置成功, multi-support.AppSwitchRequestMappingHandler");
//            logger.info("应用请求选择器在配置文件中不能找到 [ {} ] 配置 使用默认[ AppSwitchRequestMappingHandler ] 自动配置成功", "multi-support.AppSwitchRequestMappingHandler");
        } else {
            try {
                Object tmpBean = SpringContextUtil.getBean(tmpAppSwitchHandlerName);
                if (tmpBean == null) {
                    System.out.println("应用请求选择器自定义适配器 Bean: [ {} ] 未找到相关bean实例, tmpAppSwitchHandlerName");
//                    logger.warn("应用请求选择器自定义适配器 Bean: [ {} ] 未找到相关bean实例", tmpAppSwitchHandlerName);
                    tmpMapping = new AppSwitchRequestMappingHandler();
                    return tmpMapping;
                }

                tmpBean = tmpBean.getClass().newInstance();
                tmpMapping = (RequestMappingHandlerMapping)tmpBean;
                System.out.println("应用请求选择器自定义适配器 Bean: [ {} ] 适配器自动配置成功\", tmpAppSwitchHandlerName");
//                logger.info("应用请求选择器自定义适配器 Bean: [ {} ] 适配器自动配置成功", tmpAppSwitchHandlerName);
            } catch (Exception var4) {
                tmpMapping = new AppSwitchRequestMappingHandler();
                System.out.println("应用请求选择器自定义适配器 Bean: [ {} ] 强制类型转换至 RequestMappingHandlerMapping 错误或未找到Bean实例对象\", tmpAppSwitchHandlerName, var4");
//                logger.error("应用请求选择器自定义适配器 Bean: [ {} ] 强制类型转换至 RequestMappingHandlerMapping 错误或未找到Bean实例对象", tmpAppSwitchHandlerName, var4);
            }
        }

        return (RequestMappingHandlerMapping)tmpMapping;
    }
//
//    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
//        RequestMappingHandlerAdapter tmpAdapter = null;
//        String tmpAdapterBeanName = SpringContextUtil.getConfig("multi-support.MultiPojoHandleAdapter");
//        if (StringUtil.isEmpty(tmpAdapterBeanName)) {
//            tmpAdapter = new MultiPojoHandleAdapter();
//            logger.info("控制器多Pojo支持套件默认适配器，在配置文件中不能找到 [ {} ] 配置 使用默认[ MultiPojoHandleAdapter ]适配器自动配置成功", "multi-support.MultiPojoHandleAdapter");
//        } else {
//            try {
//                Object tmpBean = SpringContextUtil.getBean(tmpAdapterBeanName);
//                if (tmpBean == null) {
//                    logger.warn("控制器多Pojo支持套件自定义适配器 Bean: [ {} ] 未找到相关bean实例", tmpAdapterBeanName);
//                    tmpAdapter = new MultiPojoHandleAdapter();
//                    return tmpAdapter;
//                }
//
//                tmpAdapter = (RequestMappingHandlerAdapter)tmpBean;
//                logger.info("控制器多Pojo支持套件自定义适配器 Bean: [ {} ] 适配器自动配置成功", tmpAdapterBeanName);
//            } catch (Exception var4) {
//                tmpAdapter = new MultiPojoHandleAdapter();
//                logger.error("控制器多Pojo支持套件自定义适配器 Bean: [ {} ] 强制类型转换至 RequestMappingHandlerAdapter错误或未找到Bean实例对象", tmpAdapterBeanName, var4);
//            }
//        }
//
//        return (RequestMappingHandlerAdapter)tmpAdapter;
//    }
}
