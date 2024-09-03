package fr.mb.poker.outils;

import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.model.Card;
import fr.mb.poker.model.Hand;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HandEvaluator {

    public static int flush(Hand hand) {
        List<Card> cards = hand.getCards();
        boolean isFlush = cards.stream().allMatch(card -> card.getSuit().equals(cards.get(0).getSuit()));
        return isFlush ? cards.stream().mapToInt(Card::getValue).sum() : 0;
    }

    public static int straight(Hand hand) {
        if (evaluateLowStraight(hand)){
            adjustAceForLowStraight(hand);
            return 15;
        }
        return evaluateStraight(hand);
    }

    private static void adjustAceForLowStraight(Hand hand) {
        hand.getCards().stream()
                .filter(card -> card.getRank() == Rank.ACE)
                .forEach(card -> card.setValue(1));
        hand.sortHand();
    }

    private static boolean isAcePresent(Hand hand) {
        return hand.getCards().stream().anyMatch(card -> card.getRank() == Rank.ACE);
    }

    private static boolean evaluateLowStraight(Hand hand) {
        List<Card> cards = hand.getCards();
        return cards.stream()
                .mapToInt(Card::getValue)
                .filter(value -> value >= 2 && value <= 5)
                .distinct()
                .count() == 4 && isAcePresent(hand);
    }

    public static boolean isConsecutive(Card courrentCard, Card nextCard) {
        return courrentCard.getValue() == nextCard.getValue() - 1;
    }

    public static int evaluateStraight(Hand hand) {
        List<Card> cards = hand.getCards();
        int score = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            Card currentCard = cards.get(i);
            Card nextCard = cards.get(i + 1);
            if (!isConsecutive(currentCard, nextCard))
                return 0;
            score += currentCard.getValue();
        }
        return score + cards.get(cards.size() - 1).getValue();
    }

    public static int straightFlush(Hand hand) {
        int straightScore = straight(hand);
        return (flush(hand) > 0 && straightScore > 0) ? straightScore : 0;
    }

    public static int royalFlush(Hand hand) {
        return flush(hand) > 0 && evaluateStraight(hand) == 60 ? flush(hand) : 0;
    }

    public static int fourOfAKind(Hand hand) {
        return calculateOfAKind(hand, 4);
    }

    private static int calculateOfAKind(Hand hand, int count) {
        if (findVariations(hand) != (5 - count)) return 0;

        Map<Rank, Long> cardFrequencies = hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        return cardFrequencies.entrySet().stream()
                .filter(entry -> entry.getValue() == (long) count)
                .mapToInt(entry -> hand.getCards().stream()
                        .filter(card -> card.getRank().equals(entry.getKey()))
                        .mapToInt(Card::getValue)
                        .sum())
                .findFirst().orElse(0);
    }

    public static int findVariations(Hand hand) {
        List<Card> cards = hand.getCards();
        return (int) IntStream.range(0, cards.size() - 1)
                .filter(i -> cards.get(i).getValue() != cards.get(i + 1).getValue())
                .count();
    }

    public static int fullHouse(Hand hand) {
        if (findVariations(hand) != 1 || fourOfAKind(hand) > 0)
            return 0;
        return hand.getCards().stream().mapToInt(Card::getValue).sum();
    }

    public static int threeOfAKind(Hand hand) {
        return calculateOfAKind(hand, 3);
    }

    public static int twoPair(Hand hand) {
        return calculatePairScore(hand, 2, 2);
    }

    public static int onePair(Hand hand) {
        return calculatePairScore(hand, 3, 1);
    }

    public static int calculatePairScore(Hand hand, int expectedVariations, int pairCountRequired) {
        if (findVariations(hand) != expectedVariations || (pairCountRequired == 2 && threeOfAKind(hand) > 0))
            return 0;

        return hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 2L)
                .mapToInt(entry -> hand.getCards().stream()
                        .filter(card -> card.getRank().equals(entry.getKey()))
                        .mapToInt(Card::getValue)
                        .sum())
                .sum();
    }

    public static int highCard(Hand hand) {
        if (findVariations(hand) != 4)
            return 0;
        return hand.getCards().stream().mapToInt(Card::getValue).max().orElse(0);
    }
}