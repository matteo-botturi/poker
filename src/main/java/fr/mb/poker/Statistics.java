package fr.mb.poker;

import lombok.Getter;

public class Statistics{
    private static final double[] TOTAL_METHODS = new double[] {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    @Getter
    private static int numberOfGames = 0;

    public static double getCombo(int i) {
        return TOTAL_METHODS[i];
    }

    public static void updateCombo(int i) {
        Statistics.TOTAL_METHODS[i] += 1.0;
    }

    public static void updateNumberOfGames(int numberOfPlayers) {
        Statistics.numberOfGames += numberOfPlayers;
    }
}