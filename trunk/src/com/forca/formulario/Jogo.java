package com.forca.formulario;

import com.forca.cliente.Cliente;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public Jogo(){
		this.setSize(250,125);
		this.setLocation(400, 300);
		this.setTitle("Jogo da Forca");
		this.setLayout(new GridLayout(3,1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		label = new JLabel("Jogo da Forca");
		dica = new JLabel();
		caixa = new JTextField();
		botao = new JButton("Enviar");
		botao.addActionListener(this);
		this.add(label);
		this.add(dica);
		this.add(caixa);
		this.add(botao);
		
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
	
}
