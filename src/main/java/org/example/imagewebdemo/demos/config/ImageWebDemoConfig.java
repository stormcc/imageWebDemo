package org.example.imagewebdemo.demos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create By: Jimmy Song
 * Create At: 2025-01-16 16:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "imagewebdemo")
public class ImageWebDemoConfig {
    private String name;
    private Integer age;
}
