package br.ufrn.palitinhos.negocio;

import java.util.ArrayList;

import br.ufrn.palitinhos.dominio.Jogador;

public interface SalaInterface {

	ArrayList<Jogador> getJogadores();

	void setJogadores(ArrayList<Jogador> jogadores);

	void insertJogador(Jogador jogador);

	void deleteJogador(int id);

	Jogador proximoJogador();

}