package fr.mb.poker.model;

import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.enumeration.Suit;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant une carte avec un rang (Rank) et une couleur (Suit).
 * <p>
 * La valeur peut être modifiée pour des besoins spécifiques du jeu,
 *  comme le traitement de l'As dans une quinte basse.
 *
 *  @author matteo
 */
@Getter
public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Rank rank;
    @Setter
    private int value;

    /**
     * Constructeur pour créer une carte.
     * La valeur initiale est dérivée du rang.
     *
     * @param suit la couleur
     * @param rank le rang
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.getValue();
    }

    /**
     * Compare cette carte avec une autre carte en fonction de leur valeur.
     *
     * @param other la carte à comparer
     * @return un entier négatif, zéro ou un entier positif si la valeur de cette carte
     *         est respectivement inférieure, égale ou supérieure à celle de l'autre carte.
     */
    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.value, other.value);
    }

    /**
     * @return une chaîne de caractères représentant la carte, avec son rang, sa couleur et sa valeur.
     */
    @Override
    public String toString() {
        return " [ " + rank.getLabel() + " - " + suit + " - (" + value + ") ] ";
    }
}