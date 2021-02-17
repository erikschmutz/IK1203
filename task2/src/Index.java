import java.io.*;
import java.net.*;
import java.util.Date;

public class Index {
  public static void main(String[] args) throws IOException {

    // Asserts that the arguments are in place.
    if (args.length < 1)
      throw new Error("No port argument...");
    // Retrives the port from the first argument.
    int port = Integer.parseInt(args[0]);

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Server is listening on port " + port);

      while (true) {
        Socket socket = serverSocket.accept();

        System.out.println("New client connected");

        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(socket.getOutputStream());

        // Sends the headers to the client
        out.println("HTTP/1.0 200 OK");
        out.println("Content-Type: text/plain; charset=utf-8");
        out.println("Server: MINISERVER");

        // Empty row marks the end of the headers
        out.println("");

        // While the row is not empty or null we can still read in the
        // request. which we then sends back to the client.
        while ((line = in.readLine()) != null && (line.length() != 0)) {
          out.println(line);
        }

        out.close();
        socket.close();

        System.out.println("Connection closed");
      }

    } catch (IOException ex) {
      System.out.println("Server exception: " + ex.getMessage());
      ex.printStackTrace();
    }
  }
}
