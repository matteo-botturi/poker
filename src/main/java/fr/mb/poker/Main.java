package fr.mb.poker;

import fr.mb.poker.model.Game;
import fr.mb.poker.outils.Statistics;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe principale qui lance l'application et gère l'interaction avec l'utilisateur.
 * <p>
 * Cette classe permet à l'utilisateur de démarrer une partie, de spécifier le nombre de joueurs,
 * et de rejouer autant de fois qu'il le souhaite. À la fin, les statistiques de jeu sont affichées.
 *
 * @author matteo
 */
public class Main {

    /**
     * Méthode principale. Le point d’entrée de l’application
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean playAgain = true;
            int numPlayers = getNumberOfPlayers(scanner);

            while (playAgain) {
                try {
                    Game game = new Game(numPlayers);
                    game.start();
                } catch (IllegalArgumentException e) {
                    System.out.println("Erreur : " + e.getMessage());
                }
                System.out.print("\nVoulez-vous rejouer ? (y/n) : ");
                playAgain = askPlayAgain(scanner);
            }
            System.out.println("Merci, au revoir !\n\n");
            Statistics.printStats(numPlayers);
        }
    }

    /**
     * Demande à l'utilisateur de saisir le nombre de joueurs pour la partie,
     * et vérifie que l'entrée est valide (entre 2 et 4 joueurs).
     *
     * @param scanner l'objet Scanner utilisé pour lire les entrées de l'utilisateur
     * @return le nombre de joueurs validé
     */
    private static int getNumberOfPlayers(Scanner scanner) {
        int numPlayers = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Veuillez entrer le nombre de joueurs (2-4) : ");
            try {
                numPlayers = scanner.nextInt();
                if (numPlayers < 2 || numPlayers > 4)
                    System.out.println("Nombre de joueurs non valide. Veuillez entrer un nombre entre 2 et 4.");
                else
                    validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide entre 2 et 4.");
                scanner.next();
            }
        }
        return numPlayers;
    }

    /**
     * Demande à l'utilisateur s'il souhaite rejouer, en validant l'entrée pour "y" ou "n".
     *
     * @param scanner l'objet Scanner utilisé pour lire les entrées de l'utilisateur
     * @return true si l'utilisateur veut rejouer, false sinon
     */
    private static boolean askPlayAgain(Scanner scanner) {
        String response = scanner.next().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.print("Entrée invalide. Voulez-vous rejouer ? (y/n) : ");
            response = scanner.next().trim().toLowerCase();
        }
        return response.equals("y");
    }
}