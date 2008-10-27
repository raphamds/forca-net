package com.forca.cliente;
import java.net.*;
import java.io.*;
// Connects to port 6500 of specified host,
// sends the message and prints the reply
public class Cliente {
    // Run as: java EchoClient hostname message
    public static void main(String args[]) throws Exception {
    	String host = "10.0.4.1";
    	String texto = "rato roeu a roupa do rei de roma!";
        Socket socket = new Socket(host, 6500);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println(texto); // Write message to socket
        // Print response from server
        System.out.println("Received: " + br.readLine());
        socket.close();
    }
}