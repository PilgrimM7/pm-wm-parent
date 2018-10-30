package com.pilgrimm.wm.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class MyWebSocketConfigurer extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/ws").addInterceptors(new MyWebSocketHandshakeInterceptor());
		registry.addHandler(webSocketHandler(), "/sockjs/im").addInterceptors(new MyWebSocketHandshakeInterceptor())
				.withSockJS();
	}

	@Bean
	public TextWebSocketHandler webSocketHandler() {
		return new MyWebSocketHandler();
	}

}