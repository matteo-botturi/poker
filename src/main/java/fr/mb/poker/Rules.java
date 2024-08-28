package fr.mb.poker;

public abstract class Rules {
    String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades"};
    String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public static String stringCombinations(int determineScore) {
        switch(determineScore) {
            case 0: return "High card";
            case 1: return "One Pair";
            case 2: return "Two Pair";
            case 3: return "Three of a kind";
            case 4: return "Straight";
            case 5: return "Flush";
            case 6: return "Full House";
            case 7: return "Four of a kind";
            case 8: return "Straight Flush";
            case 9: return "Royal Flush";
            default: throw new IndexOutOfBoundsException();
        }
    }

}
