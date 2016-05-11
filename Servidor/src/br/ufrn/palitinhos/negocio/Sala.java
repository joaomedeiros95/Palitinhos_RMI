package br.ufrn.palitinhos.negocio;
import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.excecao.InvalidJogadorException;

import java.util.ArrayList;
import java.util.List;


public class Sala implements SalaInterface {
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	private int proximoJogador = 0;

	/* (non-Javadoc)
	 * @see br.ufrn.palitinhos.negocio.SalaInterface#getJogadores()
	 */
	@Override
	public ArrayList<Jogador> getJogadores() {
		return (ArrayList<Jogador>) jogadores;
	}

	/* (non-Javadoc)
	 * @see br.ufrn.palitinhos.negocio.SalaInterface#setJogadores(java.util.ArrayList)
	 */
	@Override
	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;	
		
	}
	
	private int buscarJogador(int id){
		int retorno = -1;
		for(int i = 0; i < jogadores.size(); i++){
			if(jogadores.get(i).getId() == id){
				retorno = i;
				break;
			}
		}
		return retorno;		
	}
	
	/* (non-Javadoc)
	 * @see br.ufrn.palitinhos.negocio.SalaInterface#insertJogador(br.ufrn.palitinhos.dominio.Jogador)
	 */
	@Override
	public void insertJogador(Jogador jogador) {
		if (buscarJogador(jogador.getId()) != -1){
			jogadores.add(jogador);	
		}
		else{
			throw new InvalidJogadorException("Jogador já inserido");
		}

	}
	
	/* (non-Javadoc)
	 * @see br.ufrn.palitinhos.negocio.SalaInterface#deleteJogador(int)
	 */
	@Override
	public void deleteJogador(int id) {	
		int i = buscarJogador(id);
		if (i != -1){
			jogadores.remove(i);
		}
		else {
			throw new InvalidJogadorException("Jogador não estava na lista");
		}
	}
	
	/* (non-Javadoc)
	 * @see br.ufrn.palitinhos.negocio.SalaInterface#proximoJogador()
	 */
	@Override
	public Jogador proximoJogador(){
		Jogador retorno = jogadores.get(proximoJogador);
		proximoJogador++;
		if(proximoJogador == jogadores.size()){
			proximoJogador = 0;
		}
		return retorno;
	}
	

}
