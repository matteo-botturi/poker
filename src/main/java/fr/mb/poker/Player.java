package fr.mb.poker;

public class Player {
    private String name;
    private Hand hand;
    private int points = 0;
    private String winnerCombo = "";

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getWinnerCombo() {
        return winnerCombo;
    }

    public void setWinnerCombo(String winnerCombo) {
        this.winnerCombo = winnerCombo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder playerInfo = new StringBuilder();
        playerInfo.append("Player: ").append(name).append("\n");
        playerInfo.append("Hand: ");
        for (Card card : hand.getCards())
            playerInfo.append(card.toString()).append(" , ");
        playerInfo.setLength(playerInfo.length() - 3);
        return playerInfo.toString();
    }
}
