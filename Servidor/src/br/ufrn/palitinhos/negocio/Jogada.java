package br.ufrn.palitinhos.negocio;

import java.rmi.RemoteException;

/**
 * 
 */

/**
 * @author joao
 *
 */
public class Jogada implements IJogada {
	
	private static final long serialVersionUID = 12345113265432345L;
	
	protected Jogada() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void realizarJogada(int palpite, int quantPalitosJogados, int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean esperar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String divulgarResultado() {
		// TODO Auto-generated method stub
		return null;
	}

}
