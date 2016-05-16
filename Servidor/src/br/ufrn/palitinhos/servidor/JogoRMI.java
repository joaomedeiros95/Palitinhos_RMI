package br.ufrn.palitinhos.servidor;

import br.ufrn.palitinhos.dominio.Aposta;
import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.dominio.Resposta;
import br.ufrn.palitinhos.negocio.Sala;
import br.ufrn.palitinhos.negocio.SalaInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by joao on 11/05/16.
 */
public class  JogoRMI extends UnicastRemoteObject implements Jogo {

    private static final long serialVersionUID = 938102786005220066L;
    private SalaInterface sala;
    private Integer idJogador;

    protected JogoRMI() throws RemoteException {
        sala = new Sala();
        idJogador = 1;
    }

    @Override
    public Jogador inscreverSala(String nome) throws RemoteException {
        Jogador jogador = new Jogador(nome, idJogador++);
        sala.inscreverJogador(jogador);

        return jogador;
    }

    @Override
    public Resposta esperar(int id) throws RemoteException {
        return sala.esperar(id);
    }

    @Override
    public void realizarAposta(int id, int qtdApostada, int qtdPalitosMao) throws RemoteException {
        Aposta aposta = new Aposta(id, qtdApostada);
        sala.realizarJogada(aposta, qtdPalitosMao);
    }

    @Override
    public void getStatus() throws RemoteException {

    }

    @Override
    public String divulgarResultado(int idJogador) throws RemoteException {
        return sala.divulgarResultado(idJogador);
    }
}
