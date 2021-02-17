const udp = require("dgram");
const socket = udp.createSocket("udp4");

socket.on("message", (data, whole) => {
  console.log(whole);
  console.log(data);
  console.log(data.toString());
});

socket.bind(8081);
