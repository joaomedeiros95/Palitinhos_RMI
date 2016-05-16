package br.ufrn.palitinhos.servidor;

import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.dominio.Resposta;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by joao on 11/05/16.
 */
public interface Jogo extends Remote {

    Jogador inscreverSala(String nome) throws RemoteException;
    Resposta esperar(int id) throws RemoteException;
    void realizarAposta(int id, int qtdApostada, int qtdPalitosMao) throws RemoteException;
    void getStatus() throws RemoteException;
    String divulgarResultado(int idJogador) throws RemoteException;
}
