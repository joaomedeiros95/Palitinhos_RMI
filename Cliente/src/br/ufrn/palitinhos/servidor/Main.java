package br.ufrn.palitinhos.servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
            System.out.println(jogo.divulgarResultado());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
