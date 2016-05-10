package br.ufrn.palitinhos.gui;

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
            panelsInternos[i].setSize(mesa.getWidth(null) / 6, mesa.getHeight(null) / 3);
            panelsInternos[i].setLayout(new GridLayout(1, 4));
            panelsInternos[i].setOpaque(false);

            panel.add(panelsInternos[i]);
            panel.revalidate();
        }
    }

    public static void updateStatus(int pos, String status) {
        JLabel statusLabel = new JLabel();
        statusLabel.setText(status);

        if(panelsInternos[pos - 1].getComponentCount() > 3) { //já possui o painel de palitos
            panelsInternos[pos - 1].remove(3);
        }

        panelsInternos[pos - 1].add(statusLabel);
        panelsInternos[pos - 1].revalidate();
    }

    public static void colocarPalitos(int pos, int qtdPalitos) {
        JPanel palitos = new JPanel();

        if(panelsInternos[pos - 1].getComponentCount() > 2) { //já possui o painel de palitos
            panelsInternos[pos - 1].remove(2);
        }

        if(qtdPalitos > 0) {
            palitos.setLayout(new GridLayout(1, qtdPalitos));
            palitos.setOpaque(false);

            for(int i = 0; i < qtdPalitos; i++) {
                JLabel image = new JLabel();
                image.setIcon(new ImageIcon(palito));

                palitos.add(image);
            }

            panelsInternos[pos - 1].add(palitos);
            panelsInternos[pos - 1].revalidate();
        }
    }

    public static void addJogador(int player, String nome) {
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
        nomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        nomeLabel.setForeground(Color.WHITE);

        panelsInternos[player - 1].add(imageLabel);
        panelsInternos[player - 1].add(nomeLabel);
        panelsInternos[player - 1].revalidate();
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

}