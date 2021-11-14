package Kartenspiel;

import javax.swing.*;

public class Karte {
    int nr;
    ImageIcon pic;
    Karte(int n){
        nr = n;
        java.net.URL url=getClass().getResource("./res/card"+nr+".png");
        pic = new ImageIcon(url, "karte" + nr);
    }
}
