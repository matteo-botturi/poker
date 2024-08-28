package fr.mb.poker;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Hand {
    private final List<Card> CARDS;

    public Hand() {
        CARDS = new ArrayList<>(5);
    }

    public void addCardToHand(Card card) {
        CARDS.add(card);
    }
}