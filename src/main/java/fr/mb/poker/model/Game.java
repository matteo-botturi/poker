package fr.mb.poker.model;

import fr.mb.poker.enumeration.Combo;
import fr.mb.poker.outils.CalculateScore;
import fr.mb.poker.outils.Statistics;
import fr.mb.poker.outils.TieResolver;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import java.util.stream.Collectors;

public class Game{
    private static final int NUMBER_OF_CARDS = 5;
    @Setter
    @Getter
    private List<Player> players;
    private final Deck deck;
    private final TieResolver tieResolver;

    public Game(int numberOfPlayers) {
        this.players = new ArrayList<>(numberOfPlayers);
        this.deck = new Deck(numberOfPlayers);
        this.tieResolver = new TieResolver();
        initializePlayers(numberOfPlayers);
        Statistics.updateNumberOfGames();
    }

    private void initializePlayers(int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player("Player " + i));
        }
    }

    public void start() {
        dealCards();
        determineScore();
        printOverview();
        declareWinner(andTheWinnerIs());
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < NUMBER_OF_CARDS; i++)
                player.getHand().addCard(deck.dealCard());
            player.getHand().sortHand();
        }
    }

    private void determineScore() {
        for (Player player : players) {
            CalculateScore scoreCalculator = new CalculateScore(player.getHand());
            Combo bestCombo = scoreCalculator.getBestCombo();
            int playerScore = scoreCalculator.getBestScore();
            player.setPoints(playerScore);
            player.setWinnerCombo(bestCombo);
            Statistics.updateCombo(bestCombo);
        }
    }

    public void printOverview() {
        players.forEach(System.out::println);
    }

    public Player andTheWinnerIs() {
        List<Player> topPlayers = players.stream()
                .max(Comparator
                        .comparingInt(Player::getPoints)
                        .thenComparing(player -> player.getWinnerCombo().getPriority(), Comparator.reverseOrder()))
                .stream()
                .collect(Collectors.toList());

        return topPlayers.size() == 1 ? topPlayers.get(0) : tieResolver.resolveTie(topPlayers);
    }

    private void declareWinner(Player winner) {
        System.out.println("\nAND THE WINNER IS...\n");
        System.out.println(winner);
        System.out.printf("%nthat wins with a %s combo!!!%n", winner.getWinnerCombo().getDescription());
    }
}