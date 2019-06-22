package com.jeysine.services.websocket.config;

public class WebsocketConstants {

    public enum WebsocketMessageType {
        /**
         * 测试广播消息
         */
        TEST_BROADCAST
        /**
         * 点对点消息
         */
        , TEST_POINT_TO_POINT
    }

    public enum RedisChanel {
        /**
         * redis 发布订阅通道
         */
        CHAT
    }
}
