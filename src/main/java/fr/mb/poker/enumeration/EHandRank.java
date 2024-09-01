package fr.mb.poker.enumeration;

import lombok.Getter;

public enum EHandRank {
    HIGH_CARD(0, "High card"),
    ONE_PAIR(1, "One Pair"),
    TWO_PAIR(2, "Two Pair"),
    THREE_OF_A_KIND(3, "Three of a kind"),
    STRAIGHT(4, "Straight"),
    FLUSH(5, "Flush"),
    FULL_HOUSE(6, "Full House"),
    FOUR_OF_A_KIND(7, "Four of a kind"),
    STRAIGHT_FLUSH(8, "Straight Flush"),
    ROYAL_FLUSH(9, "Royal Flush");

    @Getter
    private final int score;
    @Getter
    private final String description;

    EHandRank(int score, String description) {
        this.score = score;
        this.description = description;
    }

    public static String stringCombo(int determineScore) {
        for (EHandRank rank : EHandRank.values()) {
            if (rank.getScore() == determineScore) {
                return rank.getDescription();
            }
        }
        throw new IndexOutOfBoundsException("Score not valid: " + determineScore);
    }
}