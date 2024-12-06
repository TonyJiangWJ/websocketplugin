package com.tony.websocket;

import android.content.Context;

import org.autojs.plugin.sdk.Plugin;

public class WebSocketPlugin extends Plugin {

    public WebSocketPlugin(Context context, Context selfContext, Object runtime, Object topLevelScope) {
        super(context, selfContext, runtime, topLevelScope);
    }

    @Override
    public String getAssetsScriptDir() {
        return "websocket";
    }


    public MyWebSocketServer createServer(int port, SocketHandler socketHandler) {
        return new MyWebSocketServer(port, socketHandler);
    }

    public MyWebSocketClient createClient(String serverUrl, ClientHandler clientHandler) throws Exception {
        return new MyWebSocketClient(serverUrl, clientHandler);
    }


}
