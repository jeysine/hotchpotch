package com.jeysine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author jxyao
 * @date 2018-9-30
 */
@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class AdminApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AdminApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
