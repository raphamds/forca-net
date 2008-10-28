package com.forca.cliente;
import java.net.*;
import java.util.Scanner;
import java.io.*;

import com.forca.servidor.Servidor;

public class Cliente {

	public static String QUERO_NOVA_PALAVRA = "0";
	public static String TESTA_LETRA = "1";
	
	
	
    public static void main(String args[]) throws Exception {
    	String host = "10.0.4.1";
        Socket socket = new Socket(host, 6500);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println(QUERO_NOVA_PALAVRA);
        while(true){
        	String line = br.readLine();
        	//System.out.println("RESPOSTA DO SERVIDOR "+line);
        	if(line.equals(Servidor.GAME_OVER)){
        		break;
        	}
        	if(line.equals(Servidor.PALAVRA_SETADA) || line.equals(Servidor.LETRA_NAO_ENCONTRADA)){
        		if(line.equals(Servidor.LETRA_NAO_ENCONTRADA)){
        			System.out.println("LETRA NAO ENCONTRADA");
        		}
        		System.out.println("DIGITE A LETRA");
        		Scanner scanner = new Scanner (System.in);
        		String letra = scanner.next();
        		ps.println(TESTA_LETRA+"-"+letra);
        	}
        	if(line.charAt(0) == Servidor.LETRA_ENCONTRADA.toCharArray()[0]){
        		System.out.println("LETRA ENCONTRADA");
        		System.out.println(line.substring(2));
        		System.out.println("DIGITE A LETRA");
        		Scanner scanner = new Scanner (System.in);
        		String letra = scanner.next();
        		ps.println(TESTA_LETRA+"-"+letra);
        		
        	}
        }
        socket.close();
        System.out.println("GAME OVER");
    }
   
    
}