package com.forca.servidor;
import com.forca.cliente.*;
import java.net.*;
import java.util.Random;
import java.util.Vector;
import java.io.*;


public class Servidor {
	
	public static int erros = 0;
	public static int QTD_MAX_ERROS = 5;
	
	public static Vector<Palavra> palavras;
	public static Palavra palavraAtual;
	
	public static String PALAVRA_SETADA = "0";
	public static String GAME_OVER = "1";
	
	public static String LETRA_ENCONTRADA = "2";
	public static String LETRA_NAO_ENCONTRADA = "3";
	
	public static Vector<String> letrasDigitadas;
	
	public static void initPalavras(){
		letrasDigitadas = new Vector<String>();
		palavras = new Vector<Palavra>();
		palavras.add(new Palavra("fruta", "CAJU"));
		palavras.add(new Palavra("fruta", "MORANGO"));
		palavras.add(new Palavra("fruta", "KIWI"));
		palavras.add(new Palavra("fruta", "FRUTAPAO"));
		palavras.add(new Palavra("animal", "MOSCA"));
		palavras.add(new Palavra("animal", "GATO"));
		palavras.add(new Palavra("animal", "CAMELO"));
		palavras.add(new Palavra("animal", "MINHOCA"));
		palavras.add(new Palavra("animal", "TAMANDUA"));
		palavras.add(new Palavra("cidade", "RECIFE"));
		palavras.add(new Palavra("cidade", "PARIS"));
		palavras.add(new Palavra("cidade", "LONDRES"));
		palavras.add(new Palavra("cidade", "MOSCOW"));
		palavras.add(new Palavra("cidade", "LISBOA"));
		palavras.add(new Palavra("cidade", "CONDADO"));
		palavras.add(new Palavra("cidade", "SALGUEIRO"));
		palavras.add(new Palavra("fruta", "SIRIGUELA"));
		palavras.add(new Palavra("animal", "BURRO"));
		palavras.add(new Palavra("profissao", "DENTISTA"));
		palavras.add(new Palavra("profissao", "MORDOMO"));
		palavras.add(new Palavra("profissao", "PROGRAMADOR"));
		palavras.add(new Palavra("profissao", "DESIGNER"));
		palavras.add(new Palavra("profissao", "JARDINEIRO"));
		palavras.add(new Palavra("profissao", "ARQUITETO"));
		palavras.add(new Palavra("profissao", "ANALISTA"));
	}
	
	public static void init(){
		erros = 0;
		letrasDigitadas = new Vector<String>();
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
                	init();
                	palavraAtual = palavras.elementAt(rnd.nextInt(palavras.size()-1));
                	//System.out.println("PALAVRA SETADA ->"+palavraAtual);
                	ps.println(PALAVRA_SETADA);
                	ps.println(palavraAtual.getDica());
                	ps.println(getMostruario());
                }
            	 if(line.charAt(0) == Cliente.TESTA_LETRA.toCharArray()[0]){
                 	String letra = line.substring(2).toUpperCase();
                 	//System.out.println("recebido letra "+letra);
                 	if(palavraAtual.getPalavra().indexOf(letra)==-1){
                 		erros++;
                 		if(erros<QTD_MAX_ERROS){
                 			ps.println(LETRA_NAO_ENCONTRADA);                 			
                 		} else {
                 			ps.println(GAME_OVER);
                 		}
                 		

                 		//System.out.println(letra+" nao encontrada em "+palavraAtual);
                 	} else {
                 		letrasDigitadas.add(letra);
                 		String mostruario = "-"+getMostruario();
                 		if(letrasDigitadas.size()==palavraAtual.getPalavra().length() && letrasDigitadas.size()>0){
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
    	String result = "";
    	for(int x =1; x<palavraAtual.getPalavra().length()+1; x++){
    		String letraAtual = palavraAtual.getPalavra().substring(x-1,x);
    		//System.out.println("pesquisando "+letraAtual);
    		if(letrasDigitadas.indexOf(letraAtual)!=-1){
    			result += letraAtual;
    		} else {
    			result += "_ ";
    		}
    	}
    	return  result;
    }
   
}