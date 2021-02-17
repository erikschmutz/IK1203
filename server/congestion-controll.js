let cwnd = 1;
let ssthr = 10;

setInterval(() => {
  if (Math.random() + cwnd / 50 > 0.9) {
    if (Math.random() > 0.5) handleTimeOut();
    else handleLoss();
  } else handleAck();

  console.log("Size: ", cwnd);
}, 1000);

function handleTimeOut() {
  console.log("Timeout!");
  cwnd = 1;
}

function handleLoss() {
  console.log("Packet loss!");
  cwnd = cwnd / 2;
  ssthr = cwnd;
}

function handleAck() {
  if (cwnd > ssthr) cwnd++;
  else {
    cwnd = cwnd * 2;
  }
}
