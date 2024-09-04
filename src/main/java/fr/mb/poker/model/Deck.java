package fr.mb.poker.model;

import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.enumeration.Suit;
import fr.mb.poker.exception.DeckEmptyException;
import java.util.*;

public class Deck{
    private final List<Card> deck;

    public Deck(int numberOfPlayers) {
        deck = new ArrayList<>();
        initializeDeck(numberOfPlayers);
        Collections.shuffle(deck);
    }

    private void initializeDeck(int numberOfPlayers) {
        int numberOfStart = 11 - numberOfPlayers;
        Set<Rank> ranks = EnumSet.allOf(Rank.class);
        Set<Suit> suits = EnumSet.allOf(Suit.class);

        ranks.stream()
                .filter(rank -> rank.getValue() >= numberOfStart)
                .forEach(rank -> suits.forEach(suit -> deck.add(new Card(suit, rank))));
    }

    public Card dealCard() {
        if (deck.isEmpty())
            throw new DeckEmptyException();
        return deck.remove(0);
    }
}