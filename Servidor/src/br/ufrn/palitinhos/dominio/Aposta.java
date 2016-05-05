package br.ufrn.palitinhos.dominio;

public class Aposta {
	
	private int id;
	private int quantPalitos;
		
	public Aposta(int id, int quantPalitos) {
		super();
		this.id = id;
		this.quantPalitos = quantPalitos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantPalitos() {
		return quantPalitos;
	}
	public void setQuantPalitos(int quantPalitos) {
		this.quantPalitos = quantPalitos;
	}
	

}
