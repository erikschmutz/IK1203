echo "open 192.168.3.16 smtp"
sleep 1
echo "HELO mail.private.lab"
echo "MAIL FROM: <$1>"
echo "RCPT TO: <$2>"
sleep 1
echo "DATA"
sleep 2 
echo "$3"
echo "."
echo "quit"
echo 
echo 
sleep 2

