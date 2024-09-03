package fr.mb.poker.model;

import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.enumeration.Suit;
import fr.mb.poker.exception.DeckEmptyException;
import java.util.*;

public class Deck{
    private final List<Card> deckCards;

    public Deck(int numberOfPlayers) {
        deckCards = new ArrayList<>();
        initializeDeck(numberOfPlayers);
        Collections.shuffle(deckCards);
    }

    private void initializeDeck(int numberOfPlayers) {
        int numberOfStart = 11 - numberOfPlayers;
        Set<Rank> ranks = EnumSet.allOf(Rank.class);
        Set<Suit> suits = EnumSet.allOf(Suit.class);

        ranks.stream()
                .filter(rank -> rank.getValue() >= numberOfStart)
                .forEach(rank -> suits.forEach(suit -> deckCards.add(new Card(suit, rank))));
    }

    public Card dealCard() {
        if (deckCards.isEmpty())
            throw new DeckEmptyException();
        return deckCards.remove(0);
    }
}