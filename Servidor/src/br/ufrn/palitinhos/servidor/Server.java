package br.ufrn.palitinhos.servidor;

import java.rmi.Naming;

public class Server {
	
	public static void main(String[] args) {
		try {
			System.setSecurityManager(new SecurityManager());
			JogoRMI jogo = new JogoRMI();
			Naming.rebind("//localhost/Jogo", jogo);
			System.out.println("Palitinhos server is ready");
						
		} catch (Exception e) {
			System.out.println("Exception: Jokenpo Server failed: " + e);
		}
	}

}
