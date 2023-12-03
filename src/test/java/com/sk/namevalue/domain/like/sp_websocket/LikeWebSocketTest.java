package com.sk.namevalue.domain.like.sp_websocket;


import com.sk.namevalue.domain.like.dto.LikePayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LikeWebSocketTest {

    @LocalServerPort
    private int port;
    WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());

    @BeforeEach
    void setUp(){
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    @Test
    void test() throws ExecutionException, InterruptedException, TimeoutException {
        String uri = String.format("ws://localhost:%d/ws",port);
        StompSession stompSession = stompClient.connect(uri, new StompSessionHandler() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("afterConnected");
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                System.out.println("handleException");
            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                System.out.println("handleTransportError");
            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return null;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {

            }
        }).get(1, TimeUnit.SECONDS);

        stompSession.subscribe("/name-topic/홍길동", new StompSessionHandlerAdapter() {
        });

        stompSession.send("/name-topic/홍길동", LikePayload.of(1L, 10));
    }
}
