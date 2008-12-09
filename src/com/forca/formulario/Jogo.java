package com.forca.formulario;

import com.forca.cliente.Cliente;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Jogo extends JFrame implements ActionListener{
	public static JFrame config;
	public static JLabel nome;
	public static JLabel ip;
	public static JLabel nivel;
	
	public static JTextField txNome;
	public static JTextField txIp;
	public static JTextField txNivel;
	
	//public static Configurar cfg;
	
	public static JButton start; 
	private static final long serialVersionUID = 1L;

	private JLabel label;
	private JTextField caixa;
	private JButton botao;
	private JLabel dica;
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public JLabel imagem;
	public JFrame frame; 
	//Image img0 = tk.getImage(getClass().getResource("/icones/handset.jpg"));  
	public static int flag = -1;
	
	public Cliente c;
	
	public Jogo(Cliente _c){
		this.c = _c;
		this.setSize(250,140);
		this.setLocation(0, 375);
		this.setTitle("Jogo da Forca");
		this.setLayout(new GridLayout(2,1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		label = new JLabel("Jogo da Forca");
		dica = new JLabel();
		caixa = new JTextField();
		caixa.setColumns(1);
		botao = new JButton("Enviar");
		botao.addActionListener(this);
		imagem = new JLabel();
		this.add(label);
		this.add(dica);
		this.add(caixa);
		this.add(botao);
		
		
		frame = new JFrame();
		frame.setLayout(new GridLayout(1,1));
		frame.setSize(250,375);
		frame.setLocation(0,0);
		frame.add(imagem);
		//frame.setVisible(true);
		//this.setVisible(true);
		this.requestFocus();
		this.repaint();
		System.out.println("formulario setado");
		
		setupConfig();
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==botao) {
			String letra = getCaixa().getText();
			if(letra.length()>0) {
				Cliente.ps.println(Cliente.TESTA_LETRA+"-"+letra);
			} else {
				JOptionPane.showMessageDialog(null, "Digite uma letra");
			}
		} else {
			//Cliente.startarJogo();
			Cliente.SERVER_IP = txIp.getText();
			Cliente.GAME_LEVEL = txNivel.getText();
			Cliente.CLIENT_NAME = txNome.getText();
			
			config.setVisible(false);
			frame.setVisible(true);
			this.setVisible(true);
			flag = 1;
			
		}
			//System.out.println("click do botao");
	}

	 public void setupConfig(){
	    	config = new JFrame();
	    	config.setSize(400,140);
	    	config.setLocation(0, 375);
	    	config.setTitle("Jogo da Forca");
	    	config.setLayout(new GridLayout(4,1));
			//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			nome = new JLabel("Digite seu nome ");
			ip = new JLabel("Digite o IP do Servidor");
			nivel = new JLabel("Digite o nivel (1, 2 ou 3)");
			txNome = new JTextField();
			txNome.setText("Daniel");
			txIp = new JTextField();
			txIp.setText("192.168.1.9");
			txNivel = new JTextField();
			txNivel.setText("1");
			
			start = new JButton("Começar");
			start.addActionListener(this);
			
			config.add(nome);
			config.add(txNome);
			config.add(ip);
			config.add(txIp);
			config.add(nivel);
			config.add(txNivel);
			config.add(new JLabel(""));
			config.add(start);
			
			config.setVisible(true);
	    }

	
	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JTextField getCaixa() {
		return caixa;
	}

	public void setCaixa(JTextField caixa) {
		this.caixa = caixa;
	}

	public JButton getBotao() {
		return botao;
	}

	public void setBotao(JButton botao) {
		this.botao = botao;
	}

	public JLabel getDica() {
		return dica;
	}

	public void setDica(JLabel dica) {
		this.dica = dica;
	}

	public void setImagem(int erros) {
		 System.out.println("erros -> "+erros);
		 
		 Image img = tk.getImage(getClass().getResource(Integer.toString(erros)+".jpg"));      
		 ImageIcon icone = new ImageIcon(img);
		 imagem.setIcon(icone);
		 frame.repaint();
		// TODO Auto-generated method stub
		
	}
	
}
