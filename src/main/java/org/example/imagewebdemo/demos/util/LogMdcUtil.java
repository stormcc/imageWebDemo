package org.example.imagewebdemo.demos.util;

import org.slf4j.MDC;

/**
 * Create By: Jimmy Song
 * Create At: 2023-04-06 10:06
 */
public final class LogMdcUtil {
    private LogMdcUtil(){}

    private static final String SLF4J_MDC_NAME_TRACE_ID= "traceId";

    public static void setTraceId(String traceId){
        MDC.put(SLF4J_MDC_NAME_TRACE_ID, traceId);
    }

    public static void appendAfterTraceId(String content){
        String traceId = MDC.get(SLF4J_MDC_NAME_TRACE_ID);
        MDC.put(SLF4J_MDC_NAME_TRACE_ID,traceId+content);
    }

    public static String getTraceId(){
        return MDC.get(SLF4J_MDC_NAME_TRACE_ID);
    }

    public static void removeTraceId(){
        MDC.remove(SLF4J_MDC_NAME_TRACE_ID);
    }
}
