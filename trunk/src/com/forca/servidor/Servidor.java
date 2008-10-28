package com.forca.servidor;
import com.forca.cliente.*;
import java.net.*;
import java.util.Random;
import java.util.Vector;
import java.io.*;
// Listens for connection on port 6500,
// receives messages and echoes them back
public class Servidor {
	
	public static Vector<String> palavras;
	public static String palavraAtual;
	
	public static String PALAVRA_SETADA = "0";
	public static String GAME_OVER = "1";
	
	public static String LETRA_ENCONTRADA = "2";
	public static String LETRA_NAO_ENCONTRADA = "3";
	
	public static Vector<String> letrasDigitadas;
	
	public static void initPalavras(){
		letrasDigitadas = new Vector<String>();
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
    	initPalavras();
        ServerSocket servidor = new ServerSocket(6500);
        Socket socket = null;
        Random rnd = new Random();
        while(true) {
            socket = servidor.accept();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintStream ps = new PrintStream(
                    socket.getOutputStream());
            String line = br.readLine();
            if(line.equals(Cliente.QUERO_NOVA_PALAVRA)){
            	palavraAtual = palavras.elementAt(rnd.nextInt(palavras.size()-1));
            	ps.println(PALAVRA_SETADA);
            }
            if(line.charAt(0) == Cliente.TESTA_LETRA.toCharArray()[0]){
            	String letra = line.substring(2);
            	if(palavraAtual.indexOf(letra)==-1){
            		ps.println(LETRA_NAO_ENCONTRADA);
            	} else {
            		letrasDigitadas.add(letra);
            		ps.println(LETRA_ENCONTRADA+getMostruario());
            	}
            }
            //ps.println("fuck->"+br.readLine()); // Echo input to output
            socket.close();
        }
    }
    
    public static String getMostruario(){
    	String result = "";
    	for(int x =0; x<palavraAtual.length(); x++){
    		if(letrasDigitadas.indexOf(palavraAtual.substring(x,1))!=-1){
    			result += palavraAtual.substring(x,1);
    		} else {
    			result += "_";
    		}
    	}
    	return result;
    }
   
}