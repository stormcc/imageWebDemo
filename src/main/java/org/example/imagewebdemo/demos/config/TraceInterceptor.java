package org.example.imagewebdemo.demos.config;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.example.imagewebdemo.demos.util.LogMdcUtil;
import org.example.imagewebdemo.demos.util.RuntimeFailureHolder;
import org.example.imagewebdemo.demos.util.StringUtil;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By: Jimmy Song
 * Create At: 2024-12-28 10:36
 */
@Slf4j
public class TraceInterceptor implements HandlerInterceptor {
    private static final String DATA_FORMAT = "yyyyMMdd_HHmmss_SSS";
    private static final String WORK_TRACE_ID = "Work-Trace-Id";
    private static final String WORK_FAILURE_ORIGINAL = "Work-Failure-Original";
    private static final int MAX_TRACE_ID_LENGTH = 64;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String headerValue = request.getHeader(WORK_TRACE_ID);
        String traceId;
        if (Strings.isNullOrEmpty(headerValue)) {
            traceId = new SimpleDateFormat(DATA_FORMAT).format(new Date());
        } else {
            headerValue = headerValue.trim();
            if ( headerValue.length()> MAX_TRACE_ID_LENGTH ) {
                traceId = headerValue.substring(0, MAX_TRACE_ID_LENGTH);
            } else {
                traceId = headerValue;
            }
        }
        LogMdcUtil.setTraceId(traceId);
        response.setHeader(WORK_TRACE_ID, traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (RuntimeFailureHolder.containFailure()) {
            response.setHeader(WORK_FAILURE_ORIGINAL, StringUtil.prefix(RuntimeFailureHolder.getFailure(), 20));
        }
        RuntimeFailureHolder.clearFailure();
        LogMdcUtil.removeTraceId();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                         @Nullable Exception ex) throws Exception {
        if (RuntimeFailureHolder.containFailure()) {
            response.setHeader(WORK_FAILURE_ORIGINAL, StringUtil.prefix(RuntimeFailureHolder.getFailure(), 20));
        }
        if (ex != null) {
            log.warn("Exception is not null...");
        }
        RuntimeFailureHolder.clearFailure();
        LogMdcUtil.removeTraceId();
    }
}
