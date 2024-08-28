package fr.mb.poker;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>(5);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCardToHand(Card card) {
        cards.add(card);
    }
}
