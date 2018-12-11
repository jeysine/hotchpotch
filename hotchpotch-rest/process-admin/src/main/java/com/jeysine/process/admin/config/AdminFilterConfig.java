package com.jeysine.process.admin.config;

import com.jeysine.process.admin.filter.AdminAuthEndpointFilter;
import com.jeysine.process.common.filter.LogProcessFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author jxyao
 * @date 2018-9-30
 */
@Configuration
public class AdminFilterConfig {

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

    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean adminAuthEndpointFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(adminAuthEndpointFilter());
        registration.addUrlPatterns("/private/*");
        registration.setName("adminAuthEndpointFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "adminAuthEndpointFilter")
    public Filter adminAuthEndpointFilter() {
        return new AdminAuthEndpointFilter();
    }
}
