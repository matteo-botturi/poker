package fr.mb.poker.model;

import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.enumeration.Suit;
import fr.mb.poker.exception.DeckEmptyException;
import java.util.*;

/**
 * Classe représentant un paquet de cartes (deck) utilisé dans une partie de poker.
 * <p>
 * Le paquet est initialisé et les cartes sont mélangées
 * aléatoirement avant le début du jeu.
 *
 * @author matteo
 */
public class Deck {
    /** Liste des cartes dans le jeu. */
    private final List<Card> deck;

    /**
     * Constructeur pour créer un paquet de cartes en fonction du nombre de joueurs.
     *
     * @param numberOfPlayers le nombre de joueurs, utilisé pour déterminer les cartes à inclure dans le jeu
     */
    public Deck(int numberOfPlayers) {
        deck = new ArrayList<>();
        initializeDeck(numberOfPlayers);
        Collections.shuffle(deck);
    }

    /**
     * Les cartes sont ajoutées au paquet uniquement si leur valeur est supérieure ou égale à un seuil
     * calculé à partir du nombre de joueurs.
     */
    private void initializeDeck(int numberOfPlayers) {
        int numberOfStart = 11 - numberOfPlayers;
        Set<Rank> ranks = EnumSet.allOf(Rank.class);
        Set<Suit> suits = EnumSet.allOf(Suit.class);

        ranks.stream()
                .filter(rank -> rank.getValue() >= numberOfStart)
                .forEach(rank -> suits.forEach(suit -> deck.add(new Card(suit, rank))));
    }

    /**
     * Distribue une carte du dessus du paquet. Si il est vide, une exception est lancée.
     *
     * @return la carte distribuée
     * @throws DeckEmptyException si le paquet est vide et qu'il n'y a plus de cartes à distribuer
     */
    public Card dealCard() {
        if (deck.isEmpty())
            throw new DeckEmptyException();
        return deck.removeFirst();
    }
}