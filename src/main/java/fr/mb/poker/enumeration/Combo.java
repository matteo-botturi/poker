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
    /** Quinte Flush Royale */
    ROYAL_FLUSH(1, "Royal Flush"),

    /** Quinte flush */
    STRAIGHT_FLUSH(2, "Straight Flush"),

    /** Carré */
    FOUR_OF_A_KIND(3, "Four of a kind"),

    /** Full */
    FULL_HOUSE(4, "Full House"),

    /** Couleur */
    FLUSH(5, "Flush"),

    /** Quinte */
    STRAIGHT(6, "Straight"),

    /** Brelan */
    THREE_OF_A_KIND(7, "Three of a kind"),

    /** Double paire */
    TWO_PAIR(8, "Two Pair"),

    /** Paire */
    ONE_PAIR(9, "One Pair"),

    /** Carte haute */
    HIGH_CARD(10, "High card");

    /** La priorité de la combinaison, où 1 est la plus haute. */
    private final int priority;

    /** Description lisible de la combinaison. */
    private final String description;
}