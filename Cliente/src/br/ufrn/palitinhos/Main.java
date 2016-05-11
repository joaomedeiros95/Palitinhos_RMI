package br.ufrn.palitinhos;

import br.ufrn.palitinhos.dominio.Status;
import br.ufrn.palitinhos.gui.PalitinhosGUI;

import java.util.Scanner;

/**
 * Created by joao on 10/05/16.
 */
public class Main {

    public static void main(String args[]) {
        PalitinhosGUI gui = new PalitinhosGUI();
        Scanner scan = new Scanner(System.in);

        for(int i = 1; i < 7; i++) {
            gui.addJogador(i, "João");
        }

        gui.colocarPalitos(1, 3);
        gui.updateStatus(2, Status.AGUARDANDO_OUTROS.getDescricao());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gui.colocarPalitos(1, 2);
        gui.updateStatus(2, Status.JOGANDO.getDescricao());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gui.colocarPalitos(1, 1);

        gui.updateStatus(1, Status.TERMINOU.getDescricao());

//        while (true) {
//            System.out.println("Escreve um jogador");
//            addJogador(scan.nextInt(), "João");
//        }
    }

}
