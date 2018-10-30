package com.pilgrimm.wm.admin.func.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.pilgrimm.wm.websocket.MyWebSocketHandler;

@Controller
@RequestMapping("/func/websocket")
public class WebSocketController {
	
	@RequestMapping("/index")
	public String index() {
		return "/func/websocket/index";
	}
	
	@ResponseBody
	@RequestMapping("/send")
	public Map<String, Object> send(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject message = new JSONObject();
		message.put("msg", "hello!");
		MyWebSocketHandler handler = new MyWebSocketHandler();
		handler.sendMessageToUser("sysadmin", new TextMessage(message.toJSONString()));
		return result;
	}

}
