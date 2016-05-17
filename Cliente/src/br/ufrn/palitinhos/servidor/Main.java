package br.ufrn.palitinhos.servidor;

import br.ufrn.palitinhos.dominio.Jogador;
import br.ufrn.palitinhos.dominio.Resposta;
import br.ufrn.palitinhos.excecao.InvalidApostaException;
import br.ufrn.palitinhos.excecao.InvalidJogadorException;
import br.ufrn.palitinhos.utils.StatusThread;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by joao on 12/05/16.
 */
public class Main {

    private static final String IP = "localhost";
    private static final String METHOD = "Jogo";
    private static Jogo jogo;
    private static Jogador jogador;

    public static void main(String[] args) {
        try {
            System.setSecurityManager(new SecurityManager());

            String serverURL="rmi://" + IP + "/" + METHOD;
            jogo = (Jogo) Naming.lookup(serverURL);

            int i = 0;

            solicitarInteracao();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Solicita a interação inicial do usuário no jogo
     */
    private static void solicitarInteracao() throws InterruptedException, RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================\nDigite uma ação:\n 1- Iniciar o Jogo \n 2- Sair do Jogo\n====================" );
        int opcao = scanner.nextInt();

        if(opcao == 1) {
            boolean passou = false;

            while(!passou) {
                try {
                    System.out.println("Digite seu nome: ");
                    String nome = scanner.next();

                    jogador = jogo.inscreverSala(nome);
                    passou = true;
                    System.out.println(jogador);
                } catch (InvalidJogadorException e) {
                    System.out.println("Ocorreu um erro ao se inscrever na sala: " + e.getMessage());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (opcao == 2) {
            return;
        }

        iniciarJogo();
    }

    private static void iniciarJogo() throws InterruptedException, RemoteException {
        System.out.println("Aguardando o jogo iniciar");

        while (jogo.esperar(jogador.getId()).getStatus().equals(Resposta.AGUARDANDO_INICIAR) && jogo.esperar(jogador.getId()).isAguardar()) {
            System.out.printf(".");
            Thread.sleep(1000);
        }
        System.out.println();

        StatusThread statusThread = new StatusThread(jogo, jogador.getId());
        statusThread.start();

        System.out.println("\nAguardando sua vez de jogar!");

        while(true) {
            if(jogo.esperar(jogador.getId()).getStatus().equals(Resposta.TERMINOU_DE_JOGAR)) {
                System.out.println("Você terminou seus palitos");
            }

            if(jogo.esperar(jogador.getId()).getStatus().equals(Resposta.JOGO_TERMINOU)) {
                System.out.println("Jogo Terminou!");
                break;
            }

            System.out.printf(".");
            if(jogo.esperar(jogador.getId()).getStatus().equals(Resposta.AGUARDANDO_JOGADA) && !jogo.esperar(jogador.getId()).isAguardar()) {
                realizarJogada();
                System.out.println("\nAguardando sua vez de jogar!");
            }

            Thread.sleep(1000);
        }
    }

    private static void realizarJogada() throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        int qtdPalitos;
        int aposta;
        boolean passou = false;

        while(!passou) {
            try {
                System.out.println("\nDigite a quantidade de palitos na mão.");
                qtdPalitos = scanner.nextInt();
                System.out.println("Digite a sua aposta");
                aposta = scanner.nextInt();

                jogo.realizarAposta(jogador.getId(), aposta, qtdPalitos);
                passou = true;
            } catch (InvalidApostaException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
