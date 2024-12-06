package com.tony.websocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

public class MyWebSocketClient extends WebSocketClient {
    private ClientHandler clientHandler;

    public MyWebSocketClient(String serverUrl, ClientHandler clientHandler) throws URISyntaxException {
        super(new URI(serverUrl));
        this.clientHandler = clientHandler;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        if (clientHandler != null) {
            clientHandler.onOpen(handshake);
        }
    }

    @Override
    public void onMessage(String message) {
        if (clientHandler != null) {
            clientHandler.onMessage(message);
        }
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        if (clientHandler != null) {
            clientHandler.onByteMessage(bytes);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (clientHandler != null) {
            clientHandler.onClose(code, reason, remote);
        }
    }

    @Override
    public void onError(Exception ex) {
        if (clientHandler != null) {
            clientHandler.onError(ex);
        }
    }

}