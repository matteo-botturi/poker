package fr.mb.poker;

public class CalculateScore {
    private int[] totalMethods = new int[10];

    public CalculateScore(Hand hand) {
        totalMethods[0] = HandEvaluator.highCard(hand);
        totalMethods[1] = HandEvaluator.onePair(hand);
        totalMethods[2] = HandEvaluator.twoPair(hand);
        totalMethods[3] = HandEvaluator.threeOfAKind(hand);
        totalMethods[4] = HandEvaluator.straight(hand);
        totalMethods[5] = HandEvaluator.flush(hand);
        totalMethods[6] = HandEvaluator.fullHouse(hand);
        totalMethods[7] = HandEvaluator.fourOfAKind(hand);
        totalMethods[8] = HandEvaluator.straightFlush(hand);
        totalMethods[9] = HandEvaluator.royalFlush(hand);
    }

    public int getTotalMethods(int i) {
        return totalMethods[i];
    }
}