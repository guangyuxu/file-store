package com.woven.gavin.fs.api.config;

import com.woven.gavin.domain.DomainApplication;
import com.woven.gavin.domain.config.DomainConfiguration;
import com.woven.gavin.fs.api.ApiApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DomainConfiguration.class, SpringFoxConfig.class})
@ComponentScan(basePackageClasses = {DomainApplication.class, ApiApplication.class})
public class ApiConfiguration {
}
