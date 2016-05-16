package br.ufrn.palitinhos.dominio;

import java.io.Serializable;

public class Aposta implements Serializable {

	private static final long serialVersionUID = -4733497404513143331L;

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
