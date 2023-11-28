//package com.security.multisupport.multipojo;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
//
//public class MultiPojoHandleAdapter extends RequestMappingHandlerAdapter {
//    private static final Logger logger = LoggerFactory.getLogger(MultiPojoHandleAdapter.class);
//
//    public MultiPojoHandleAdapter() {
//    }
//
//    protected ServletInvocableHandlerMethod createInvocableHandlerMethod(HandlerMethod handlerMethod) {
//        return new MultiInvocableHandlerMethod(handlerMethod);
//    }
//}
