package fr.mb.poker;

import java.util.*;

public class Game{
    private final List<Player> PLAYERS;
    private final Deck DECK;

    public Game(int numberOfPlayers) {
        PLAYERS = new ArrayList<>(numberOfPlayers);
        DECK = new Deck(numberOfPlayers);
        for (int i = 1; i <= numberOfPlayers; i++)
            PLAYERS.add(new Player("Player " + i));
        Statistics.updateNumberOfGames(numberOfPlayers);
    }

    public void start() {
        dealerCards();
        printOverview();
        determineScore();
        andTheWinnerIs();
    }

    private void andTheWinnerIs() {
        Optional<Player> winner = PLAYERS.stream().max(Comparator.comparingInt(Player::getPoints));
        if(winner.isPresent()) {
            Player winningPlayer = winner.get();
            System.out.println("\nAND THE WINNER IS...\n");
            System.out.println(winningPlayer);
            System.out.printf("%nthat wins with a %s combo!!!", winningPlayer.getWinnerCombo());
        }
    }

    private void determineScore() {
        int maxScore = 0;
        int winningCombo = -1;

        for (Player player : PLAYERS) {
            CalculateScore score = new CalculateScore(player.getHAND());
            for (int i = 0; i < 10; i++) {
                int comboScore = score.getTotalMethods(i);
                if (comboScore > maxScore) {
                    maxScore = comboScore;
                    winningCombo = i;
                }
            }
            player.setPoints(maxScore);
            player.setWinnerCombo(Rules.stringCombinations(winningCombo));
            Statistics.updateCombo(winningCombo);
        }
    }

    private void dealerCards() {
        for (Player player : PLAYERS) {
            for (int i = 0; i < 5; i++)
                player.getHAND().addCardToHand(DECK.dealCard());
            Collections.sort(player.getHAND().getCARDS(), new CardValueComparator());
        }
    }

    public void printOverview() {
        for (Player giocatore : PLAYERS) {
            System.out.println(giocatore);
        }
    }
}