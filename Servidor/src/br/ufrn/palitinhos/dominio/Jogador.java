package br.ufrn.palitinhos.dominio;

import java.io.Serializable;

public class Jogador implements Serializable {

	private static final long serialVersionUID = -3165665194765081410L;

	private int quantPalitos;
	private String nome;
	private int id;
	private boolean estaJogando;

	public Jogador(String nome, int id) {
		super();
		this.id = id;
		this.quantPalitos = 1;
		this.nome = nome;
		this.estaJogando = true;
	}
	
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public boolean isEstaJogando() {
		return estaJogando;
	}


	public void setEstaJogando(boolean estaJogando) {
		this.estaJogando = estaJogando;
	}

	public int getQuantPalitos() {
		return quantPalitos;
	}

	public void decrementarQuantPalitos() {
		quantPalitos --;
		if(quantPalitos == 0){
			estaJogando = false; //ganhou- muda status
		}
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

	@Override
    public String toString() {
        return "====== JOGADOR ======\nId: " + this.id + " Nome: " + this.nome;
    }
}
