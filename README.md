# MULTITHREADED-CHAT-APPLICATION

**COMPANY:** CODTECH IT SOLUTIONS  
**NAME:** PATEL MANN  
**INTERN ID:** CT04DM113  
**DOMAIN:** JAVA PROGRAMMING  
**DURATION:** 4 WEEKS  
**MENTOR:** NEELA SANTOSH  

---

## TASK DESCRIPTION

This Java project demonstrates a **multithreaded client-server chat application** using Java Sockets. It enables real-time communication between multiple clients via a central server.

### Key concepts covered:

- Use of `ServerSocket` and `Socket` classes for TCP connections  
- Handling multiple clients simultaneously using multithreading  
- Broadcasting messages from one client to all others  
- Clean disconnection and thread-safe message handling  
- Console-based communication without GUI  

This application showcases Java's capability to build basic networked systems.

---

## HOW TO RUN

### 1. Compile

Open a terminal and navigate to the project directory containing the `.java` files:

```bash
javac ChatServer.java ChatClient.java
```

### 2. Run the Server

```bash
java ChatServer
```

> Leave this terminal open — it will handle all client messages.

### 3. Run Clients (in separate terminals)

```bash
java ChatClient
```

- Enter a name when prompted.  
- Type messages to broadcast to all connected clients.  
- Repeat this step to simulate multiple clients in different terminal windows.

---

## OUTPUT

1.
   ![Server and Client Connected](https://github.com/user-attachments/assets/7f14376c-205c-44c4-bbf0-4e97e66bb2c2)  
2.
   ![Message Broadcast](https://github.com/user-attachments/assets/19b871c7-80da-418f-80f3-f3ad7e8b262d)

---

## CONCLUSION

This project provided practical experience in Java network programming using sockets and multithreading. It is a foundational exercise for understanding how real-time systems work, and can be extended into more advanced messaging platforms with features like private messaging, chat rooms, or GUI integration.

---
