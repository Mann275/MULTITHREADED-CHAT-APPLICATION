import java.io.*;
import java.net.*;
import java.util.*;

// Server class to accept client connections and broadcast messages
public class ChatServer {
    private static final int PORT = 12345;

    // Set to store all connected client handlers
    private static Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            // Continuously wait for client connections
            while (true) {
                Socket clientSocket = serverSocket.accept(); // New client connected
                System.out.println("New client connected: " + clientSocket);

                // Start a new thread for each client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    // Send a message to all connected clients except the sender
    public static void broadcast(String message, ClientHandler excludeUser) {
        synchronized (clientHandlers) {
            for (ClientHandler client : clientHandlers) {
                if (client != excludeUser) {
                    client.sendMessage(message);
                }
            }
        }
    }

    // Remove a client from the active client list
    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }
}

// Class to handle messages from a specific client
class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private String clientName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out = new PrintWriter(socket.getOutputStream(), true);

            // Ask the client for their name
            out.println("Enter your name:");
            clientName = in.readLine();

            // Notify all clients that a new user has joined
            ChatServer.broadcast(clientName + " has joined the chat!", this);

            String message;
            // Keep receiving messages from the client while connected
            while ((message = in.readLine()) != null) {
                ChatServer.broadcast(clientName + ": " + message, this);
            }
        } catch (IOException e) {
            System.err.println("Connection error with client " + clientName);
        } finally {
            // Client disconnected
            ChatServer.removeClient(this);
            ChatServer.broadcast(clientName + " has left the chat.", this);

            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket.");
            }
        }
    }

    // Send a message to this client
    public void sendMessage(String message) {
        out.println(message);
    }
}
