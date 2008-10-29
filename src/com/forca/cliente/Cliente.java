package com.forca.cliente;
import java.net.*;
import java.util.Scanner;
import java.io.*;

import javax.swing.JOptionPane;

import com.forca.formulario.Jogo;
import com.forca.servidor.Servidor;

public class Cliente {

	public static String QUERO_NOVA_PALAVRA = "0";
	public static String TESTA_LETRA = "1";
	
	public static PrintStream ps;
	
    public static void main(String args[]) throws Exception {
    	Jogo jogo = new Jogo();
    	String host = "10.0.4.1";
        Socket socket = new Socket(host, 6500);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        ps = new PrintStream(socket.getOutputStream());
        ps.println(QUERO_NOVA_PALAVRA);
        jogo.setVisible(true);
        while(true){
        	String line = br.readLine();
        	//System.out.println("RESPOSTA DO SERVIDOR "+line);
        	if(line.equals(Servidor.GAME_OVER)){
        		int outravez = JOptionPane.showConfirmDialog(null, "GAME OVER !! Jogar outra vez ?");
    			if(outravez == JOptionPane.OK_OPTION){
    				ps.println(QUERO_NOVA_PALAVRA);
    			}else {
    				break;        				
    			}
        	}
        	if(line.equals(Servidor.PALAVRA_SETADA) || line.equals(Servidor.LETRA_NAO_ENCONTRADA)){
        		if(line.equals(Servidor.LETRA_NAO_ENCONTRADA)){
        			//System.out.println("LETRA NAO ENCONTRADA");
        			JOptionPane.showMessageDialog(null, "LETRA NAO ENCONTRADA");
        		}
        		if(line.equals(Servidor.PALAVRA_SETADA)){
        			jogo.getDica().setText(br.readLine().toUpperCase());
        			jogo.getLabel().setText(br.readLine());
        		}
        		jogo.getCaixa().setText("");
        		jogo.getCaixa().requestFocus();
//        		System.out.println("DIGITE A LETRA");
//        		Scanner scanner = new Scanner (System.in);
//        		String letra = scanner.next();
//        		ps.println(TESTA_LETRA+"-"+letra);
        	}
        	if(line.charAt(0) == Servidor.LETRA_ENCONTRADA.toCharArray()[0]){
        		String exibir = line.substring(2);
        		jogo.getLabel().setText(exibir);
        		jogo.getCaixa().setText("");
        		jogo.getCaixa().requestFocus();
        		if(exibir.indexOf("_")==-1){
        			//JOptionPane.showMessageDialog(null, "GAME OVER !!!");
        			int outravez = JOptionPane.showConfirmDialog(null, "GAME OVER !! Jogar outra vez ?");
        			if(outravez == JOptionPane.OK_OPTION){
        				ps.println(QUERO_NOVA_PALAVRA);
        			}else {
        				break;        				
        			}
        			
        		}
//        		System.out.println("LETRA ENCONTRADA");
//        		System.out.println(line.substring(2));
//        		System.out.println("DIGITE A LETRA");
//        		Scanner scanner = new Scanner (System.in);
//        		String letra = scanner.next();
//        		ps.println(TESTA_LETRA+"-"+letra);
        		
        	}
        }
        try{
        	socket.close();        	
        }
        catch(Exception e){
        	
        }
        
        //System.out.println("GAME OVER");
    }
   
    
}