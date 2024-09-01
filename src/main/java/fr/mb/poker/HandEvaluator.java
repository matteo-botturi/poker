package fr.mb.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HandEvaluator {

    public static int flush(Hand hand) {
        List<Card> cards = hand.getCards();
        if (cards.stream().allMatch(card -> card.getSuit().equals(cards.get(0).getSuit())))
            return cards.stream().mapToInt(Card::getValue).sum();
        return 0;
    }

    public static int straight(Hand hand) {
        if (hasAce(hand) && aceOut(hand) == 14)
            return 15;
        return straightResearch(hand);
    }

    public static int aceOut(Hand hand) {
        List<Card> cards = hand.getCards();
        int score = 0;
        for (int i = 0; i < cards.size() - 2; i++) {
            Card currentCard = cards.get(i);
            Card nextCard = cards.get(i + 1);
            if (isConsecutive(currentCard, nextCard))
                score += currentCard.getValue();
            else
                return 0;
        }
        return score += hand.getCards().get(hand.getCards().size() - 2).getValue();
    }

    public static int straightResearch(Hand hand) {
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

    private static boolean hasAce(Hand hand) {
        return hand.getCards().stream().anyMatch(card -> card.getRank().equals("A"));
    }

    public static int straightFlush(Hand hand) {
        int straightScore = straight(hand);
        if (flush(hand) > 0 && straightScore > 0)
            return straightScore;
        return 0;
    }

    public static int royalFlush(Hand hand) {
        return flush(hand) > 0 && straightResearch(hand) == 60 ? flush(hand) : 0;
    }

    private static int calculateOfAKind(Hand hand, int count) {
        if (findVariations(hand) != (5 - count)) return 0;

        Map<String, Long> cardFrequencies = hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        for (Map.Entry<String, Long> entry : cardFrequencies.entrySet()) {
            if (entry.getValue() == (long) count) {
                return hand.getCards().stream()
                        .filter(card -> card.getRank().equals(entry.getKey()))
                        .findFirst()
                        .get()
                        .getValue() * count;
            }
        }
        return 0;
    }

    public static int fourOfAKind(Hand hand) {
        return calculateOfAKind(hand, 4);
    }

    public static int fullHouse(Hand hand) {
        if (findVariations(hand) != 1 || fourOfAKind(hand) > 0)
            return 0;
        return hand.getCards().stream().mapToInt(Card::getValue).sum();
    }

    public static int threeOfAKind(Hand hand) {
        return calculateOfAKind(hand, 3);
    }

    public static int calculatePairScore(Hand hand, int expectedVariations, int pairCountRequired) {
        if (findVariations(hand) != expectedVariations || (pairCountRequired == 2 && threeOfAKind(hand) > 0)) return 0;

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

    public static int twoPair(Hand hand) {
        return calculatePairScore(hand, 2, 2);
    }

    public static int onePair(Hand hand) {
        return calculatePairScore(hand, 3, 1);
    }

    public static int highCard(Hand hand) {
        if (findVariations(hand) != 4)
            return 0;
        return hand.getCards().stream().mapToInt(Card::getValue).max().orElse(0);
    }

    public static int findVariations(Hand hand) {
        List<Card> cards = hand.getCards();
        return (int) IntStream.range(0, cards.size() - 1)
                .filter(i -> cards.get(i).getValue() != cards.get(i + 1).getValue())
                .count();
    }

    public static boolean isConsecutive(Card courrentCard, Card nextCard) {
        return courrentCard.getValue() == nextCard.getValue() - 1;
    }
}