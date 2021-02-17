package tcpclient;

import java.net.*;
import java.io.*;

public class TCPClient {

    public static String askServer(String hostname, int port, String toServer) throws IOException {

        if (toServer == null)
            toServer = "";
        Socket echoSocket = new Socket(hostname, port);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(toServer.getBytes())));
        String userInput;
        StringBuilder output = new StringBuilder("");

        while (true) {
            userInput = stdIn.readLine();
            out.println(userInput);
            String nl = in.readLine();
            if (nl == null)
                break;
            output.append(nl);

        }

        echoSocket.close();
        return output.toString();

    }

    public static String askServer(String hostname, int port) throws IOException {
        return TCPClient.askServer(hostname, port, null);
    }
}
