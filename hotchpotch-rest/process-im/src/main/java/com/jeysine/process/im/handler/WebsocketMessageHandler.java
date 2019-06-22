package com.jeysine.process.im.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeysine.services.websocket.config.WebsocketConstants;
import com.jeysine.services.websocket.entity.WebsocketMessage;
import com.jeysine.services.websocket.service.TestMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author yaojx
 * @date 2018-10-25
 */
@Slf4j
public class WebsocketMessageHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    private final SimpMessagingTemplate messagingTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public WebsocketMessageHandler(SimpMessagingTemplate simpMessagingTemplate, RedisTemplate redisTemplate) {
        this.messagingTemplate = simpMessagingTemplate;
        this.redisTemplate = redisTemplate;
    }

    public void receiveMessage(String message) {
        try {
            WebsocketMessage notificationMessage = mapper.readValue(message, WebsocketMessage.class);
            WebsocketConstants.WebsocketMessageType typeEnum = WebsocketConstants.WebsocketMessageType.valueOf(notificationMessage.getType());
            switch (typeEnum) {
                /**
                 * 测试广播消息
                 */
                case TEST_BROADCAST: sendToAll(notificationMessage); break;
                case TEST_POINT_TO_POINT: sendToUser(notificationMessage); break;

                default: log.warn("匹配不到notificationMessage數據類型:{}", message);
            }
        } catch (IOException e) {
            log.error("error: ", e);
        }
        log.info("Received <" + message + ">");
    }


    private void sendToUser(WebsocketMessage message) throws JsonProcessingException {
        messagingTemplate.convertAndSend("/queue/service/all", message);
    }

    private void sendToAll(WebsocketMessage message) throws JsonProcessingException {
        messagingTemplate.convertAndSendToUser(message.getTo(),"/queue/service/point", message);
    }
}
