package fr.mb.poker.outils;

import fr.mb.poker.enumeration.Combo;
import fr.mb.poker.model.Hand;
import lombok.Getter;
import java.util.EnumMap;
import java.util.Map;

public class CalculateScore {
    private final Map<Combo, Integer> scores = new EnumMap<>(Combo.class);
    @Getter
    private Combo bestCombo;
    @Getter
    private int bestScore;

    public CalculateScore(Hand hand) {
        calculateAllScores(hand);
        determineBestScore();
    }

    private void calculateAllScores(Hand hand) {
        scores.put(Combo.HIGH_CARD, HandEvaluator.highCard(hand));
        scores.put(Combo.ONE_PAIR, HandEvaluator.onePair(hand));
        scores.put(Combo.TWO_PAIR, HandEvaluator.twoPair(hand));
        scores.put(Combo.THREE_OF_A_KIND, HandEvaluator.threeOfAKind(hand));
        scores.put(Combo.STRAIGHT, HandEvaluator.straight(hand));
        scores.put(Combo.FLUSH, HandEvaluator.flush(hand));
        scores.put(Combo.FULL_HOUSE, HandEvaluator.fullHouse(hand));
        scores.put(Combo.FOUR_OF_A_KIND, HandEvaluator.fourOfAKind(hand));
        scores.put(Combo.STRAIGHT_FLUSH, HandEvaluator.straightFlush(hand));
        scores.put(Combo.ROYAL_FLUSH, HandEvaluator.royalFlush(hand));
    }

    private void determineBestScore() {
        Map.Entry<Combo, Integer> bestEntry = scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(Map.entry(Combo.HIGH_CARD, 0));

        this.bestCombo = bestEntry.getKey();
        this.bestScore = bestEntry.getValue();
    }
}