const net = require("net");

const server = net.createServer(socket => {
  socket.write("hello from a simple tcp server");
  socket.on("data", data => {
    console.log(data.toString());
  });
});

server.listen(8080);
