package com.wov.gavin.fs.api.config;

import com.wov.gavin.domain.DomainApplication;
import com.wov.gavin.domain.config.DomainConfiguration;
import com.wov.gavin.fs.api.ApiApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DomainConfiguration.class, SpringFoxConfig.class})
@ComponentScan(basePackageClasses = {DomainApplication.class, ApiApplication.class})
public class ApiConfiguration {
}
