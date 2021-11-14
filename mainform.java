package Kartenspiel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class mainform {
    private JPanel mainPanel;
    private JLabel stapel;
    private JLabel pos1;
    private JLabel pos2;
    private JLabel pos3;
    private JLabel pos4;
    private JButton Button_newcard;
    private JRadioButton rotRadioButton;
    private JRadioButton schwarzRadioButton;
    private JRadioButton größerRadioButton;
    private JRadioButton kleinerRadioButton;
    private JRadioButton innerhalbRadioButton;
    private JRadioButton außerhalbRadioButton;
    private JRadioButton karoRadioButton;
    private JRadioButton herzRadioButton;
    private JRadioButton pikRadioButton;
    private JRadioButton kreuzRadioButton;

    Karte Kartenspiel[] = new Karte[52];
    int pos = 0;

    void shuffle(int [] Karte) {
        for (int i = 0; i < Karte.length; i++) {
            int index = (int) (Math.random() * Karte.length);
            int temp = Karte[i];
            Karte[i] = Karte[index];
            Karte[index] = temp;
        }
    }

/*
    public static int[] RandomizeArray(int[] Karte){
        Random rand = new Random();

        for (int i=0; i<Karte.length; i++) {
            int randomPosition = rand.nextInt(Karte.length);
            int temp = Karte[i];
            Karte[i] = Karte[randomPosition];
            Karte[randomPosition] = temp;
        }

        return Karte;
    }
*/


    public mainform()
    {
        for (int i=0; i < 52; i++)
            Kartenspiel[i] = new Karte(i);

        Button_newcard.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                KarteLegen(); //shuffle();
            }
        }
        );
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Kartenspiel");
        frame.setContentPane(new mainform().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
    public int Runde = 1;
    public void KarteLegen()
    {
 /*
            Random r = new Random();
            int nr = r.nextInt(52);
            java.net.URL url=getClass().getResource("./res/card"+nr+".png");
 */

            ImageIcon card = Kartenspiel[pos].pic;
            int nr = Kartenspiel[pos].nr;
            pos++;

            if(Runde == 1)
            {
                pos1.setIcon(card);
                if (    (rotRadioButton.isSelected() && nr < 26) ||
                        (schwarzRadioButton.isSelected() && nr >= 26))
                {
                    JOptionPane.showMessageDialog(null, "1. Karte richtig!");
                    Runde = 2;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Leider falsch! \nSie haben verloren!");
                    System.exit(0);
                }
            }

            else if(Runde == 2)
            {
                pos2.setIcon(card);
                if (    (größerRadioButton.isSelected() && nr >= nr) ||
                        (kleinerRadioButton.isSelected() && nr <= nr))
                {
                    // JOptionPane.showMessageDialog(null, "2. Karte richtig!");
                    Runde = 3;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Leider falsch! \nSie haben verloren!");
                    System.exit(0);
                }
            }

            else if(Runde == 3)
            {
                pos3.setIcon(card);
                if(     (innerhalbRadioButton.isSelected())||
                        (außerhalbRadioButton.isSelected()))
                {
                    //JOptionPane.showMessageDialog((null, "3. Karte richtig!));
                    Runde = 4;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Leider falsch! \nSie haben verloren!");
                    System.exit(0);
                }
            }

            else if(Runde == 4)
            {
                pos4.setIcon(card);
                if(     (karoRadioButton.isSelected() && nr < 13) ||
                        (herzRadioButton.isSelected() && nr >= 13 && nr < 26) ||
                        (pikRadioButton.isSelected() && nr > 26 && nr < 39) ||
                        (kreuzRadioButton.isSelected() && nr > 39 && nr < 52))
                {
                    JOptionPane.showMessageDialog(null, "4. Karte richtig!\nSie haben das Spiel gewonnen!!");
                    System.exit(0);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Leider falsch! \nSie haben verloren!");
                    System.exit(0);
                }
            }
    }
}

