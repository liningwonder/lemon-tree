package io.lemon.tree.service.impl;

import io.lemon.tree.service.LemonTreeService;
import io.lemon.tree.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class LemonTreeServiceImpl implements LemonTreeService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public String getHelloMessage() throws IOException {
        webSocketServer.sendMessageToAll("开始调用了");
        log.debug("start process...");
        return "invoke test";
    }
}
