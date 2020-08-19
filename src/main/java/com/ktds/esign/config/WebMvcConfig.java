package com.ktds.esign.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final String uploadFilePath;

    public WebMvcConfig(@Value("${app.file.upload.path}") String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("uploadFilePath=====================>{}", uploadFilePath);
        log.info("uploadFilePath=====================>{}", "file://" + uploadFilePath);
        registry.addResourceHandler("/upload/**") // every file request
                .addResourceLocations("file://" + uploadFilePath); // find this location
    }


}
