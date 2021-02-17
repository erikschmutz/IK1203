echo "_________________"
echo "un.org"

## Retrives the head from the URL and port. Then 
## only prints the value which starts with the word Server
## Does this for all website. If no Server is found on in
## the response the "No server found" is deployed instead
./head.sh www.un.org 80 | telnet | grep Server || echo "No server found"
echo "_________________"
echo "stockholm.se"
./head.sh www.stockholm.se 80 | telnet| grep Server || echo "No server found"
echo "_________________"
echo "google.se"
./head.sh www.google.se 80 | telnet| grep Server || echo "No server found"
echo "_________________"
echo "time.com"
./head.sh www.time.com 80 | telnet | grep Server || echo "No serverfound"
echo "_________________"