package fr.mb.poker;

import fr.mb.poker.enumeration.ScoreType;

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
        Statistics.updateNumberOfGames(numberOfPlayers);
    }

    public void start() {
        dealerCards();
        printOverview();
        determineScore();
        andTheWinnerIs();
    }

    private void andTheWinnerIs() {
        Optional<Player> winner = players.stream().max(Comparator.comparingInt(Player::getPoints));
        winner.ifPresent(player -> {
            System.out.println("\nAND THE WINNER IS...\n");
            System.out.println(player);
            System.out.printf("%nthat wins with a %s combo!!!%n", player.getWinnerCombo());
        });
    }

    private void determineScore() {
        for (Player player : players) {
            CalculateScore score = new CalculateScore(player.getHand());
            int maxScore = 0;
            ScoreType winningCombo = ScoreType.HIGH_CARD;

            for (ScoreType combo : ScoreType.values()) {
                int comboScore = score.getScore(combo);
                if (comboScore > maxScore) {
                    maxScore = comboScore;
                    winningCombo = combo;
                }
            }
            player.setPoints(maxScore);
            player.setWinnerCombo(Rules.stringCombo(winningCombo.ordinal()));
            Statistics.updateCombo(winningCombo.ordinal());
        }
    }

    private void dealerCards() {
        for (Player player : players) {
            for (int i = 0; i < NUMBER_OF_CARDS; i++)
                player.getHand().addCardToHand(deck.dealCard());
            player.getHand().getCards().sort(new CardValueComparator());
        }
    }

    public void printOverview() {
        for (Player player : players) {
            System.out.println(player);
        }
    }
}