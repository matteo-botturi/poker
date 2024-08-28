package fr.mb.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends Rules{
    private List<Card> deckCards;

    public Deck(int numberOfPlayers) {
        deckCards = new ArrayList<>();
        initializeDeck(numberOfPlayers);
        shuffle();
    }

    public void initializeDeck(int numberOfPlayers) {

        int numberOfStart = 11 - numberOfPlayers;
        int indexOfStart = indexOf(values, numberOfStart);

        for (String suit : suits) {
            for (int i = indexOfStart; i < ranks.length; i++)
                deckCards.add(new Card(suit, ranks[i]));
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
        Collections.shuffle(deckCards);
    }

    public Card dealCard() {
        if (deckCards.isEmpty())
            throw new DeckEmptyException();
        return deckCards.remove(0);
    }
}
