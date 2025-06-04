import java.io.*;
import java.net.*;

// Client class that connects to server and sends/receives messages
public class ChatClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        ) {
            // Thread to continuously read messages from server
            Thread readThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });
            readThread.start();

            // Main thread reads user input and sends it to the server
            String input;
            while ((input = userInput.readLine()) != null) {
                out.println(input);
            }
        } catch (IOException e) {
            System.err.println("Unable to connect to server: " + e.getMessage());
        }
    }
}
