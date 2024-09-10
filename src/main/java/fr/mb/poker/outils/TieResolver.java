package fr.mb.poker.outils;

import fr.mb.poker.enumeration.Combo;
import fr.mb.poker.model.Player;
import fr.mb.poker.model.Card;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe utilisée pour résoudre les égalités entre joueurs ayant la même combinaison de poker.
 * <p>
 * Cette classe compare les joueurs en fonction de leur main et de la combinaison qu'ils ont obtenue,
 * et détermine le gagnant en cas d'égalité.
 *
 * @author matteo
 */
public class TieResolver {

    /**
     * Résout une égalité entre plusieurs joueurs ayant la même combinaison de poker.
     *
     * @param players la liste des joueurs ayant la même combinaison
     * @return le joueur gagnant après résolution de l'égalité
     * @throws IllegalArgumentException si le type de combinaison est inconnu
     */
    public Player resolveTie(List<Player> players) {
        Combo comboType = players.getFirst().getWinnerCombo();

        return switch (comboType) {
            case ROYAL_FLUSH, STRAIGHT_FLUSH, FLUSH, STRAIGHT -> compareBySuit(players);
            case FULL_HOUSE -> compareFullHouse(players);
            case THREE_OF_A_KIND -> compareThreeOfAKind(players);
            case TWO_PAIR -> compareTwoPair(players);
            case ONE_PAIR -> compareOnePair(players);
            case HIGH_CARD -> compareHighCard(players);
            default -> throw new IllegalArgumentException("Unknown combo type: " + comboType);
        };
    }

    /**
     * Compare les joueurs en fonction de la couleur de leur main.
     *
     * @param players la liste des joueurs à comparer
     * @return le joueur avec la meilleure couleur
     */
    private Player compareBySuit(List<Player> players) {
        return players.stream()
                .max(Comparator.comparing(player -> player.getHand().getCards().stream()
                        .max(Comparator.comparing(card -> card.getSuit().getLevel()))
                        .orElseThrow(() -> new IllegalStateException("Empty hand"))))
                .orElseThrow(() -> new IllegalStateException("No winner found"));
    }

    /**
     * Compare les joueurs ayant un full en fonction de la valeur de leur brelan, puis de leur paire.
     *
     * @param players la liste des joueurs à comparer
     * @return le joueur avec le meilleur full
     */
    private Player compareFullHouse(List<Player> players) {
        return players.stream()
                .max(Comparator
                        .comparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) == 3)
                                .mapToInt(Card::getValue)
                                .max().orElse(0))
                        .thenComparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) == 2)
                                .mapToInt(Card::getValue)
                                .max().orElse(0)))
                .orElseThrow(() -> new IllegalStateException("No winner found"));
    }

    /**
     * Compare les joueurs ayant un brelan en fonction de la valeur de leur brelan, puis de leurs kicker.
     *
     * @param players la liste des joueurs à comparer
     * @return le joueur avec le meilleur brelan
     */
    private Player compareThreeOfAKind(List<Player> players) {
        return players.stream()
                .max(Comparator
                        .comparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) == 3)
                                .mapToInt(Card::getValue)
                                .max().orElse(0))
                        .thenComparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) != 3)
                                .mapToInt(Card::getValue)
                                .sorted()
                                .skip(1)
                                .max().orElse(0)))
                .orElseThrow(() -> new IllegalStateException("No winner found"));
    }

    /**
     * Compare les joueurs ayant une double paire en fonction de la valeur de leurs paires,
     * puis du kicker.
     *
     * @param players la liste des joueurs à comparer
     * @return le joueur avec la meilleure double paire
     */
    private Player compareTwoPair(List<Player> players) {
        return players.stream()
                .max(Comparator
                        .comparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) == 2)
                                .mapToInt(Card::getValue)
                                .max().orElse(0))
                        .thenComparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) == 2)
                                .mapToInt(Card::getValue)
                                .sorted()
                                .skip(1)
                                .max().orElse(0))
                        .thenComparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) == 1)
                                .mapToInt(Card::getValue)
                                .max().orElse(0)))
                .orElseThrow(() -> new IllegalStateException("No winner found"));
    }

    /**
     * Compare les joueurs ayant une paire en fonction de la valeur de leur paire,
     * puis de leurs kicker.
     *
     * @param players la liste des joueurs à comparer
     * @return le joueur avec la meilleure paire
     */
    private Player compareOnePair(List<Player> players) {
        return players.stream()
                .max(Comparator
                        .comparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) == 2)
                                .mapToInt(Card::getValue)
                                .max().orElse(0))
                        .thenComparing((Player player) -> player.getHand().getCards().stream()
                                .filter(card -> Collections.frequency(player.getHand().getCards(), card) == 1)
                                .mapToInt(Card::getValue)
                                .sorted()
                                .skip(1)
                                .max().orElse(0)))
                .orElseThrow(() -> new IllegalStateException("No winner found"));
    }

    /**
     * Compare les joueurs ayant une carte haute en fonction de la valeur de leur carte la plus haute,
     * puis des cartes suivantes si nécessaire.
     *
     * @param players la liste des joueurs à comparer
     * @return le joueur avec la meilleure carte haute
     */
    private Player compareHighCard(List<Player> players) {
        return players.stream()
                .max(Comparator.comparing((Player player) -> player.getHand().getCards().stream()
                                .mapToInt(Card::getValue)
                                .max().orElse(0))
                        .thenComparing((Player player) -> player.getHand().getCards().stream()
                                .mapToInt(Card::getValue)
                                .sorted()
                                .skip(1)
                                .max().orElse(0)))
                .orElseThrow(() -> new IllegalStateException("No winner found"));
    }
}