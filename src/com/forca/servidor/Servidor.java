package com.forca.servidor;
import java.net.*;
import java.util.Vector;
import java.io.*;
// Listens for connection on port 6500,
// receives messages and echoes them back
public class Servidor {
	
	public static Vector<String> palavras;
	
	public static void initPalavras(){
		palavras = new Vector<String>();
		palavras.add("Caju");
		palavras.add("Policia");
		palavras.add("Maracaipe");
		palavras.add("hortolandia");
		palavras.add("higienopolis");
		palavras.add("paraiba");
		palavras.add("astrolabio");
	}
	
    public static void main(String args[]) throws Exception {
        ServerSocket servidor = new ServerSocket(6500);
        Socket socket = null;
        while(true) {
            socket = servidor.accept();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintStream ps = new PrintStream(
                    socket.getOutputStream());
            ps.println("fuck->"+br.readLine()); // Echo input to output
            socket.close();
        }
    }
    
   
}