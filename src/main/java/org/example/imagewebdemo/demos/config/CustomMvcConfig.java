package org.example.imagewebdemo.demos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;


/**
 * Create By: Jimmy Song
 * Create At: 2023-02-09 20:27
 */
@Configuration
public class CustomMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private ValidHeaderInterceptor validHeaderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogRequestInterceptor())
//                .addPathPatterns("/**");
        registry.addInterceptor(new TraceInterceptor() ).addPathPatterns("/**");
        registry.addInterceptor(validHeaderInterceptor ).addPathPatterns("/**");
//        registry.addInterceptor(new LogRequestInterceptor() ).addPathPatterns("/**");
    }


}
