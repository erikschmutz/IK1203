# address telnet ik1203.private.lab 80
## This needs to be ported into 
echo "open $1 $2 "
sleep 1
echo "HEAD /index.html HTTP/1.1"
sleep 1
echo "Host: $1"
echo 
sleep 2