package com.jeysine.services.websocket.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeysine.services.websocket.config.WebsocketConstants;
import com.jeysine.services.websocket.entity.WebsocketMessage;
import com.jeysine.services.websocket.service.TestMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestMessageServiceImpl implements TestMessageService {

    @Autowired
    private StringRedisTemplate template;

    public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public WebsocketMessage handleMessage(WebsocketMessage data) throws JsonProcessingException {
        WebsocketConstants.WebsocketMessageType type = WebsocketConstants.WebsocketMessageType.valueOf(data.getType());
        switch (type) {
            case TEST_BROADCAST: sendToAll(data);break;
            case TEST_POINT_TO_POINT: sendToUser(data);break;
            default : break;
        }
        log.info("handle message hear, data: {}", data);
        return null;
    }

    private void sendToUser(WebsocketMessage message) throws JsonProcessingException {
        template.convertAndSend(WebsocketConstants.RedisChanel.CHAT.name(), OBJECT_MAPPER.writeValueAsString(message));
    }

    private void sendToAll(WebsocketMessage message) throws JsonProcessingException {
        template.convertAndSend(WebsocketConstants.RedisChanel.CHAT.name(), OBJECT_MAPPER.writeValueAsString(message));
    }
}
