package fr.mb.poker.model;

import fr.mb.poker.enumeration.Combo;
import fr.mb.poker.outils.CalculateScore;
import fr.mb.poker.outils.Statistics;
import java.util.*;

public class Game{
    private static final int NUMBER_OF_CARDS = 5;
    private final List<Player> players;
    private final Deck deck;

    public Game(int numberOfPlayers) {
        players = new ArrayList<>(numberOfPlayers);
        deck = new Deck(numberOfPlayers);
        for (int i = 1; i <= numberOfPlayers; i++)
            players.add(new Player("Player " + i));
        Statistics.updateNumberOfGames();
    }

    public void start() {
        dealCards();
        determineScore();
        printOverview();
        andTheWinnerIs();
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < NUMBER_OF_CARDS; i++)
                player.getHand().addCardToHand(deck.dealCard());
            player.getHand().sortHand();
        }
    }

    private void determineScore() {
        for (Player player : players) {
            CalculateScore scoreCalculator = new CalculateScore(player.getHand());
            Combo bestCombo = scoreCalculator.getBestCombo();
            int playerScore = scoreCalculator.getBestScore();
            player.setPoints(playerScore);
            player.setWinnerCombo(bestCombo.getDescription());
            Statistics.updateCombo(bestCombo);
        }
    }

    public void printOverview() {
        players.forEach(System.out::println);
    }

    private void andTheWinnerIs() {
        players.stream()
                .max(Comparator.comparingInt(Player::getPoints))
                .ifPresent(winner -> {
                    System.out.println("\nAND THE WINNER IS...\n");
                    System.out.println(winner);
                    System.out.printf("%nthat wins with a %s combo!!!%n", winner.getWinnerCombo());
                });
    }
}