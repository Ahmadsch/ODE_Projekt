package org.fhtw.client;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    // driver code
    public static void main(String[] args) {
        // establish a connection by providing host and port number
        try (Socket socket = new Socket("localhost", 1234)) {
            // writing to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line;
            do {
                // reading from user
                line = sc.nextLine();
                // sending the user input to server
                out.println(line);
                out.flush();
            } while (!line.equals("exit"));
            // closing the scanner object
            sc.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
