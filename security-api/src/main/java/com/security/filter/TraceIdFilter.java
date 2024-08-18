package com.security.filter;

import cn.hutool.core.util.StrUtil;
import com.security.common.filter.TraceContext;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.UUID;

/**
 * 过滤器
 *
 * @author madongliang
 * &#064;date  2024/3/27
 */
//这里注解指定了该过滤器同时使用于消费端服务和提供者端服务，然后在下面的代码中判断不同的端走不同的逻辑（也可以写两个过滤器，分别使用于消费端服务和提供者端服务）
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
@Log4j2
public class TraceIdFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        Object[] arguments = invocation.getArguments();

        if (RpcContext.getContext().isConsumerSide()) {
            // 在消费者端（调用方）生成或获取 traceId
            String traceId = generateOrGetTraceId();
            RpcContext.getContext().setAttachment(TraceContext.TRACE_ID_KEY, traceId);
        } else if (RpcContext.getContext().isProviderSide()) {
            // 在提供者端（被调用方）接收 traceId 并记录
            String traceId = RpcContext.getContext().getAttachment(TraceContext.TRACE_ID_KEY);
            saveTraceId(traceId);
        }
        return invoker.invoke(invocation);
    }


    private String generateOrGetTraceId() {
        // 从已有的上下文中获取
        String traceId = TraceContext.getTraceId();
        if (StrUtil.isBlank(traceId)) {
            //如果为空，生成一个
            traceId = UUID.randomUUID().toString();
            TraceContext.setTraceId(traceId);
        }
        return traceId;
    }

    private void saveTraceId(String traceId) {
        // 将 traceId 保存到当前上下文中
        TraceContext.setTraceId(traceId);
    }

}
