import java.net.*;
import java.io.*;

import tcpclient.TCPClient;



class NetThread implements Runnable {
  BufferedReader in;
  PrintWriter out;

  Socket socket;

  public NetThread(Socket socket, int id) throws IOException{
    System.out.println("Im just a simple thread with the id "+ id);
    this.socket = socket;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream());

  }

  public void run() {
    try {

    
      String line;

      // While the row is not empty or null we can still read in the
      // request. which we then sends back to the client.
      StringBuilder stringB = new StringBuilder();

      while ((line = in.readLine()) != null && (line.length() != 0)) {
        stringB.append(line);
      }

      String request = stringB.toString();

      System.out.println(request);

      try {

        if (request.indexOf("HTTP/1.1") == -1 || request.indexOf("GET") == -1) {
          throw new Exception();
        }

        String hostName = (ConcHTTPAsk.GetKeyFromParams(request, "hostname"));
        String hostPort = (ConcHTTPAsk.GetKeyFromParams(request, "port"));
        String toServer = (ConcHTTPAsk.GetKeyFromParams(request, "string"));

        String response;

        if (hostName == null || hostPort == null) {
          throw new Exception();
        }

        try {
          if (toServer != null) {
            response = TCPClient.askServer(hostName, Integer.parseInt(hostPort), toServer);
          } else {
            response = TCPClient.askServer(hostName, Integer.parseInt(hostPort));
          }

          // Sends the headers to the client
          out.println("HTTP/1.0 200 OK");
          out.println("Content-Type: text/html; charset=utf-8");
          out.println("Server: MINISERVER");

          // Empty row marks the end of the headers
          out.println("");
          // Here we sent the body
          out.println(response);
        } catch (UnknownHostException error) {
          // Sends the headers to the client
          out.println("HTTP/1.0 404 Not Found");
          out.println("Content-Type: text/html; charset=utf-8");
          out.println("Server: MINISERVER");

          // Empty row marks the end of the headers
          out.println("");
          // Here we sent the body
          out.println("404");
        }

        // Here we handle the URL request and
        // there for reads out the url params
        // from the request.

      } catch (Exception e) {

        System.out.println("Hello");
        // Sends the headers to the client
        out.println("HTTP/1.0 400 Bad Request");
        out.println("Content-Type: text/html; charset=utf-8");
        out.println("Server: MINISERVER");

        // Empty row marks the end of the headers
        out.println("");
        // Here we sent the body
        out.println("400");
      }
      out.close();
      socket.close();

      System.out.println("Connection closed");
    } catch (IOException ex) {
      System.out.println("Server exception: " + ex.getMessage());
      ex.printStackTrace();

      try{
        out.close();
        socket.close();
      }catch (IOException exi) {

      }

    }
  }
}

public class ConcHTTPAsk {
  static String GetKeyFromParams(String params, String key) {
    String[] splittedArrayBecauseJavaIsBroken = params.split(key + "=", 2);
    // Checks that the the length of the array
    // to ensure we have both the values we require.
    if (splittedArrayBecauseJavaIsBroken.length < 2) {
      return null;
    }
    String out = splittedArrayBecauseJavaIsBroken[1];
    if (out.indexOf("&") != -1) {
      return out.split("&")[0];
    }
    if (out.indexOf(" ") != -1) {
      return out.split(" ")[0];
    }
    return out;
  }

  public static void main(String[] args) {

    // Asserts that the arguments are in place.
    if (args.length < 1)
      throw new Error("No port argument...");
    // Retrives the port from the first argument.
    int port = Integer.parseInt(args[0]);

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Started...Server is listening on port " + port);

      int threadId = 0;
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("Accepted connection");
        Thread th = new Thread(new NetThread(socket, threadId++));
        th.start();
      }
    } catch (IOException ex) {
      System.out.println("Server exception: " + ex.getMessage());
      ex.printStackTrace();
    }
  }
}
