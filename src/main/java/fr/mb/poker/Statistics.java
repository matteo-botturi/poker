package fr.mb.poker;

import lombok.Getter;

public class Statistics{
    private static final double[] comboList = new double[] {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    @Getter
    private static int numberOfGames = 0;

    public static double getCombo(int i) {
        return comboList[i];
    }

    public static void updateCombo(int i) {
        comboList[i] += 1.0;
    }

    public static void updateNumberOfGames(int numberOfPlayers) {
        numberOfGames += numberOfPlayers;
    }
}