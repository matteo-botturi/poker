package fr.mb.poker.enumeration;

import lombok.Getter;

public enum ERank {
    TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5),
    SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9),
    TEN("10", 10), JACK("J", 11), QUEEN("Q", 12), KING("K", 13), ACE("A", 14);

    @Getter
    private final String symbol;
    @Getter
    private final int value;

    ERank(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }
}