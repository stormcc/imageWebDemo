package org.example.imagewebdemo.demos.config;

/**
 * Create By: Jimmy Song
 * Create At: 2022-08-15 15:31
 */

public final class HttpResponseConstant {
    private static String CONTENT_TYPE= "application/json; UTF-8";
    private static String CHARACTER_ENCODING = "UTF-8";
    private HttpResponseConstant(){}

    public static String getContentType() {
        return CONTENT_TYPE;
    }

    public static String getCharacterEncoding() {
        return CHARACTER_ENCODING;
    }

}
