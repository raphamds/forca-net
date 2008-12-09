package com.forca.cliente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Configurar extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	public JLabel nome;
	public JLabel ip;
	public JLabel nivel;
	
	public JTextField txNome;
	public JTextField txIp;
	public JTextField txNivel;
	
	public static Configurar cfg;
	
	public JButton start; 
	
	public Configurar(){
		this.setSize(400,140);
		this.setLocation(0, 375);
		this.setTitle("Jogo da Forca");
		this.setLayout(new GridLayout(4,1));
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		nome = new JLabel("Digite seu nome ");
		ip = new JLabel("Digite o IP do Servidor");
		nivel = new JLabel("Digite o nivel (1, 2 ou 3)");
		txNome = new JTextField();
		txNome.setText("Daniel");
		txIp = new JTextField();
		txIp.setText("192.168.1.100");
		txNivel = new JTextField();
		txNivel.setText("1");
		
		start = new JButton("Começar");
		//start.addActionListener(this);
		
		this.add(nome);
		this.add(txNome);
		this.add(ip);
		this.add(txIp);
		this.add(nivel);
		this.add(txNivel);
		this.add(new JLabel(""));
		this.add(start);
		//setVisible(true);
	}
	

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if(txNome.getText().length()>0 && txIp.getText().length()>0 && txNivel.getText().length()>0){
			Cliente.CLIENT_NAME = txNome.getText();
			Cliente.GAME_LEVEL = txNivel.getText();
			Cliente.SERVER_IP = txIp.getText();
			//this.setVisible(false);
			this.dispose();
			//Cliente.configOk();
				try {
					//Cliente.main(null);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha corretamente os campos");
		}
	}

}
