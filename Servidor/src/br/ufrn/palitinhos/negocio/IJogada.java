package br.ufrn.palitinhos.negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ufrn.palitinhos.dominio.Aposta;

public interface IJogada extends Remote {
	void realizarJogada(Aposta aposta, int quantPalitosJogados);
	boolean esperar(int id);
	String divulgarResultado();

}
