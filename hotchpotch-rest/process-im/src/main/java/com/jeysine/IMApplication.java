package com.jeysine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * @author jeysine
 */
@EnableWebSocketMessageBroker
@EnableAsync
@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class IMApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IMApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(IMApplication.class, args);
    }
}
