package Server;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chatRoomServer")
public class SocketServer {

	static Set<Session> users = Collections.synchronizedSet(new HashSet<>());
	static Map<String,Set<Session>> ListUser =  new HashMap<String,Set<Session>>();
	@OnOpen
	public void handleOpen(Session session) {
		users.add(session);
	}

	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
//		String username = (String) userSession.getUserProperties().get("username");
//		if (username == null) {
//			userSession.getUserProperties().put("username", message);
//			userSession.getBasicRemote().sendText("System: you are connectd as " + message);
//		} else {
//			for (Session session : users) {
//				session.getBasicRemote().sendText(username + ": " + message);
//			}
//		}
		String[] Chanel = message.split(" ", 3);
//		System.out.println(pub[0]);
			switch (Chanel[0]) {
		case "PUB":
			System.out.println("server pub");
			if(ListUser.containsKey(Chanel[1])) {
				for(Session session : ListUser.get(Chanel[1])) {
					session.getBasicRemote().sendText("Chanel "+Chanel[1]+" notify : " + Chanel[2]);
				}
			}
			userSession.getBasicRemote().sendText("Admin chanel " + Chanel[1]+": "+ Chanel[2]);
			break;
		case "SUB":
			System.out.println("server sub");
			if(ListUser.containsKey(Chanel[1])) {
				if(!ListUser.get(Chanel[1]).contains(userSession)) {
					ListUser.get(Chanel[1]).add(userSession);
					System.out.println("add new customer!");
				}
			}
			else {
				Set<Session> us = Collections.synchronizedSet(new HashSet<>());
				us.add(userSession);
				ListUser.put(Chanel[1], us);
				System.out.println("create new chanel");
			}
			userSession.getBasicRemote().sendText("You SUB chanel " + Chanel[1]);
			break;
		}
		
		
	}

	@OnClose
	public void handleClose(Session session) {
		users.remove(session);
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

}