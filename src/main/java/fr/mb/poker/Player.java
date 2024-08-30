package fr.mb.poker;

import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
public class Player {
    private final String name;
    private final Hand hand;
    @Setter
    private int points = 0;
    @Setter
    private String winnerCombo = "";

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    @Override
    public String toString() {
        String handCards = hand.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.joining(" , "));
        return "Player: " + name + "\n" +
                "Hand: " + handCards;
    }
}