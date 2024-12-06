package com.tony.websocket;

import org.java_websocket.handshake.ServerHandshake;

import java.nio.ByteBuffer;

public interface ClientHandler {
    void onOpen(ServerHandshake handshake);

    void onMessage(String message);

    void onByteMessage(ByteBuffer bytes);

    void onClose(int code, String reason, boolean remote);

    void onError(Exception ex);
}
