package fr.mb.poker;

import fr.mb.poker.model.Game;
import fr.mb.poker.outils.Statistics;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

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
            Statistics.printStats(numPlayers);
        } finally {
            scanner.close();
        }
    }

    private static int getNumberOfPlayers(Scanner scanner) {
        int numPlayers = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Insert number of players (2-4): ");
            try {
                numPlayers = scanner.nextInt();
                if (numPlayers < 2 || numPlayers > 4) {
                    System.out.println("Number of players not valid. Please enter a number between 2 and 4.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number between 2 and 4.");
                scanner.next();
            }
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