package br.ufrn.palitinhos.negocio;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.excecao.InvalidApostaException;

/**
 * @author joao
 *
 */
public class Jogada implements IJogada {
		
	private int somaPalitos= 0;
	private List<Aposta> apostas = new ArrayList<Aposta>(); 
	
	protected Jogada()  {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void realizarJogada(Aposta aposta, int quantPalitosJogados)  {
		
		for(Aposta i: apostas){
			if(i.getQuantPalitos() == aposta.getQuantPalitos()){
				throw new InvalidApostaException("Aposta já realizada, insira outra aposta");
			}
		}
		somaPalitos = somaPalitos + quantPalitosJogados;
		apostas.add(aposta);
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
