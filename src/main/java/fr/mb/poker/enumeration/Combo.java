package fr.mb.poker.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(of = "description")
@AllArgsConstructor
@Getter
public enum Combo {
    HIGH_CARD("High card"),
    ONE_PAIR("One Pair"),
    TWO_PAIR("Two Pair"),
    THREE_OF_A_KIND("Three of a kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULL_HOUSE("Full House"),
    FOUR_OF_A_KIND("Four of a kind"),
    STRAIGHT_FLUSH("Straight Flush"),
    ROYAL_FLUSH("Royal Flush");

    private final String description;
}