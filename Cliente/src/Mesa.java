import com.intellij.uiDesigner.core.GridLayoutManager;

import java.awt.*;

import javax.swing.*;

public class Mesa extends JFrame{

    private Image mesa;

    private  Image player1;
    private  Image player2;


    public Mesa() {
        super("Carregando imagens em uma JFrame");

        // imagem a ser exibida


        // caminho da imagem da mesa
        String minhaImagem = "C:\\Users\\danilo.damasceno\\Desktop\\Trabalho de PD\\mesa.jpg";

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        mesa = toolkit.getImage(minhaImagem);
        MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(mesa, 0);


        String minhaImagem3 = "C:\\Users\\danilo.damasceno\\Desktop\\Trabalho de PD\\player2.jpg";

        Toolkit toolkit3 = Toolkit.getDefaultToolkit();
        player2 = toolkit3.getImage(minhaImagem3);
        MediaTracker mediaTracker3 = new MediaTracker(this);
        mediaTracker3.addImage(mesa, 0);


        String minhaImagem2 = "C:\\Users\\danilo.damasceno\\Desktop\\Trabalho de PD\\player1.jpg";

        Toolkit toolkit2 = Toolkit.getDefaultToolkit();
        player1 = toolkit2.getImage(minhaImagem2);
        MediaTracker mediaTracker2 = new MediaTracker(this);
        mediaTracker2.addImage(player1, 0);

        try{
            mediaTracker.waitForID(0);
        }
        catch(InterruptedException ie){
            System.err.println(ie);
            System.exit(1);
        }

        setSize(player1.getWidth(null),player1.getHeight(null));
        setSize(player2.getWidth(null),player2.getHeight(null));

        setSize(mesa.getWidth(null),mesa.getHeight(null));


        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]){
        Mesa app = new Mesa();
    }


    public void paint(Graphics graphics){
        // para desenhar a mesa do jogo
        graphics.drawImage(mesa, 0, 0, null);
        graphics.drawImage(player1, 80, 110, null);
        graphics.drawImage(player2, 45, 300, null);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - JoÃ£o Medeiros
        JPanel panel1 = new JPanel();

        //======== panel1 ========
        {

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

}