package br.ufrn.palitinhos.negocio;

import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.dominio.Jogador;

public interface SalaInterface {

	Jogador proximoJogador();

	boolean esperar(int id);

    void realizarJogada(Aposta aposta, int quantPalitosJogados);

	void inscreverJogador(Jogador jogador);

}