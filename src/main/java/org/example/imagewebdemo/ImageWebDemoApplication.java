package org.example.imagewebdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages="org.example.imagewebdemo")
public class ImageWebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageWebDemoApplication.class, args);
    }

}
