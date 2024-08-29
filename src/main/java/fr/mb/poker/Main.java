package fr.mb.poker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int numPlayers = getNumberOfPlayers(scanner);
        while (playAgain) {
            try {
                Game game = new Game(numPlayers);
                game.start();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.print("\nDo you want to play again? (y/n)");
            playAgain = scanner.next().equalsIgnoreCase("y");
        }
        System.out.println("Thank you, Bye!\n\n");
        seeStats();
    }

    private static void seeStats() {
        for(int i = 0; i < 10; i++) {
            System.out.println(Rules.stringCombo(i) + ": " + percentage(i) + " %");
        }
    }

    private static double percentage(int i) {
        double result = Statistics.getCombo(i)*100.0/Statistics.getNumberOfGames();
        return Math.round(result*100.0)/100.0;
    }

    private static int getNumberOfPlayers(Scanner scanner) {
        System.out.print("Insert players (2-4): ");
        int numPlayers = scanner.nextInt();
        if (numPlayers < 2 || numPlayers > 4)
            throw new IllegalArgumentException("Number of players not valid.");
        return numPlayers;
    }
}