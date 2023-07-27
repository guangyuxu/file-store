package com.wov.gavin.domain.config;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {
    @Value("${file.root.path:${user.home}}/files")
    private String uploadPath;

    @Bean
    public String fileUploadPath() {
        boolean mkdirs = new File(this.uploadPath).mkdirs();
        return this.uploadPath;
    }
}
