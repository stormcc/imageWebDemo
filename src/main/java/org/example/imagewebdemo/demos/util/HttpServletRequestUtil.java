package org.example.imagewebdemo.demos.util;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;

/**
 * Create By: Jimmy Song
 * Create At: 2023-06-28 16:38
 */
public final class HttpServletRequestUtil {
    private final static String REQUEST_HEADER_REFERER_NAME = "Referer";
    private HttpServletRequestUtil(){}

    public static String url(HttpServletRequest request){
        if ( request == null ) {
            return null;
        }

        if (Strings.isNullOrEmpty(request.getQueryString())) {
            return request.getMethod() + " " + request.getRequestURI();
        } else {
            return request.getMethod() + " " + request.getRequestURI() + "?" + request.getQueryString();
        }
    }

    public static String referer(HttpServletRequest request){
        if ( request == null ) {
            return null;
        }
        return request.getHeader(REQUEST_HEADER_REFERER_NAME);
    }
}
