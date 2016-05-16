package br.ufrn.palitinhos.negocio;

import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.dominio.Resposta;

import java.rmi.RemoteException;

public interface SalaInterface {

	Jogador proximoJogador();

	Resposta esperar(int id);

    void realizarJogada(Aposta aposta, int quantPalitosJogados);

	void inscreverJogador(Jogador jogador);

	String divulgarResultado(int idJogador);

}