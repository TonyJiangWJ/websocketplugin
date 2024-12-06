module.exports = function (plugin) {
  const runtime = plugin.runtime

  function SocketServerCreator() {
    this.createServer = function (port, socketHandler) {
      return plugin.createServer(port, socketHandler)
    }

    this.createClient = function (serverUrl, clientHandler) {
      return plugin.createClient(serverUrl, clientHandler)
    }
  }

  return new SocketServerCreator()

}