package com.forca.servidor;
import com.forca.cliente.*;
import java.net.*;
import java.util.Random;
import java.util.Vector;
import java.io.*;


public class Servidor {
	
	public static int erros = 0;
	public static int QTD_MAX_ERROS = 4;
	
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
		palavras.add("CAJU");
		palavras.add("POLICIA");
		palavras.add("MARACAIPE");
		palavras.add("HORTOLANDIA");
		palavras.add("HIGIENOPOLIS");
		palavras.add("PARAIBA");
		palavras.add("ASTROLABIO");
	}
	
    public static void main(String args[]) throws Exception {
    	initPalavras();
        ServerSocket servidor = new ServerSocket(6500);
        Socket socket = null;
        Random rnd = new Random();
        //while(true) {
            socket = servidor.accept();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintStream ps = new PrintStream(
                    socket.getOutputStream());
            System.out.println("SERVIDOR STARTADO");
            while(true){
            	String line = br.readLine();
                if(line.equals(Cliente.QUERO_NOVA_PALAVRA)){
                	palavraAtual = palavras.elementAt(rnd.nextInt(palavras.size()-1));
                	System.out.println("PALAVRA SETADA ->"+palavraAtual);
                	ps.println(PALAVRA_SETADA);
                }
            	 if(line.charAt(0) == Cliente.TESTA_LETRA.toCharArray()[0]){
                 	String letra = line.substring(2).toUpperCase();
                 	if(palavraAtual.indexOf(letra)==-1){
                 		erros++;
                 		if(erros<QTD_MAX_ERROS){
                 			ps.println(LETRA_NAO_ENCONTRADA);                 			
                 		} else {
                 			ps.println(GAME_OVER);
                 		}
                 		

                 		//System.out.println(letra+" nao encontrada em "+palavraAtual);
                 	} else {
                 		letrasDigitadas.add(letra);
                 		String mostruario = getMostruario();
                 		if(letrasDigitadas.size()==palavraAtual.length() && letrasDigitadas.size()>0){
                 			ps.println(GAME_OVER);
                 		} else {
                 			ps.println(LETRA_ENCONTRADA+mostruario);                 			
                 		}
                 		
                 		//System.out.println(letra+" encontrada em "+palavraAtual);
                 	}
                 }
            }
           
        //}
    }
    
    public static String getMostruario(){
    	String result = "-";
    	for(int x =1; x<palavraAtual.length()+1; x++){
    		String letraAtual = palavraAtual.substring(x-1,x);
    		//System.out.println("pesquisando "+letraAtual);
    		if(letrasDigitadas.indexOf(letraAtual)!=-1){
    			result += letraAtual;
    		} else {
    			result += "_";
    		}
    	}
    	return result;
    }
   
}