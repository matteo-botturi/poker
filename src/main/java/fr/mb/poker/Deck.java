package fr.mb.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends Rules{
    private final List<Card> DECK_CARDS;

    public Deck(int numberOfPlayers) {
        DECK_CARDS = new ArrayList<>();
        initializeDeck(numberOfPlayers);
        shuffle();
    }

    public void initializeDeck(int numberOfPlayers) {
        int numberOfStart = 11 - numberOfPlayers;
        int indexOfStart = indexOf(values, numberOfStart);

        for (String suit : suits) {
            for (int i = indexOfStart; i < ranks.length; i++)
                DECK_CARDS.add(new Card(suit, ranks[i]));
        }
    }

    private int indexOf(int[] values, int numberOfStart) {
        for(int i = 0; i < values.length; i++) {
            if(values[i] == numberOfStart)
                return i;
        }
        return 0;
    }

    public void shuffle() {
        Collections.shuffle(DECK_CARDS);
    }

    public Card dealCard() {
        if (DECK_CARDS.isEmpty())
            throw new DeckEmptyException();
        return DECK_CARDS.removeFirst();
    }
}