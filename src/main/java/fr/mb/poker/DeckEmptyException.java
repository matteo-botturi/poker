package fr.mb.poker;

public class DeckEmptyException extends RuntimeException {

    public DeckEmptyException() {
        super("The Deck is empty");
    }
}