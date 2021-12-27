package com.cardgame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel mainPanel;

    /*Karten*/
    private JLabel stapel;
    private JLabel pos1;
    private JLabel pos2;
    private JLabel pos3;
    private JLabel pos4;
    private JButton cardNext;

    /*RadioButtons*/
    private JPanel Frage1;
    private JRadioButton redRadio;
    private JRadioButton blackRadio;
    private JPanel Frage2;
    private JRadioButton biggerRadio;
    private JRadioButton smallerRadio;
    private JPanel Frage3;
    private JRadioButton innenRadioButton;
    private JRadioButton aussenRadioButton;
    private JPanel Frage4;
    private JRadioButton KaroRadioButton;
    private JRadioButton HerzRerzRadioButton;
    private JRadioButton PikRadioButton;
    private JRadioButton KreuzRadioButton;
    private JLabel lost;
    private JLabel won;

    Kartenspiel Karten;
    Karte[] gelegt;

    int Frage;
    ImageIcon[] card;

    int gewonnen;
    int verloren;

    public MainForm() {
        card = new ImageIcon[5];
        card[0] = createImageIcon("./res/deck.png","Deckblatt");
        card[1] = createImageIcon("./res/deck.png","Karte1");
        card[2] = createImageIcon("./res/deck.png","Karte2");
        card[3] = createImageIcon("./res/deck.png","Karte3");
        card[4] = createImageIcon("./res/deck.png","Karte4");

        Karten = new Kartenspiel();
        Karten.init();

        Karten.mischen();

        Frage = 0;
        gelegt = new Karte[4];
        gewonnen = verloren = 0;
        won.setText(" "+gewonnen);
        lost.setText(" "+verloren);
        cardNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Karten.getNextCard() < 52) {
                    Karte_legen();
                    Auswerten();
                    if (Karten.getNextCard() == 52) {
                        stapel.setIcon(null);
                        cardNext.setText("Mischen");
                    }
                } else {
                    Karten.mischen();
                    cardNext.setText("neue Karte");
                    stapel.setIcon(card[0]);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    protected void Karte_legen()
    {
        Karte k = Karten.getCard();
        switch(Frage) {
            case 0:
                pos1.setIcon(k.getBild());
                break;
            case 1:
                pos2.setIcon(k.getBild());
                break;
            case 2:
                pos3.setIcon(k.getBild());
                break;
            case 3:
                pos4.setIcon(k.getBild());
                break;
        }
        gelegt[Frage] = k;
    }

    protected void Auswerten()
    {
        if (Frage == 0)
        {
            if( blackRadio.isSelected() && gelegt[0].getPos() > 25 ||
                redRadio.isSelected() && gelegt[0].getPos() < 26
                )
            {
                Frage++;
            }
            else if(!(blackRadio.isSelected()||redRadio.isSelected()))
            {
                JOptionPane.showMessageDialog(null, "Es muss eine Antwort ausgewählt werden!");
                Frage = 0;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "leider verloren");
                pos1.setIcon(card[1]);
                pos2.setIcon(card[2]);
                pos3.setIcon(card[3]);
                pos4.setIcon(card[4]);
                Frage = 0;
                verloren++;
                lost.setText(" "+verloren);
            }
        }
        else if (Frage == 1)
        {
            int ersteKarte = gelegt[0].getPos()%13;
            int zweiteKarte = gelegt[1].getPos()%13;
            if( smallerRadio.isSelected() && ersteKarte > zweiteKarte ||
                biggerRadio.isSelected() && zweiteKarte > ersteKarte)
            {
                Frage++;
            }
            else if(!(smallerRadio.isSelected() || biggerRadio.isSelected()))
            {
                JOptionPane.showMessageDialog(null, "Es muss eine Antwort ausgewählt werden!");
                Frage = 1;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "leider verloren");
                pos1.setIcon(card[1]);
                pos2.setIcon(card[2]);
                pos3.setIcon(card[3]);
                pos4.setIcon(card[4]);
                Frage = 0;
                verloren++;
                lost.setText(" "+verloren);
            }
        }
        else if (Frage == 2)
        {
            int kleineKarte = Math.min(gelegt[0].getPos() % 13, gelegt[1].getPos() % 13);
            int grosseKarte = Math.max(gelegt[0].getPos() % 13, gelegt[1].getPos() % 13);
            int aktuelleKarte = gelegt[2].getPos()%13;
            if( innenRadioButton.isSelected() && aktuelleKarte < grosseKarte && aktuelleKarte > kleineKarte ||
                aussenRadioButton.isSelected() && aktuelleKarte > grosseKarte ||
                aussenRadioButton.isSelected() && aktuelleKarte < kleineKarte)
            {
                Frage++;
            }
            else if(!(innenRadioButton.isSelected() || aussenRadioButton.isSelected()))
            {
                JOptionPane.showMessageDialog(null, "Es muss eine Antwort ausgewählt werden!");
                Frage = 2;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "leider verloren");
                pos1.setIcon(card[1]);
                pos2.setIcon(card[2]);
                pos3.setIcon(card[3]);
                pos4.setIcon(card[4]);
                Frage = 0;
                verloren++;
                lost.setText(" "+verloren);
            }
        }
        else if(Frage == 3)
        {
            if( KaroRadioButton.isSelected() && gelegt[3].getPos() < 13 ||
                HerzRerzRadioButton.isSelected() && gelegt[3].getPos() > 12 && gelegt[3].getPos() < 26 ||
                PikRadioButton.isSelected() && gelegt[3].getPos() > 25 && gelegt[3].getPos() < 39 ||
                KreuzRadioButton.isSelected() && gelegt[3].getPos() > 38 && gelegt[3].getPos() >= 51)
            {
                JOptionPane.showMessageDialog(null, "Spiel gewonnen!");
                gewonnen++;
                won.setText(" "+gewonnen);
            }
            else if(!(KaroRadioButton.isSelected() || HerzRerzRadioButton.isSelected() || PikRadioButton.isSelected() || KreuzRadioButton.isSelected()))
            {
                JOptionPane.showMessageDialog(null, "Es muss eine Antwort ausgewählt werden!");
                Frage = 3;
            }
            else{
                JOptionPane.showMessageDialog(null, "leider verloren");
                verloren++;
                lost.setText(" "+verloren);
                pos1.setIcon(card[1]);
                pos2.setIcon(card[2]);
                pos3.setIcon(card[3]);
                pos4.setIcon(card[4]);
                Frage = 0;
            }
        }
    }
}
