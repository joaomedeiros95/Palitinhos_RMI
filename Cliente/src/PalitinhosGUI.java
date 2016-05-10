import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PalitinhosGUI {
    private static BufferedImage mesa;
    private static BufferedImage player1;
    private static BufferedImage player2;
    private static BufferedImage player3;
    private static BufferedImage player4;
    private static BufferedImage player5;
    private static BufferedImage player6;
    private static BufferedImage palito;
    private static BufferedImage garrafa;

    private static JPanel panel;

    private static void createImages() {
        try {
            mesa = ImageIO.read(new File("resources/imagens/mesa.jpg"));
            player1 = ImageIO.read(new File("resources/imagens/player1.png"));
            player2 = ImageIO.read(new File("resources/imagens/player2.png"));
            player3 = ImageIO.read(new File("resources/imagens/player3.png"));
            player4 = ImageIO.read(new File("resources/imagens/player4.png"));
            player5 = ImageIO.read(new File("resources/imagens/player5.png"));
            player6 = ImageIO.read(new File("resources/imagens/player6.png"));
            palito = ImageIO.read(new File("resources/imagens/fosforo.png"));
            garrafa = ImageIO.read(new File("resources/imagens/garrafa.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static JFrame buildFrame() {
        createImages();

        JFrame frame = new JFrame("Palitinhos Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(mesa.getWidth(null), mesa.getHeight(null));
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }

    private static void addJogador(int player) {
        JLabel label = new JLabel();
        BufferedImage image = null;
        String position;

        switch (player) {
            case 1:
                image = player1;
                position = BorderLayout.NORTH;
                break;
            case 2:
                image = player2;
                position = BorderLayout.NORTH;
                break;
            case 3:
                image = player3;
                position = BorderLayout.SOUTH;
                break;
            case 4:
                image = player4;
                position = BorderLayout.SOUTH;
                break;
            case 5:
                image = player5;
                position = BorderLayout.EAST;
                break;
            default:
                image = player6;
                position = BorderLayout.WEST;
                break;
        }

        label.setIcon(new ImageIcon(image));
        label.setHorizontalAlignment(JLabel.CENTER);

        panel.add(label, position);
        panel.revalidate();
        panel.repaint();
    }

    public PalitinhosGUI() {
        JFrame frame = buildFrame();

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(mesa, 0, 0, null);
                graphics.drawImage(garrafa, 480, 330, null);

//                // jogadores da mesa
//                graphics.drawImage(player1, 300, 50, null);
//                graphics.drawImage(player2, 550, 50, null);
//                graphics.drawImage(player3, 45, 300, null);
//                graphics.drawImage(player4, 800, 300, null);
//                graphics.drawImage(player5, 300, 570, null);
//                graphics.drawImage(player6, 550, 570, null);

                // palitos do jogador amarelo
                graphics.drawImage(palito, 350, 460, null);
                graphics.drawImage(palito, 372, 460, null);
                graphics.drawImage(palito, 395, 460, null);
            }
        };

        panel.setLayout(new BorderLayout());
        panel.setSize(mesa.getWidth(null), mesa.getHeight(null));

        frame.add(panel);
    }

    public static void main(String args[]) {
        new PalitinhosGUI();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Escreve um jogador");
            addJogador(scan.nextInt());
        }
    }

}