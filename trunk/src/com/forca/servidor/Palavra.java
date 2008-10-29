package com.forca.servidor;

public class Palavra {

	public Palavra(String _dica, String _palavra){
		setDica(_dica);
		setPalavra(_palavra);
	}
	
	private String palavra;
	private String dica;
	public String getPalavra() {
		return palavra;
	}
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	public String getDica() {
		return dica;
	}
	public void setDica(String dica) {
		this.dica = dica;
	}
	
}
