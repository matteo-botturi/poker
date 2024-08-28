package fr.mb.poker;

public class CalculateScore {
    private final int[] TOTAL_METHODS = new int[10];

    public CalculateScore(Hand hand) {
        TOTAL_METHODS[0] = HandEvaluator.highCard(hand);
        TOTAL_METHODS[1] = HandEvaluator.onePair(hand);
        TOTAL_METHODS[2] = HandEvaluator.twoPair(hand);
        TOTAL_METHODS[3] = HandEvaluator.threeOfAKind(hand);
        TOTAL_METHODS[4] = HandEvaluator.straight(hand);
        TOTAL_METHODS[5] = HandEvaluator.flush(hand);
        TOTAL_METHODS[6] = HandEvaluator.fullHouse(hand);
        TOTAL_METHODS[7] = HandEvaluator.fourOfAKind(hand);
        TOTAL_METHODS[8] = HandEvaluator.straightFlush(hand);
        TOTAL_METHODS[9] = HandEvaluator.royalFlush(hand);
    }

    public int getTotalMethods(int i) {
        return TOTAL_METHODS[i];
    }
}