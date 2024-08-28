package fr.mb.poker;

import lombok.Getter;

@Getter
public class Card extends Rules{
    private final String SUIT;
    private final String RANK;
    private final int VALUE;

    public Card(String suit, String rank) {
        this.SUIT = suit;
        this.RANK = rank;
        this.VALUE = calculateValue(rank);
    }

    private int calculateValue(String rank) {
        return switch (rank) {
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "10" -> 10;
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
            case "A" -> 14;
            default -> throw new IllegalArgumentException("Rank not valid: " + rank);
        };
    }

    @Override
    public String toString() {
        return " [ " + RANK + " - " + SUIT + " - " + VALUE + " ] ";
    }
}