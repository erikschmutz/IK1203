# address telnet ik1203.private.lab 80
echo "open $1 $2 "
sleep 1
echo "GET /index.html HTTP/1.1"
sleep 1
echo "Host: $1"
echo 
sleep 2