package fr.mb.poker.exception;

/**
 * @author matteo
 */
public class DeckEmptyException extends RuntimeException {

    public DeckEmptyException() {
        super("The Deck is empty");
    }
}