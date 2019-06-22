package com.jeysine.process.im.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * @author yaojx
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {


    private final WebSocketUserInterceptor webSocketUserInterceptor;

    @Autowired
    public WebSocketStompConfig(WebSocketUserInterceptor webSocketUserInterceptor) {
        this.webSocketUserInterceptor = webSocketUserInterceptor;
    }

    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在网页上我们就可以通过这个链接
        // http://localhost:8080/webSocketServer
        // 来和服务器的WebSocket连接
        registry.addEndpoint("/webServer")
                //.addInterceptors(sessionAuthHandshakeInterceptor)
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * 配置信息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 订阅Broker名称
        registry.enableSimpleBroker( "/topic","/queue");
        // 全局使用的消息前缀（客户端订阅路径上会体现出来）
        registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user/");
    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketUserInterceptor);

        //其中corePoolSize为核心线程数, maxPoolSize最大线程数, queueCapacity队列容积.
        //这里需要注意一点, queueCapacity的默认配置是无限大, 如果是无限大, 那么线程数则永远是核心线程数.
        //只能当队列容积不够用时, 实际线程数才会大于核心线程数.
        /*registration.taskExecutor()
                .corePoolSize(32)
                .maxPoolSize(200)
                .queueCapacity(1000);*/

    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
       // registration.addDecoratorFactory(authWebSocketHandlerDecoratorFactory);
        WebSocketMessageBrokerConfigurer.super.configureWebSocketTransport(registration);
    }

}
