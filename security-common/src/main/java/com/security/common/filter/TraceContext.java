package com.security.common.filter;

import org.slf4j.MDC;

public class TraceContext {

    public static final String TRACE_ID_KEY = "traceId";

    public static String getTraceId() {
        return MDC.get(TRACE_ID_KEY);
    }

    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID_KEY, traceId);
    }

    public static void clearTraceId() {
        MDC.remove(TRACE_ID_KEY);
    }
}
