package com.Smart_Student_Attendance_Backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/student/**") // Apply to all sub-routes
                .allowedOrigins("*") // Allow requests from any frontend
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("*"); // Allow all headers
    }


}
