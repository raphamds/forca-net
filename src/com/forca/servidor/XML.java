package com.forca.servidor;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XML {

	String nivel;
	String palavra;
	String dica;
	
	public XML(String n)
	{
		
		this.nivel = n;
		
		File arquivo = new File("palavras.xml");
		
		SAXBuilder construtor = new SAXBuilder();
		
		Document documento = null;
		
		try {
			documento = construtor.build(arquivo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "erro ao montar documento xml");
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		Element elementos = documento.getRootElement();
		
		List lista = elementos.getChildren();
		Iterator interador = lista.iterator();
		
		Element elementosFilhos = null;
		
		while(interador.hasNext())
		{
			
			elementosFilhos = (Element) interador.next();
			
			
			
			if(elementosFilhos.getAttributeValue("nivel").equals(this.nivel) )
			{
				
				break;
			
			}
			
			
		}
		
		lista = elementosFilhos.getChildren();
		
		interador = lista.iterator();
		
		int quantidade = lista.size();
		
		Random rnd = new Random();
		
		int index = 1 + rnd.nextInt(quantidade);
		int i;
		
		for(i = 0; i < index-1; i++)
		{
			
			interador.next();
		
		}
		
		elementosFilhos = (Element) interador.next();
		
		this.palavra = elementosFilhos.getText().trim();
		this.dica = elementosFilhos.getAttributeValue("dica");
		
	}
	
	public String GetPalavra()
	{
		
		return this.palavra;
	
	}
	
	public String GetDica()
	{
		
		return this.dica;
	
	}
	
}
