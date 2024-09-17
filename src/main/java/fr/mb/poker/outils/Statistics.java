package fr.mb.poker.outils;

import fr.mb.poker.enumeration.Combo;
import lombok.Getter;
import java.util.EnumMap;
import java.util.Map;

/**
 * Classe utilitaire pour suivre les statistiques des combinaisons.
 * <p>
 * Cette classe enregistre le nombre de fois où chaque combinaison est obtenue au cours des parties,
 * ainsi que le nombre total de parties jouées. Elle permet de calculer les pourcentages
 * de chaque combinaison par rapport au nombre total de mains jouées.
 *
 * @author matteo
 *
 */
public class Statistics {
    /** Map associant chaque combinaison de poker à son nombre d'occurrences. */
    private static final Map<Combo, Double> comboList = new EnumMap<>(Combo.class);

    /** Le nombre total de parties jouées. */
    @Getter
    private static int numberOfGames = 0;

    /** Bloc statique pour initialiser la map des combinaisons avec des valeurs initiales de 0. */
    static {
        for (Combo combo : Combo.values())
            comboList.put(combo, 0.0);
    }

    /**
     * Met à jour le compteur pour une combinaison spécifique lorsqu'elle est obtenue dans une partie.
     *
     * @param combo la combinaison à mettre à jour
     */
    public static synchronized void updateCombo(Combo combo) {
        comboList.put(combo, comboList.get(combo) + 1.0);
    }

    /**
     * Incrémente le nombre total de parties jouées.
     */
    public static synchronized void updateNumberOfGames() {
        numberOfGames += 1;
    }

    /**
     * Retourne le nombre d'occurrences d'une combinaison spécifique.
     *
     * @param combo la combinaison à interroger
     * @return le nombre d'occurrences de cette combinaison
     */
    public static synchronized double getCombo(Combo combo) {
        return comboList.get(combo);
    }

    /**
     * Calcule le pourcentage d'apparition d'une combinaison spécifique par rapport au nombre total de mains jouées.
     *
     * @param combo la combinaison à calculer
     * @param numberOfPlayers le nombre de joueurs par partie
     * @return le pourcentage d'apparition de cette combinaison
     */
    public static synchronized double getComboPercentage(Combo combo, int numberOfPlayers) {
        int totalHands = numberOfGames * numberOfPlayers;
        if (totalHands == 0)
            return 0.0;
        double result = getCombo(combo) * 100.0 / totalHands;
        return Math.round(result * 100.0) / 100.0;
    }

    /**
     * Affiche les statistiques des combinaisons de poker, incluant le nombre de parties jouées
     * et le pourcentage d'apparition de chaque combinaison.
     *
     * @param numberOfPlayers le nombre de joueurs par partie
     */
    public static synchronized void printStats(int numberOfPlayers) {
        int totalHands = numberOfGames * numberOfPlayers;
        System.out.printf("Statistiques du jeu sur %d %s (%d mains totales) :\n", numberOfGames, numberOfGames == 1 ? "partie" : "parties", totalHands);
        for (Combo combo : Combo.values())
            System.out.println(combo.getDescription() + " : " + getComboPercentage(combo, numberOfPlayers) + " %");
    }
}