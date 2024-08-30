package fr.mb.poker;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Hand {
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>(5);
    }

    public void addCardToHand(Card card) {
        cards.add(card);
    }
}