package br.ufrn.palitinhos.dominio;

import br.ufrn.palitinhos.excecao.InvalidJogadorException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 12/05/16.
 */
public class Jogadores {

    private List<Jogador> jogadores;

    public Jogadores() {
        this.jogadores = new ArrayList<>();
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;

    }

    public int buscarJogador(int id){
        int retorno = -1;
        for(int i = 0; i < jogadores.size(); i++){
            if(jogadores.get(i).getId() == id){
                retorno = i;
                break;
            }
        }
        return retorno;
    }

    public Jogador buscarJogadorObj(int id){
        int retorno = -1;
        for(int i = 0; i < jogadores.size(); i++){
            if(jogadores.get(i).getId() == id){
                return jogadores.get(i);
            }
        }
        return null;
    }

    public void insertJogador(Jogador jogador) {
        if (buscarJogador(jogador.getId()) == -1){
            jogadores.add(jogador);
        }
        else {
            throw new InvalidJogadorException("Jogador já inserido");
        }

    }

    public void deleteJogador(int id) {
        int i = buscarJogador(id);
        if (i != -1){
            jogadores.remove(i);
        }
        else {
            throw new InvalidJogadorException("Jogador não estava na lista");
        }
    }

}
