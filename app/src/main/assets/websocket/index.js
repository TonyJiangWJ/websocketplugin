module.exports = function (plugin) {
  const runtime = plugin.runtime

  function SocketServerCreator() {
    this.createServer = function (port, socketHandler) {
        return plugin.createServer(port, socketHandler)
    }
  }

  return new SocketServerCreator()

}