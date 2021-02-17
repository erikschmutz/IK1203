package tcpclient;

import java.net.*;
import java.io.*;

public class TCPClient {

    public static String askServer(String hostname, int port, String toServer) throws IOException {

        if (toServer == null)
            toServer = "";

        // Initializes a socket.
        Socket echoSocket = new Socket(hostname, port);
        // Creates a pipe fromt the socket, We will use this later on when
        // listening to the current network.
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

        // Creeates a stream from the to listen to incomming data.
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        // Create a stream for the toServer input. We convert it to byte firstly.
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(toServer.getBytes())));

        // This is the current user input. We keep track of it to simply
        // access the stream.
        String userInput;

        // We create a builder which will be the return value of the function
        StringBuilder output = new StringBuilder("");

        // Continues for ever until null is sent.
        while (true) {
            // Checks the lastest fromt he input
            userInput = stdIn.readLine();
            // We emit it into the output.
            out.println(userInput);
            // Reads in the next line from the server.
            String nl = in.readLine();
            // Checks for null
            if (nl == null)
                break;
            // Appends on the output,
            output.append(nl);

        }

        // IMPORTANT. CLOSE Socker
        echoSocket.close();
        return output.toString();

    }

    public static String askServer(String hostname, int port) throws IOException {
        return TCPClient.askServer(hostname, port, null);
    }
}
