package com.sk.namevalue.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * title        : WebSocketConfig
 * author       : sim
 * date         : 2023-11-29
 * description  : STOMP + 메시지 브로커를 지원하는 웹소켓 설정
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // like-topic으로 시작되는 토픽을 구독한 클라이언트들에게 메시지를 브로드캐스팅한다.
        registry.enableSimpleBroker("/name-topic");
    }
}
