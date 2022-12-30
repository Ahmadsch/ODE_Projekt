package org.fhtw.thread;

import org.fhtw.dao.SavableImplementation;
import org.fhtw.dao.Savable;
import org.fhtw.entity.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Savable savableImplem;

    public ClientHandler(Socket socket, Savable savableImplem) {
        this.savableImplem = savableImplem;
        this.clientSocket = socket;
    }

    public String[] tokenizeString(String line) {
        String DELIM = ";";
        return line.split(Pattern.quote(DELIM));
    }

    public void run() {
        PrintWriter clientOutputStream = null;
        BufferedReader clientInputStream = null;
        try {
            // get the outputstream of client
            clientOutputStream = new PrintWriter(clientSocket.getOutputStream(), true);

            // get the inputstream of client
            clientInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            do {
                line = clientInputStream.readLine();
                String[] clientMessage = tokenizeString(line);
                Message message = new Message(Integer.parseInt(clientMessage[0]),clientMessage[1],clientMessage[2],clientMessage[3],clientMessage[4]);
                savableImplem.writeDataIntoFile(message);
            } while (!line.equals("exit"));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (clientOutputStream != null) {
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

