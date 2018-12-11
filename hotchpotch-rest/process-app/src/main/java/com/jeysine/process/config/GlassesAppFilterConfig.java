package com.jeysine.process.config;

import com.jeysine.process.common.filter.LogProcessFilter;
import com.jeysine.process.filter.AppAuthEndpointFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author yaojx
 * @date 2018-10-30
 */
@Configuration
public class GlassesAppFilterConfig {
    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> logProcessFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(logProcessFilter());
        registration.addUrlPatterns("/*");
        registration.setName("logProcessFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "logProcessFilter")
    public Filter logProcessFilter() {
        return new LogProcessFilter();
    }

    @Bean
    public FilterRegistrationBean appAuthEndpointFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(appAuthEndpointFilter());
        registration.addUrlPatterns("/private/*");
        registration.setName("appAuthEndpointFilter");
        return registration;
    }

    @Bean(name = "appAuthEndpointFilter")
    public Filter appAuthEndpointFilter() {
        return new AppAuthEndpointFilter();
    }
}
