package fr.mb.poker.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum représentant les quatre couleurs d'un jeu de cartes, classées par niveau
 * pour déterminer la couleur gagnante en cas d'égalité de mains.
 *
 * @author matteo
 */
@Getter
@AllArgsConstructor
public enum Suit {
    /** Cœur */
    HEARTS(4, "♥", Color.RED),

    /** Carreau */
    DIAMONDS(3, "♦", Color.RED),

    /** Trèfle */
    CLUBS(2, "♣", Color.BLACK),

    /** Pique */
    SPADES(1, "♠", Color.BLACK);

    /** Le niveau de la couleur, où 4 est la plus haute. */
    private final int level;

    /** Le symbole associé à la couleur. */
    private final String symbol;

    /** La couleur (rouge ou noir) associée à la couleur du jeu de cartes. */
    private final Color color;
}