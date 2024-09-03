package fr.mb.poker.model;

import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.enumeration.Suit;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Card implements Comparable<Card>{
    private final Suit suit;
    private final Rank rank;
    @Setter
    private int value;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.getValue();
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return " [ " + rank.getLabel() + " - " + suit + " - (" + value + ") ] ";
    }
}