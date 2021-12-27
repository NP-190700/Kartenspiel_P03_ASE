package com.cardgame;

import javax.swing.*;

public class Karte {
    private ImageIcon Bild;
    private String file;
    private int pos;

    public Karte(){

    }

    public Karte(int pos)
    {
        this.pos = pos;
        file = "card"+ pos +".png";
        java.net.URL imgURL = getClass().getResource("./res/"+file);
        if (imgURL != null) {
            Bild= new ImageIcon(imgURL, file);
        }
        else
            Bild = null;
    }

    public int getPos() {
        return pos;
    }

    public ImageIcon getBild() {
        return Bild;
    }

}
