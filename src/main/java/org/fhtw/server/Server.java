package org.fhtw.server;

import org.fhtw.dao.SavableImplementation;
import org.fhtw.thread.ClientHandler;
import org.fhtw.validator.MessageValidator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            // server is listening on port 1234
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
            // running infinite loop for getting client request
            while (true) {
                // socket object to receive incoming client requests
                Socket client = server.accept();
                // Displaying that new client is connected to server
                System.out.println("New client connected: " + client.getInetAddress().getHostAddress());
                // create a new thread object
                ClientHandler clientSock = new ClientHandler(client, new SavableImplementation(), new MessageValidator());
                // This thread will handle the client separately
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
