package fr.mb.poker.model;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Hand {
    private static final int MAX_CARDS = 5;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>(MAX_CARDS);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void sortHand() {
        Collections.sort(cards);
    }
}