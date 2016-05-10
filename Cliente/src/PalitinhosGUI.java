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
    private static JPanel[] panelsInternos;

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

    private static void buildPanels() {
        panelsInternos = new JPanel[6];

        for(int i = 0; i < panelsInternos.length; i++) {
            panelsInternos[i] = new JPanel();
            panelsInternos[i].setSize(mesa.getWidth(null) / 6, mesa.getHeight(null));
            panelsInternos[i].setLayout(new GridLayout(1, 2));
            panelsInternos[i].setOpaque(false);

            panel.add(panelsInternos[i]);
            panel.revalidate();
            panel.repaint();
        }
    }

    private static void addJogador(int player, String nome) {
        JLabel imageLabel = new JLabel();
        JLabel nomeLabel = new JLabel();

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

        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(JLabel.LEFT);

        nomeLabel.setText(nome);
        nomeLabel.setHorizontalAlignment(JLabel.LEFT);

        panelsInternos[player - 1].add(imageLabel);
        panelsInternos[player - 1].add(nomeLabel);
        panelsInternos[player - 1].revalidate();
        panelsInternos[player - 1].repaint();
    }

    public PalitinhosGUI() {
        JFrame frame = buildFrame();

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(mesa, 0, 0, null);
                graphics.drawImage(garrafa, 480, 330, null);
            }
        };

        panel.setLayout(new GridLayout(6, 1));
        panel.setSize(mesa.getWidth(null), mesa.getHeight(null));
        buildPanels();

        frame.add(panel);
    }

    public static void main(String args[]) {
        new PalitinhosGUI();
        Scanner scan = new Scanner(System.in);

//        for(int i = 1; i < 7; i++) {
//            addJogador(i, "João");
//        }

        while (true) {
            System.out.println("Escreve um jogador");
            addJogador(scan.nextInt(), "João");
        }
    }

}