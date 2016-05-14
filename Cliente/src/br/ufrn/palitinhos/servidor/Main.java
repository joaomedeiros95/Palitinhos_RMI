package br.ufrn.palitinhos.servidor;

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

    public static void main(String[] args) {
        try {
            System.setSecurityManager(new SecurityManager());

            String serverURL="rmi://" + IP + "/" + METHOD;
            jogo = (Jogo) Naming.lookup(serverURL);

            Scanner scanner = new Scanner(System.in);
            int i = 0;

            while(true) {
                int opcao = scanner.nextInt();

                if(opcao == 1) {
                    System.out.println(jogo.inscreverSala("Teste " + i));
                } else if(opcao == 2) {
                    jogo.getStatus();
                } else if(opcao == 3) {
                    System.out.println(jogo.divulgarResultado());
                } else if(opcao == 4) {
                    int jogador = scanner.nextInt();
                    System.out.println(jogo.esperar(jogador));
                } else {
                    int jogador = scanner.nextInt();
                    int palitos = scanner.nextInt();
                    int aposta = scanner.nextInt();
                    jogo.realizarAposta(jogador, aposta, palitos);
                }
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
