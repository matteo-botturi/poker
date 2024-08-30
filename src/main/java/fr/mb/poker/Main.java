package fr.mb.poker;

import java.util.Scanner;

import java.util.Scanner;

public class Main {

    private static final int NUM_COMBINATIONS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            boolean playAgain = true;
            int numPlayers = getNumberOfPlayers(scanner);

            while (playAgain) {
                try {
                    Game game = new Game(numPlayers);
                    game.start();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                System.out.print("\nDo you want to play again? (y/n): ");
                playAgain = askPlayAgain(scanner);
            }

            System.out.println("Thank you, Bye!\n\n");
            seeStats();
        } finally {
            scanner.close(); // Assicurati di chiudere lo Scanner
        }
    }

    private static void seeStats() {
        for (int i = 0; i < NUM_COMBINATIONS; i++) {
            System.out.println(Rules.stringCombo(i) + ": " + percentage(i) + " %");
        }
    }

    private static double percentage(int i) {
        double result = Statistics.getCombo(i) * 100.0 / Statistics.getNumberOfGames();
        return Math.round(result * 100.0) / 100.0;
    }

    private static int getNumberOfPlayers(Scanner scanner) {
        System.out.print("Insert number of players (2-4): ");
        int numPlayers = scanner.nextInt();
        if (numPlayers < 2 || numPlayers > 4) {
            throw new IllegalArgumentException("Number of players not valid.");
        }
        return numPlayers;
    }

    private static boolean askPlayAgain(Scanner scanner) {
        String response = scanner.next().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.print("Invalid input. Do you want to play again? (y/n): ");
            response = scanner.next().trim().toLowerCase();
        }
        return response.equals("y");
    }
}
