package fr.mb.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Deck extends Rules{
    private final List<Card> deckCards;

    public Deck(int numberOfPlayers) {
        deckCards = new ArrayList<>();
        initializeDeck(numberOfPlayers);
        Collections.shuffle(deckCards);
    }

    private void initializeDeck(int numberOfPlayers) {
        int numberOfStart = 11 - numberOfPlayers;
        int indexOfStart = indexOf(values, numberOfStart);

        for (String suit : suits) {
            for (int i = indexOfStart; i < ranks.length; i++)
                deckCards.add(new Card(suit, ranks[i]));
        }
    }

    private int indexOf(int[] values, int numberOfStart) {
        return IntStream.range(0, values.length)
                .filter(i -> values[i] == numberOfStart)
                .findFirst()
                .orElse(0);
    }

    public Card dealCard() {
        if (deckCards.isEmpty())
            throw new DeckEmptyException();
        return deckCards.remove(0);
    }
}