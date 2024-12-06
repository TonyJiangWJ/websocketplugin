package com.tony.websocket;

import android.content.Context;

import org.autojs.plugin.sdk.Plugin;
import org.autojs.plugin.sdk.PluginLoader;
import org.autojs.plugin.sdk.PluginRegistry;

public class PluginRegister extends PluginRegistry {
    static {
        // 注册默认插件
        registerDefaultPlugin(new PluginLoader() {
            @Override
            public Plugin load(Context context, Context selfContext, Object runtime, Object topLevelScope) {
                return new WebSocketPlugin(context, selfContext, runtime, topLevelScope);
            }
        });
    }

}