package fr.mb.poker.model;

import fr.mb.poker.enumeration.Combo;
import fr.mb.poker.outils.CalculateScore;
import fr.mb.poker.outils.Statistics;
import fr.mb.poker.outils.TieResolver;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe principale représentant une partie de poker.
 * <p>
 * La classe gère l'initialisation des joueurs, la distribution des cartes, le calcul des scores,
 * la résolution des égalités et la détermination du gagnant.
 *
 * @author matteo
 */
public class Game {
    /** Le nombre de cartes distribué à chaque joueur. */
    private static final int NUMBER_OF_CARDS = 5;

    /** La liste des joueurs participant à la partie. */
    @Setter
    @Getter
    private List<Player> players;

    /** Le paquet de cartes utilisé pour distribuer les cartes. */
    private final Deck deck;

    /** Le résolveur d'égalités. */
    private final TieResolver tieResolver;

    /**
     * Constructeur qui initialise une nouvelle partie de poker avec un nombre donné de joueurs.
     * <p>
     * Les joueurs sont initialisés, le paquet est prêt à être utilisé, et les statistiques
     * globales sont mises à jour pour refléter une nouvelle partie.
     *
     * @param numberOfPlayers le nombre de joueurs participant à la partie
     */
    public Game(int numberOfPlayers) {
        this.players = new ArrayList<>(numberOfPlayers);
        this.deck = new Deck(numberOfPlayers);
        this.tieResolver = new TieResolver();
        initializePlayers(numberOfPlayers);
        Statistics.updateNumberOfGames();
    }

    /**
     * Initialise les joueurs de la partie en leur attribuant un nom.
     *
     * @param numberOfPlayers le nombre de joueurs à initialiser
     */
    private void initializePlayers(int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player("Player " + i));
        }
    }

    /**
     * Démarre la partie de poker, en distribuant les cartes, en déterminant les scores,
     * en affichant un aperçu de la partie, puis en déclarant le gagnant.
     */
    public void start() {
        dealCards();
        determineScore();
        printOverview();
        declareWinner(andTheWinnerIs());
    }

    /**
     * Distribue les cartes à tous les joueurs et trie leurs mains.
     */
    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < NUMBER_OF_CARDS; i++)
                player.getHand().addCard(deck.dealCard());
            player.getHand().sortHand();
        }
    }

    /**
     * Calcule les scores pour chaque joueur et met à jour les statistiques
     * pour la combinaison gagnante de chaque joueur.
     */
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

    /**
     * Affiche un aperçu des mains et des scores de chaque joueur.
     */
    public void printOverview() {
        players.forEach(System.out::println);
    }

    /**
     * Détermine le gagnant de la partie en fonction des scores et des priorités des combinaisons.
     * En cas d'égalité, un résolveur d'égalités est utilisé pour départager les joueurs.
     *
     * @return le joueur gagnant
     */
    public Player andTheWinnerIs() {
        List<Player> topPlayers = players.stream()
                .max(Comparator
                        .comparingInt(Player::getPoints)
                        .thenComparing(player -> player.getWinnerCombo().getPriority(), Comparator.reverseOrder()))
                .stream()
                .collect(Collectors.toList());

        return topPlayers.size() == 1 ? topPlayers.getFirst() : tieResolver.resolveTie(topPlayers);
    }

    /**
     * Déclare et affiche le joueur gagnant avec sa combinaison.
     *
     * @param winner le joueur gagnant
     */
    private void declareWinner(Player winner) {
        System.out.println("\nAND THE WINNER IS...\n");
        System.out.println(winner);
        System.out.printf("%nthat wins with a %s combo!!!%n", winner.getWinnerCombo().getDescription());
    }
}