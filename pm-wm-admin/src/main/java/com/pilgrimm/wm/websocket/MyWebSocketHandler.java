package com.pilgrimm.wm.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

	private static final Map<String, Map<String, WebSocketSession>> usersMap;

	static {
		usersMap = new HashMap<String, Map<String, WebSocketSession>>();
	}

	public MyWebSocketHandler() {
	}

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String userFlag = session.getAttributes().get("WEBSOCKET_USERFLAG").toString();
		System.out.println("websocket user[" + userFlag + "]user session id[" + session.getId() + "]已连接");
		
		Map<String, WebSocketSession> socketSessionMap = usersMap.get(userFlag);
		if (socketSessionMap == null) {
			socketSessionMap = new HashMap<String, WebSocketSession>(4);
			socketSessionMap.put(session.getId(), session);
			usersMap.put(userFlag, socketSessionMap);
		} else {
			if (socketSessionMap.get(session.getId()) == null) {
				socketSessionMap.put(session.getId(), session);
			}
		}
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		String userFlag = session.getAttributes().get("WEBSOCKET_USERFLAG").toString();
		Map<String, WebSocketSession> socketSessionMap = usersMap.get(userFlag);
		if (socketSessionMap != null) {
			socketSessionMap.remove(session.getId());
			if (socketSessionMap.isEmpty()) {
				usersMap.remove(userFlag);
			}
		}
		System.out.println("websocket user[" + userFlag + "]user session id[" + session.getId() + "]已关闭");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
	}

	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		String userFlag = session.getAttributes().get("WEBSOCKET_USERFLAG").toString();
		Map<String, WebSocketSession> socketSessionMap = usersMap.get(userFlag);
		if (socketSessionMap != null) {
			socketSessionMap.remove(session.getId());
			if (socketSessionMap.isEmpty()) {
				usersMap.remove(userFlag);
			}
		}
		System.out.println("websocket user[" + userFlag + "]user session id[" + session.getId() + "]已移除");
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给某个用户发送消息
	 *
	 * @param userFlag
	 * @param message
	 */
	public void sendMessageToUser(String userFlag, TextMessage message) {
		Map<String, WebSocketSession> socketSessionMap = usersMap.get(userFlag);
		if (socketSessionMap != null) {
			Iterator<Entry<String, WebSocketSession>> it = socketSessionMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, WebSocketSession> entry = it.next();
				if (entry.getValue() != null && entry.getValue().isOpen()) {
					try {
						entry.getValue().sendMessage(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 给所有在线用户发送消息
	 *
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		Iterator<Entry<String, Map<String, WebSocketSession>>> it = usersMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Map<String, WebSocketSession>> entry = it.next();
			Iterator<Entry<String, WebSocketSession>> it2 = entry.getValue().entrySet().iterator();
			// 多线程群发
			while (it2.hasNext()) {
				final Entry<String, WebSocketSession> entry2 = it2.next();
				if (entry2.getValue().isOpen()) {
					// entry.getValue().sendMessage(message);
					new Thread(new Runnable() {
						public void run() {
							try {
								if (entry2.getValue().isOpen()) {
									entry2.getValue().sendMessage(message);
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		}
	}

	public boolean sendMessageToAppointUsers(TextMessage message, String appoint) {
		boolean flag = false;
		Iterator<Entry<String, Map<String, WebSocketSession>>> it = usersMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Map<String, WebSocketSession>> entry = it.next();
			if (entry.getKey().contains(appoint)) {
				Iterator<Entry<String, WebSocketSession>> it2 = entry.getValue().entrySet().iterator();
				// 多线程群发
				while (it2.hasNext()) {
					final Entry<String, WebSocketSession> entry2 = it2.next();
					if (entry2.getValue().isOpen()) {
						// entry.getValue().sendMessage(message);
						new Thread(new Runnable() {
							public void run() {
								try {
									if (entry2.getValue().isOpen()) {
										entry2.getValue().sendMessage(message);
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}).start();
						flag = true;
					}
				}
			}
		}
		return flag;
	}
}