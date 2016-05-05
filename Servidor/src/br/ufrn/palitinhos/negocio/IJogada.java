package br.ufrn.palitinhos.negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IJogada extends Remote {
	void realizarJogada(int palpite, int quantPalitosJogados, int id) throws RemoteException;
	boolean esperar(int id);
	String divulgarResultado();

}
