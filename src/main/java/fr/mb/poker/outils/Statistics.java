package fr.mb.poker.outils;

import fr.mb.poker.enumeration.Combo;
import lombok.Getter;
import java.util.EnumMap;
import java.util.Map;

public class Statistics {
    private static final Map<Combo, Double> comboList = new EnumMap<>(Combo.class);
    @Getter
    private static int numberOfGames = 0;

    static {
        for (Combo combo : Combo.values())
            comboList.put(combo, 0.0);
    }

    public static synchronized void updateCombo(Combo combo) {
        comboList.put(combo, comboList.get(combo) + 1.0);
    }

    public static synchronized void updateNumberOfGames() {
        numberOfGames += 1;
    }

    public static synchronized double getCombo(Combo combo) {
        return comboList.get(combo);
    }

    public static synchronized double getComboPercentage(Combo combo, int numberOfPlayers) {
        int totalHands = numberOfGames * numberOfPlayers;
        if (totalHands == 0)
            return 0.0;
        double result = getCombo(combo) * 100.0 / totalHands;
        return Math.round(result * 100.0) / 100.0;
    }

    public static synchronized void printStats(int numberOfPlayers) {
        int totalHands = numberOfGames * numberOfPlayers;
        System.out.printf("Game Statistics across %d %s (%d total hands):\n", numberOfGames, numberOfGames == 1 ? "game" : "games", totalHands);
        for (Combo combo : Combo.values())
            System.out.println(combo.getDescription() + ": " + getComboPercentage(combo, numberOfPlayers) + " %");
    }
}