echo Sending Email
## arg 1 is the sender 
## arg 2 is the reciver
## arg 3 is the body
./smtp.sh $1 $2 $3 | telnet 