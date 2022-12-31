package org.fhtw.thread;


import org.fhtw.dao.Savable;
import org.fhtw.entity.Message;
import org.fhtw.validator.MessageValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Savable savableImpl;
    private final MessageValidator messageValidator;

    public ClientHandler(Socket socket, Savable savableImpl, MessageValidator messageValidator) {
        this.savableImpl = savableImpl;
        this.clientSocket = socket;
        this.messageValidator = messageValidator;
    }

    public Message tokenizeString(String line) {
        String[] result = line.split(";");
        if (result.length != 5)
            throw new IllegalArgumentException("Message has to have this form:  \"EmployeeID\";\"Name\";\"Task\";\"Date-from\";\"Date-to\"");
        return new Message(result[0].trim(), result[1].trim(), result[2].trim(), result[3].trim(), result[4].trim());
    }

    public void run() {
        PrintWriter clientOutputStream = null;
        BufferedReader clientInputStream = null;
        try {
            // get the output stream of client
            clientOutputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            // get the input stream of client
            clientInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            Message clientMessage;
            while ((line = clientInputStream.readLine()) != null) {
                if (line.equals("exit"))
                    break;
                try {
                    clientMessage = tokenizeString(line);
                    messageValidator.validateMessage(clientMessage);
                    savableImpl.writeDataIntoFile(clientMessage);
                    clientOutputStream.println("Added a new Entry");
                } catch (IllegalArgumentException | NullPointerException | IOException e) {
                    clientOutputStream.println(e);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientOutputStream != null) {
                    clientOutputStream.println("server shut down");
                    clientOutputStream.close();
                }
                if (clientInputStream != null) {
                    clientInputStream.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

