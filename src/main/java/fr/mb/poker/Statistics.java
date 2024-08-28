package fr.mb.poker;

public class Statistics{
    private static double[] totalMethods = new double[] {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    private static int numberOfGames = 0;

    public static double getCombo(int i) {
        return totalMethods[i];
    }

    public static void updateCombo(int i) {
        Statistics.totalMethods[i] += 1.0;
    }

    public static int getNumberOfGames() {
        return numberOfGames;
    }

    public static void updateNumberOfGames(int numberOfPlayers) {
        Statistics.numberOfGames += numberOfPlayers;
    }
}
