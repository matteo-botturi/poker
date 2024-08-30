package fr.mb.poker;

import lombok.Getter;

@Getter
public class Card extends Rules{
    private final String suit;
    private final String rank;
    private final int value;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = calculateValue(rank);
    }

    private int calculateValue(String rank) {
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].equals(rank))
                return values[i];
        }
        throw new IllegalArgumentException("Rank not valid: " + rank);
    }

    @Override
    public String toString() {
        return " [ " + rank + " - " + suit + " - (" + value + ") ] ";
    }
}