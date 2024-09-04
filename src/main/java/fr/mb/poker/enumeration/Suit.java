package fr.mb.poker.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Suit {
    HEARTS(4, "♥", Color.RED),
    DIAMONDS(3, "♦", Color.RED),
    CLUBS(2, "♣", Color.BLACK),
    SPADES(1, "♠", Color.BLACK);

    private final int priority;
    private final String symbol;
    private final Color color;
}