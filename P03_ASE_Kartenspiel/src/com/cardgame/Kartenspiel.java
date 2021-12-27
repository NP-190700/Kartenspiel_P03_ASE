package com.cardgame;

import java.util.*;


public class Kartenspiel {

    int nextCard;
    private List<Karte> cardgame = new ArrayList<Karte>(52);

    public List<Karte> getCardgame() {

        return cardgame;
    }

    public void setCardgame(List<Karte> cardgame) {

        this.cardgame = cardgame;
    }

    public int getNextCard() {

        return nextCard;
    }

    public void setNextCard(int nextCard) {

        this.nextCard = nextCard;
    }

    public void init()
    {
        Karte Karten[]= new Karte[52];
        for (int p = 0; p < 52; p++) {
            Karten[p] = new Karte(p);
        }
        setCardgame(Arrays.asList(Karten));
        nextCard=0;
    }

    public void mischen()
    {
        Karte tmp;
        int rand;
        Random r = new Random();
        for (int i = 0; i < 52; i++) {
            rand = r.nextInt(52);
            tmp = cardgame.get(i);
            cardgame.set(i, cardgame.get(rand));
            cardgame.set(rand,tmp);
        }
        nextCard=0;

    }
    public Karte getCard()
    {
        return cardgame.get(nextCard++);
    }
}
