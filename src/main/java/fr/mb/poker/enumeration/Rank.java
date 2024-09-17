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
    /** Deux (2) : La carte avec la valeur la plus basse. */
    TWO("2", 2),

    /** Trois (3). */
    THREE("3", 3),

    /** Quatre (4). */
    FOUR("4", 4),

    /** Cinq (5). */
    FIVE("5", 5),

    /** Six (6). */
    SIX("6", 6),

    /** Sept (7). */
    SEVEN("7", 7),

    /** Huit (8). */
    EIGHT("8", 8),

    /** Neuf (9). */
    NINE("9", 9),

    /** Dix (10). */
    TEN("10", 10),

    /** Valet (J). */
    JACK("J", 11),

    /** Dame (Q). */
    QUEEN("Q", 12),

    /** Roi (K). */
    KING("K", 13),

    /** As : La valeur peut changer de 14 à 1 */
    ACE("A", 14);

    /** Le label (symbole) du rang, par exemple "A" pour l'As. */
    private final String label;

    /** La valeur numérique associée au rang, utilisée pour les comparaisons. */
    private final int value;
}