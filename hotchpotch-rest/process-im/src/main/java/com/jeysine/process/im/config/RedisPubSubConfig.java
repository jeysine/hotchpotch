package com.jeysine.process.im.config;

import com.jeysine.process.im.handler.WebsocketMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author yaojx
 * @date 2018-10-25
 */
@Configuration
public class RedisPubSubConfig {

    private final SimpMessagingTemplate messagingTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisPubSubConfig(SimpMessagingTemplate messagingTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(WebsocketMessageHandler receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    WebsocketMessageHandler receiver() {
        return new WebsocketMessageHandler(messagingTemplate, redisTemplate);
    }


    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
