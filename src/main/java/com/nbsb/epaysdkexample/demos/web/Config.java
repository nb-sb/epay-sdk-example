package com.nbsb.epaysdkexample.demos.web;

import com.nbsb.epaysdk.epaybase.properties.LoaderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
public class Config {
    @Resource
    private Environment environment;
    @Bean
    public void init() {
        LoaderConfig loaderConfig = new LoaderConfig();
        loaderConfig.setEnvironment(environment);
        loaderConfig.afterPropertiesSet();
    }
}
