package br.ufrn.palitinhos.utils;

import br.ufrn.palitinhos.servidor.Jogo;

import java.rmi.RemoteException;

/**
 * Created by joao on 16/05/16.
 */
public class StatusThread extends Thread {

    private Jogo jogo;
    private String impresso;
    private Integer idJogador;

    public StatusThread(Jogo jogo, Integer idJogador) {
        this.jogo = jogo;
        this.impresso = "";
        this.idJogador = idJogador;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String tmp = jogo.divulgarResultado(idJogador);
                if(!impresso.equalsIgnoreCase(tmp)) {
                    impresso = tmp;
                    System.out.println(impresso);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
