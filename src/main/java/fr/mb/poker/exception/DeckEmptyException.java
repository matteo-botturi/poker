package fr.mb.poker.exception;

public class DeckEmptyException extends RuntimeException {

    public DeckEmptyException() {
        super("The Deck is empty");
    }
}