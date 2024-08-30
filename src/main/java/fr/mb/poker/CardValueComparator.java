package fr.mb.poker;

import java.util.Comparator;

public class CardValueComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        return Comparator.comparingInt(Card::getValue).compare(card1, card2);
    }
}