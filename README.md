# websocket插件

## 插件说明

- 基于 'org.java-websocket:Java-WebSocket:1.5.7' 实现的简易WebSocket服务端插件，可以实现基础的websocket服务端和客户端，进行一些通信操作。

## 源码编译说明

- clone当前仓库代码
- 在环境变量中配置签名证书信息，证书的创建自己问AI或者利用搜索引擎
  - `autojspasswd` 证书密钥
  - `autojsalias` 证书alias
  - `autojsstorepath` 证书路径
  - 可以直接在`~/.bashrc` 或 `~/.zshrc` 中添加，然后`source ~/.zshrc`加载 内容如下：
    ```shell
    export autojspasswd="你的证书密码"
    export autojsalias="你的证书alias"
    export autojsstorepath="你的.jks证书所在路径"
    ```
- 命令行中执行 `./gradlew build` 即可编译apk，然后安装到手机即可，编译后路径为`app/build/outputs/apk/release/app-release.apk`

## 插件使用说明

- 先下载或者编译apk文件，安装到手机上

### 服务端代码参考

- 编写代码，加载插件并调用，服务端代码参考如下：
  ```javascript
  let plugin_websocket = (() => {
    try {
      return plugins.load('com.tony.websocket')
    } catch (e) {
      toastLog('当前未安装websocket插件，加载失败' + e)
      exit()
    }
  })()
  // 创建websocket服务端
  let socketServer = plugin_websocket.createServer(8080/*指定监听端口*/, {
      onOpen: function (conn, handshake) {
  			// 监听连接创建成功的消息
      },
      onClose: function (conn, code, reason, remote) {
  			// 监听连接断开消息
      },
      onMessage: function (conn, message) {
        // 监听收到消息的操作，这里简易demo 直接返回收到的消息给客户端
        conn.send("Echo: " + message);
      },
    	onByteMessage: function (conn, bytes) {
        // 监听二进制数据
      },
      onError: function (conn, ex) {
  			// 监听连接异常信息
      },
      onStart: function () {
        // 监听服务启动消息
        toastLog('服务已启动')
      }
    }
  )
  // 启动websocket服务
  socketServer.start()
  setInterval(()=> {
  	// 避免脚本自动退出
  }, 20000)
  
  events.on('exit', function () {
    // 脚本退出时主动断开服务
    socketServer.stop()
  })
  ```

### 客户端代码参考

```javascript
let plugin_websocket = (() => {
  try {
    return plugins.load('com.tony.websocket')
  } catch (e) {
    toastLog('当前未安装websocket插件，加载失败' + e)
    exit()
  }
})()
// 创建websocket客户端
let socketClient = plugin_websocket.createClient("ws://192.168.1.100:8080"/*指定服务ip和端口*/, {
    onOpen: function (handshake) {
			// 监听连接创建成功的消息
    },
    onMessage: function (message) {
      // 监听收到消息的操作
    },
  	onByteMessage: function (bytes) {
      // 监听二进制数据
    },
    onError: function (ex) {
			// 监听连接异常信息
    },
    onClose: function (code, reason, remote) {
      // 监听连接断开消息
    }
  }
)
// 启动websocket服务
// 连接到服务端
socketClient.connect();

// 主线程等待连接成功（可选）
while (!socketClient.isOpen()) {
  sleep(100); // 等待连接打开
}
socketClient.send("How are you, Server?")
setInterval(()=> {
	// 避免脚本自动退出
}, 20000)

events.on('exit', function () {
  // 脚本退出时主动断开服务
  socketClient.close()
})
```



## HTML客户端示例代码

- 复制以下代码，保存到任意html文件并用浏览器打开即可：

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebSocket Client</title>
</head>
<body>
    <h1>WebSocket Client</h1>
    <div>
        <label for="message">Message:</label>
        <input type="text" id="message" placeholder="Enter a message">
        <button id="sendButton">Send</button>
    </div>
    <div id="output" style="margin-top: 20px;">
        <h3>Messages:</h3>
        <ul id="messages"></ul>
    </div>

    <script>
        // 初始化 WebSocket
        const serverUrl = "ws://192.168.1.100:8080"; // 替换为服务端的地址和端口
        const socket = new WebSocket(serverUrl);

        const outputElement = document.getElementById("messages");
        const messageInput = document.getElementById("message");
        const sendButton = document.getElementById("sendButton");

        // 连接打开时的回调
        socket.onopen = () => {
            addMessage("Connected to the WebSocket server");
        };

        // 接收到消息时的回调
        socket.onmessage = (event) => {
            addMessage("Server: " + event.data);
        };

        // 连接关闭时的回调
        socket.onclose = () => {
            addMessage("Connection closed");
        };

        // 出现错误时的回调
        socket.onerror = (error) => {
            addMessage("Error: " + error.message);
        };

        // 点击发送按钮发送消息
        sendButton.addEventListener("click", () => {
            const message = messageInput.value.trim();
            if (message) {
                socket.send(message);
                addMessage("You: " + message);
                messageInput.value = ""; // 清空输入框
            }
        });

        // 添加消息到页面
        function addMessage(message) {
            const li = document.createElement("li");
            li.textContent = message;
            outputElement.appendChild(li);
        }
    </script>
</body>
</html>

```

