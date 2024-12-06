package com.tony.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class MyWebSocketServer extends WebSocketServer {
    private SocketHandler socketHandler;

    public MyWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    public MyWebSocketServer(int port, SocketHandler handler) {
        super(new InetSocketAddress(port));
        this.socketHandler = handler;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
        if (socketHandler != null) {
            socketHandler.onOpen(conn, handshake);
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        if (socketHandler != null) {
            socketHandler.onClose(conn, code, reason, remote);
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (socketHandler != null) {
            socketHandler.onMessage(conn, message);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if (socketHandler != null) {
            socketHandler.onError(conn, ex);
        }
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket Server started!");
        if (socketHandler != null) {
            socketHandler.onStart();
        }
    }

}
