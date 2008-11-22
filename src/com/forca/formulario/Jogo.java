package com.forca.formulario;

import com.forca.cliente.Cliente;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Jogo extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JLabel label;
	private JTextField caixa;
	private JButton botao;
	private JLabel dica;
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public JLabel imagem;
	public JFrame frame; 
	//Image img0 = tk.getImage(getClass().getResource("/icones/handset.jpg"));  
	
	public Jogo(){
		this.setSize(250,140);
		this.setLocation(0, 375);
		this.setTitle("Jogo da Forca");
		this.setLayout(new GridLayout(2,1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		label = new JLabel("Jogo da Forca");
		dica = new JLabel();
		caixa = new JTextField();
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
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
			String letra = getCaixa().getText();
			Cliente.ps.println(Cliente.TESTA_LETRA+"-"+letra);
			//System.out.println("click do botao");
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
		 //remove(imagem);
		 imagem.setIcon(icone);
		 //frame.add(imagem);
		 //frame.setVisible(true);
		 //this.add(imagem);
		 frame.repaint();
		// TODO Auto-generated method stub
		
	}
	
}
