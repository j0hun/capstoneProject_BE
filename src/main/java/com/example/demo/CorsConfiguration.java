package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 허용할 Origin 또는 "*"로 모든 Origin 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP Method
                .allowedHeaders("*") // 허용할 Header
                .maxAge(3600); // 캐시 시간(초)
    }
}