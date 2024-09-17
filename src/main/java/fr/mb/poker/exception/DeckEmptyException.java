package fr.mb.poker.exception;

/**
 * Exception levée lorsque le jeu de cartes (deck) est vide et qu'il n'y a plus de cartes à distribuer.
 * <p>
 * Cette exception est utilisée pour signaler un état d'erreur lorsqu'une tentative est faite
 * pour distribuer une carte d'un jeu vide.
 *</p>
 * @author matteo
 */
public class DeckEmptyException extends RuntimeException {

    /**
     * Constructeur par défaut qui initialise l'exception avec un message d'erreur par défaut.
     */
    public DeckEmptyException() {
        super("The Deck is empty");
    }
}