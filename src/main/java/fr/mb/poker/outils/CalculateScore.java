package fr.mb.poker.outils;

import fr.mb.poker.enumeration.Combo;
import fr.mb.poker.model.Hand;
import lombok.Getter;
import java.util.EnumMap;
import java.util.Map;

/**
 * Classe utilisée pour calculer et déterminer la meilleure combinaison de cartes
 * dans une main, ainsi que le score associé.
 *
 * @author matteo
 */
public class CalculateScore {
    /** Map associant chaque combinaison à son score pour une main donnée. */
    private final Map<Combo, Integer> scores = new EnumMap<>(Combo.class);

    @Getter
    private Combo bestCombo;
    @Getter
    private int bestScore;

    /**
     * Constructeur qui calcule toutes les combinaisons possibles
     * et détermine la meilleure combinaison et son score.
     *
     * @param hand la main de poker à évaluer
     */
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

    /**
     * Détermine la meilleure combinaison et son score en comparant toutes les combinaisons calculées.
     */
    private void determineBestScore() {
        Map.Entry<Combo, Integer> bestEntry = scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(Map.entry(Combo.HIGH_CARD, 0));

        this.bestCombo = bestEntry.getKey();
        this.bestScore = bestEntry.getValue();
    }
}