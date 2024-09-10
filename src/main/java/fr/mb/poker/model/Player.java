package fr.mb.poker.model;

import fr.mb.poker.enumeration.Combo;
import lombok.Getter;
import lombok.Setter;
import java.util.stream.Collectors;

/**
 * Classe représentant un joueur.
 * <p>
 * Chaque joueur a un nom, une main de cartes, un score (points) et une combinaison gagnante (winnerCombo).
 *
 * @author matteo
 */
@Getter
public class Player {
    private final String name;
    private final Hand hand;
    @Setter
    private int points = 0;
    @Setter
    private Combo winnerCombo;

    /**
     * Constructeur qui initialise un joueur avec un nom spécifique et une main vide.
     *
     * @param name le nom du joueur
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    /**
     * @return une chaîne de caractères représentant les informations du joueur.
     */
    @Override
    public String toString() {
        String handCards = hand.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.joining(" , "));
        return "Player: " + name +
                "\nHand: " + handCards +
                "\nPoints: " + points +
                "\nWinning Combination: " +
                (winnerCombo != null ? winnerCombo.getDescription() : "Aucune");
    }
}