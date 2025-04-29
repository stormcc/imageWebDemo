package org.example.imagewebdemo.demos.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.example.imagewebdemo.vo.JsonResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Create By: Jimmy Song
 * Create At: 2022-08-15 11:23
 */
@Slf4j
@Component("validHeaderInterceptor")
public class ValidHeaderInterceptor implements HandlerInterceptor {

    @Value("#{'${system.security.access.control.allow.origin:}'.split(',')}")
    private Set<String> originalWhiteSet;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("valid http header, originalWhiteSet is:{}", originalWhiteSet);
        String allowOrigin = HttpRequestConstant.HEADER_NAME_ACCESS_CONTROL_ALLOW_ORIGIN;
        if (Strings.isNullOrEmpty(allowOrigin)) {
            String msg = "invalid http header, allowOrigin is blank";
            log.warn(msg);
            setJsonErrorResponse(response, 10002, msg);
            return false;
        }
        if (originalWhiteSet.contains(allowOrigin)) {
            log.info("valid http header, allowOrigin is {}, originalWhiteSet:{}", allowOrigin, originalWhiteSet);
            return true;
        }
        log.warn("invalid http header, allowOrigin is {}, originalWhiteSet:{}", allowOrigin, originalWhiteSet);
        setJsonErrorResponse(response, 10002, "invalid http header, allowOrigin is" + allowOrigin);
        return false;
    }

    private void setJsonErrorResponse(HttpServletResponse response, Integer code, String message) {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResultVo<String> vo = new JsonResultVo<>(code, message);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(vo);
            response.getWriter().write(json);
        } catch (IOException e) {
            log.error("setJsonErrorResponse MyInterceptor ", e);
        }
    }
}
