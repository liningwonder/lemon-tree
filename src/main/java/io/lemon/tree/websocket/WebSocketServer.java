package io.lemon.tree.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {

    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    //ws://127.0.0.1:8080/websocket/1
    private Session session;

    //接收session id
    private String sid = "";


    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        this.sid = sid;
        webSocketSet.put(sid, this);
        log.info("[WebSocket] 会话 {} 连接成功，当前连接人数为：{}", sid, webSocketSet.size());
        try {
            sendMessage("[WebSocket] 连接成功, 客户端会话ID : " + sid);
        } catch (IOException e) {
            log.error("Websocket IO Exception");
        }
    }


    @OnClose
    public void onClose() {
        webSocketSet.remove(this.sid);
        log.info("[WebSocket] 会话ID {} 退出成功，当前连接人数为：={}", this.sid, webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("[WebSocket] 收到来自窗口" + sid + "的信息:" + message);
        //TODO 处理客户端消息（如果是群聊, 则发给所有人）
        session.getBasicRemote().sendText("回显 : " + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("[WebSocket] 发生错误", error);
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendMessageToSid(String message, String sid) throws IOException {
        WebSocketServer webSocketServer = webSocketSet.get(sid);
        if (null == webSocketServer) {
            log.error("请求的 sid:" + sid + "不在该服务器上");
        } else {
            webSocketServer.sendMessage(message);
        }
    }

    public void sendMessageToAll(String message) throws IOException {
        for (WebSocketServer webSocketServer : webSocketSet.values()) {
            webSocketServer.sendMessage(message);
        }
    }

}
