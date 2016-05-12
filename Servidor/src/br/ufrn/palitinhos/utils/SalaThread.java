package br.ufrn.palitinhos.utils;

import br.ufrn.palitinhos.negocio.Sala;

/**
 * Created by joao on 12/05/16.
 */
public class SalaThread extends Thread {

    private Sala sala;

    public SalaThread(Sala sala) {
        this.sala = sala;
    }

    @Override
    public void run() {
        boolean liberada = false;

        try {
            while(!liberada) {
                int tamanho = sala.getJogadores().getJogadores().size();

                if(tamanho < Parametros.MINIMO_JOGADORES) {
                    System.out.println("Sala ainda não alcançou o tamanho mínimo. " + tamanho + " jogadores em sala.");
                    sleep(2000);
                    continue;
                } else {
                    if(tamanho == Parametros.MAXIMO_JOGADORES) {
                        System.out.println("Jogo começou! Sala lotada!");
                        sala.setJogoIniciou(true);
                        break;
                    }

                    if(tamanho < Parametros.MAXIMO_JOGADORES) {
                        int tempTamanho = sala.getJogadores().getJogadores().size();
                        System.out.println("Sala alcançou tamanho mínimo, aguardando 10s para caso algum jogador queira entrar.");
                        System.out.println(tamanho + " jogadores em sala.");
                        sleep(10000);

                        /* caso não tenha entrado ninguém o jogo se inicia
                         * caso contrário espera-se mais uma iteração do while
                         */
                        if(tempTamanho == sala.getJogadores().getJogadores().size()) {
                            sala.setJogoIniciou(true);
                            break;
                        }
                    }
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}