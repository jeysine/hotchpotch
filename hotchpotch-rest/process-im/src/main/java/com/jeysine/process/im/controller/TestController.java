package com.jeysine.process.im.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.websocket.config.WebsocketConstants;
import com.jeysine.services.websocket.entity.WebsocketMessage;
import com.jeysine.services.websocket.service.TestMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private TestMessageService testMessageService;

    @GetMapping("/point")
    public ResponseVO testPoint(@RequestParam("to") String to) throws JsonProcessingException {
        WebsocketMessage message = WebsocketMessage.builder()
                .from("test")
                .to(to)
                .type(WebsocketConstants.WebsocketMessageType.TEST_POINT_TO_POINT.name())
                .build();
        testMessageService.handleMessage(message);
        return ResponseVO.success();
    }

    @GetMapping("/broadcast")
    public ResponseVO testBroadcast() throws JsonProcessingException {
        WebsocketMessage message = WebsocketMessage.builder()
                .from("test")
                .type(WebsocketConstants.WebsocketMessageType.TEST_BROADCAST.name())
                .build();
        testMessageService.handleMessage(message);
        return ResponseVO.success();
    }

    @MessageMapping("/service/point")
    public void toPoint(Principal principal, WebsocketMessage message) throws Exception {
        log.info("发送消息给:{}:{}", principal.getName(), message);
        testMessageService.handleMessage(message);
    }

    @MessageMapping("/service/all")
    public void toAll(Principal principal, WebsocketMessage message) throws Exception {
        log.info("发送消息给:{}:{}", principal.getName(), message);
        testMessageService.handleMessage(message);
    }
}
