package fr.mb.poker.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Combo {
    ROYAL_FLUSH(1, "Royal Flush"),
    STRAIGHT_FLUSH(2, "Straight Flush"),
    FOUR_OF_A_KIND(3, "Four of a kind"),
    FULL_HOUSE(4, "Full House"),
    FLUSH(5, "Flush"),
    STRAIGHT(6, "Straight"),
    THREE_OF_A_KIND(7, "Three of a kind"),
    TWO_PAIR(8, "Two Pair"),
    ONE_PAIR(9, "One Pair"),
    HIGH_CARD(10, "High card");

    private final int priority;
    private final String description;
}