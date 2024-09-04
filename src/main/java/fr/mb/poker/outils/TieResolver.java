package fr.mb.poker.outils;

import fr.mb.poker.enumeration.Combo;
import fr.mb.poker.model.Player;
import fr.mb.poker.model.Card;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TieResolver {

    public Player resolveTie(List<Player> players) {
        Combo comboType = players.get(0).getWinnerCombo();

        switch (comboType) {
            case ROYAL_FLUSH:
            case STRAIGHT_FLUSH:
            case FLUSH:
            case STRAIGHT:
                return compareBySuit(players);
            case FULL_HOUSE:
                return compareFullHouse(players);
            case THREE_OF_A_KIND:
                return compareThreeOfAKind(players);
            case TWO_PAIR:
                return compareTwoPair(players);
            case ONE_PAIR:
                return compareOnePair(players);
            case HIGH_CARD:
                return compareHighCard(players);
            default:
                throw new IllegalArgumentException("Unknown combo type: " + comboType);
        }
    }

    private Player compareBySuit(List<Player> players) {
        return players.stream()
                .max(Comparator.comparing(player -> player.getHand().getCards().stream()
                        .max(Comparator.comparing(card -> card.getSuit().getPriority()))
                        .orElseThrow(() -> new IllegalStateException("Empty hand"))))
                .orElseThrow(() -> new IllegalStateException("No winner found"));
    }

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