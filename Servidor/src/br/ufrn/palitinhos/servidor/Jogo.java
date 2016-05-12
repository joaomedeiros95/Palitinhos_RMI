package br.ufrn.palitinhos.servidor;

import br.ufrn.palitinhos.dominio.Jogador;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by joao on 11/05/16.
 */
public interface Jogo extends Remote {

    Jogador inscreverSala(String nome) throws RemoteException;
    boolean esperar(int id) throws RemoteException;
    void realizarAposta(int id, int qtdApostada, int qtdPalitosMao) throws RemoteException;
    void getStatus() throws RemoteException;
    int divulgarResultado() throws RemoteException;

}
