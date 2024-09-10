package fr.mb.poker.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum représentant les différentes combinaisons possibles dans une main de poker,
 * classées par ordre de priorité, de la plus forte à la plus faible.
 * <p>
 * La priorité est utilisée pour déterminer la combinaison gagnante en cas d'égalité de points.
 *
 * @author matteo
 */
@AllArgsConstructor
@Getter
public enum Combo {
    /** Quinte Flush Royale (Royal Flush) */
    ROYAL_FLUSH(1, "Royal Flush"),

    /** Quinte flush (Straight Flush) */
    STRAIGHT_FLUSH(2, "Straight Flush"),

    /** Carré (Four of a Kind) */
    FOUR_OF_A_KIND(3, "Four of a kind"),

    /** Full (Full House) */
    FULL_HOUSE(4, "Full House"),

    /** Couleur (Flush) */
    FLUSH(5, "Flush"),

    /** Quinte (Straight) */
    STRAIGHT(6, "Straight"),

    /** Brelan (Three of a Kind) */
    THREE_OF_A_KIND(7, "Three of a kind"),

    /** Double paire (Two Pair) */
    TWO_PAIR(8, "Two Pair"),

    /** Paire (One Pair) */
    ONE_PAIR(9, "One Pair"),

    /** Carte haute (High Card) */
    HIGH_CARD(10, "High card");

    /** La priorité de la combinaison, où 1 est la plus haute. */
    private final int priority;
    private final String description;
}