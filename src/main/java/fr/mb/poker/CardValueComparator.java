package fr.mb.poker;

import java.util.Comparator;

public class CardValueComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        return Integer.compare(card1.getVALUE(), card2.getVALUE());
    }
}