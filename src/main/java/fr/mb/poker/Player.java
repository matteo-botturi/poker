package fr.mb.poker;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private final String NAME;
    private final Hand HAND;
    private int points = 0;
    private String winnerCombo = "";

    public Player(String name) {
        this.NAME = name;
        this.HAND = new Hand();
    }

    @Override
    public String toString() {
        StringBuilder playerInfo = new StringBuilder();
        playerInfo.append("Player: ").append(NAME).append("\n");
        playerInfo.append("Hand: ");
        for (Card card : HAND.getCARDS())
            playerInfo.append(card.toString()).append(" , ");
        playerInfo.setLength(playerInfo.length() - 3);
        return playerInfo.toString();
    }
}