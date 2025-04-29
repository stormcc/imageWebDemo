package org.example.imagewebdemo.demos.web;

import org.example.imagewebdemo.demos.config.HttpRequestConstant;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Create By: Jimmy Song
 * Create At: 2025-04-28 10:08
 */
class MonitorControllerTest extends BaseControllerTest {

    @Test
    void one() {
        String url = "/monitor/one";
        try {
            baseTest(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void oneWithHeaderMap() {
        String url = "/monitor/one";
        Map<String, String> headerMap = headerMap();
        try {
            baseTest(url, 2000, headerMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> headerMap(){
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(HttpRequestConstant.HEADER_NAME_ACCESS_CONTROL_ALLOW_ORIGIN, "http://www.baidu.com/");
        headerMap.put(HttpRequestConstant.HEADER_NAME_ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        headerMap.put(HttpRequestConstant.HEADER_NAME_ACCESS_CONTROL_ALLOW_METHODS, "GET, POST");
        return headerMap;
    }

}