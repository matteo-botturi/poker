package fr.mb.poker;

import fr.mb.poker.enumeration.ScoreType;
import lombok.Getter;
import java.util.EnumMap;
import java.util.Map;

public class CalculateScore {
    private final Map<ScoreType, Integer> scores = new EnumMap<>(ScoreType.class);
    private final Hand hand;
    @Getter
    private ScoreType bestCombo;
    @Getter
    private int bestScore;

    public CalculateScore(Hand hand) {
        this.hand = hand;
        calculateAllScores();
        determineBestScore();
    }

    private void calculateAllScores() {
        scores.put(ScoreType.HIGH_CARD, HandEvaluator.highCard(hand));
        scores.put(ScoreType.ONE_PAIR, HandEvaluator.onePair(hand));
        scores.put(ScoreType.TWO_PAIR, HandEvaluator.twoPair(hand));
        scores.put(ScoreType.THREE_OF_A_KIND, HandEvaluator.threeOfAKind(hand));
        scores.put(ScoreType.STRAIGHT, HandEvaluator.straight(hand));
        scores.put(ScoreType.FLUSH, HandEvaluator.flush(hand));
        scores.put(ScoreType.FULL_HOUSE, HandEvaluator.fullHouse(hand));
        scores.put(ScoreType.FOUR_OF_A_KIND, HandEvaluator.fourOfAKind(hand));
        scores.put(ScoreType.STRAIGHT_FLUSH, HandEvaluator.straightFlush(hand));
        scores.put(ScoreType.ROYAL_FLUSH, HandEvaluator.royalFlush(hand));
    }

    private void determineBestScore() {
        bestCombo = scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(ScoreType.HIGH_CARD);

        bestScore = scores.getOrDefault(bestCombo, 0);
    }

    public int getScore(ScoreType type) {
        return scores.getOrDefault(type, 0);
    }
}