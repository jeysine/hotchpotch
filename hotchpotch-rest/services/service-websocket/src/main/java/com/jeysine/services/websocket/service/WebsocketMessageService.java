package com.jeysine.services.websocket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jeysine.services.websocket.entity.WebsocketMessage;

public interface WebsocketMessageService<T extends WebsocketMessage> {

    T handleMessage(T data) throws JsonProcessingException;
}
