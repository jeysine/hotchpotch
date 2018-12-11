package com.jeysine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author jxyao
 * @date 2018-9-30
 */
@EnableScheduling
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class AppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
