package fr.mb.poker.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum représentant les différentes valeurs (rangs) des cartes.
 * <p>
 * Chaque rang est associé à un label (symbole) et à une valeur numérique
 * utilisée pour comparer les cartes entre elles.
 *
 * @author matteo
 */
@Getter
@AllArgsConstructor
public enum Rank {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),

    /** Valet */
    JACK("J", 11),

    /** Dame */
    QUEEN("Q", 12),

    /** Roi */
    KING("K", 13),

    /** As : La valeur peut changer de 14 à 1 */
    ACE("A", 14);

    private final String label;
    private final int value;
}