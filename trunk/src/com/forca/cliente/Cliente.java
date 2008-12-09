package com.forca.cliente;
import java.net.*;
//import java.util.Scanner;
import java.io.*;

import javax.swing.JOptionPane;

import com.forca.formulario.Jogo;
import com.forca.servidor.Servidor;

public class Cliente {
	public static String SERVER_IP = "192.168.1.100";
	public static String CLIENT_NAME;
	public static String GAME_LEVEL;
	
	
	public static String QUERO_NOVA_PALAVRA = "0";
	public static String TESTA_LETRA = "1";
	public static int erros = 0;
	public static PrintStream ps;
	public static Jogo jogo;

	public static void startarJogo(){
		String host = Cliente.SERVER_IP;
        Socket socket = null;
		try {
			socket = new Socket(host, 6500);
			
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        try {
			ps = new PrintStream(socket.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        ps.println(QUERO_NOVA_PALAVRA);
        ps.println(GAME_LEVEL);
        
        while(true){
        	String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	//System.out.println("RESPOSTA DO SERVIDOR "+line);
			
			if(line.equals(Servidor.VICTORY)){
        		erros = 0;        		
        		int outravez = JOptionPane.showConfirmDialog(null, "PARABENS, VOCE VENCEU !! Jogar outra vez ?");
    			if(outravez == JOptionPane.OK_OPTION){
    				ps.println(QUERO_NOVA_PALAVRA);
    				ps.println(GAME_LEVEL);
    			}
        	}
			
        	if(line.equals(Servidor.GAME_OVER)){
        		jogo.setImagem(erros+1);
        		erros = 0;        		
        		int outravez = JOptionPane.showConfirmDialog(null, "GAME OVER !! Jogar outra vez ?");
    			if(outravez == JOptionPane.OK_OPTION){
    				ps.println(QUERO_NOVA_PALAVRA);
    				ps.println(GAME_LEVEL);
    			}else {
    				break;        				
    			}
        	}
        	if(line.equals(Servidor.PALAVRA_SETADA) || line.equals(Servidor.LETRA_NAO_ENCONTRADA)){
        		if(line.equals(Servidor.LETRA_NAO_ENCONTRADA)){
        			//System.out.println("LETRA NAO ENCONTRADA");
        			erros += 1;
        			jogo.setImagem(erros);
        			//JOptionPane.showMessageDialog(null, "LETRA NAO ENCONTRADA");
        			
        		}
        		if(line.equals(Servidor.PALAVRA_SETADA)){
        			try {
						jogo.getDica().setText(br.readLine().toUpperCase());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
        			try {
						jogo.getLabel().setText(br.readLine());
					} catch (IOException e) {
						e.printStackTrace();
					}
        			jogo.setImagem(0);
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
        			jogo.setImagem(erros+1);
        			erros = 0;
        			int outravez = JOptionPane.showConfirmDialog(null, "GAME OVER !! Jogar outra vez ?");
        			if(outravez == JOptionPane.OK_OPTION){
        				ps.println(QUERO_NOVA_PALAVRA);
        				ps.println(GAME_LEVEL);
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
	
    public static void main(String args[]) throws Exception {
    	Configurar cfg = new Configurar();
    	jogo = new Jogo();
    	jogo.setVisible(true);
    	cfg.setVisible(true);
    	
		//Cliente.configOk();
    }
   
    public static void configOk(){
    	jogo.requestFocus();
    	jogo.paint(jogo.getGraphics());
    	jogo.repaint();
    	//Configurar.sair();
    	startarJogo();
    }
    
    
}