package br.ufrn.palitinhos.servidor;

import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.dominio.Jogador;

import java.rmi.Remote;

/**
 * Created by joao on 11/05/16.
 */
public interface Jogo extends Remote {

    Jogador inscreverSala(String nome);
    boolean esperar(int id);
    void realizarAposta(int id, int qtdApostada, int qtdPalitosMao);
    void getStatus();
    int divulgarResultado();

}
