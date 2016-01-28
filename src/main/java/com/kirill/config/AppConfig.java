package com.kirill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Kirill on 10.01.2016.
 */

@Configuration
@ComponentScan({
        "com.kirill.dao",
        "com.kirill.services",
        "com.kirill.config",
        "com.kirill.controller"
})
@EnableTransactionManagement
@Import({SpringMVCConfig.class, SpringConfig.class})
public class AppConfig {

}