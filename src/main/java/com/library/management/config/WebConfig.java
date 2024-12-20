package com.library.management.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Tüm yollar için
                .allowedOrigins("http://localhost:3000", "http://localhost:3001") // İzin verilen etki alanları
                .allowedMethods("*"); // İzin verilen HTTP yöntemleri
    }
}