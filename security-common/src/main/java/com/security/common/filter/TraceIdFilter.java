package com.security.common.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(urlPatterns = "/**", filterName = "traceIdFilter")
@Log4j2
@Configuration
public class TraceIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //从前端请求头中获取traceId,如果没有则生成一个
        String traceId = httpServletRequest.getHeader(TraceContext.TRACE_ID_KEY);
        if (StrUtil.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString().replaceAll("-", "");
        }

        //将traceId放到日志框架的上下文中的ThreadLocal中
        TraceContext.setTraceId(traceId);

        log.info("请求start:{}", httpServletRequest.getRequestURL().toString());
        long st = System.currentTimeMillis();
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            long et = System.currentTimeMillis();
            log.info("请求end:{},耗时(ms):{}", httpServletRequest.getRequestURL().toString(), (et - st));
            //清理ThreadLocal中的traceId
            TraceContext.clearTraceId();
        }
    }

}
