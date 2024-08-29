package fr.mb.poker;

public abstract class Rules {
    String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades"};
    String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public static String stringCombo(int determineScore) {
        return switch(determineScore) {
            case 0 -> "High card";
            case 1 -> "One Pair";
            case 2 -> "Two Pair";
            case 3 -> "Three of a kind";
            case 4 -> "Straight";
            case 5 -> "Flush";
            case 6 -> "Full House";
            case 7 -> "Four of a kind";
            case 8 -> "Straight Flush";
            case 9 -> "Royal Flush";
            default -> throw new IndexOutOfBoundsException();
        };
    }
}