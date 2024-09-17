package fr.mb.poker.model;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe représentant une main de poker.
 * <p>
 * Une main peut contenir un nombre maximum de cartes, défini par la constante {@code MAX_CARDS}.
 * Les cartes dans la main peuvent être ajoutées et triées.
 *
 * @author matteo
 */
@Getter
public class Hand {
    /** Le nombre maximum de cartes dans une main. */
    private static final int MAX_CARDS = 5;

    /** La liste des cartes qui composent la main. */
    private final List<Card> cards;

    /**
     * Constructeur qui initialise une main vide avec une capacité maximale prédéfinie.
     */
    public Hand() {
        cards = new ArrayList<>(MAX_CARDS);
    }

    /**
     * Ajoute une carte à la main.
     *
     * @param card la carte à ajouter à la main
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Trie les cartes en fonction de leur valeur.
     */
    public void sortHand() {
        Collections.sort(cards);
    }
}