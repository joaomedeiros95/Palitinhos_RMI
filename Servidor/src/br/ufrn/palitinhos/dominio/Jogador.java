package br.ufrn.palitinhos.dominio;

public class Jogador {

	private int quantPalitos;
	private String nome;
	private int id;
	
	public Jogador(String nome, int id) {
		super();
		this.id = id;
		this.quantPalitos = 3;
		this.nome = nome;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
