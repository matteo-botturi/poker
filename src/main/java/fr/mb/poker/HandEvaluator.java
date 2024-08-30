package fr.mb.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        for (int i = 0; i < cards.size() - 1; i++) {
            Card currentCard = cards.get(i);
            Card nextCard = cards.get(i + 1);
            if (isConsecutive(currentCard, nextCard))
                score += currentCard.getValue();
            else
                return 0;
        }
        return score + cards.get(cards.size() - 1).getValue();
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
        if (flush(hand) > 0 && straight(hand) > 0)
            return straightScore;
        return 0;
    }

    public static int royalFlush(Hand hand) {
        return flush(hand) > 0 && straightResearch(hand) == 60 ? flush(hand) : 0;
    }

    public static int fourOfAKind(Hand hand) {
        if (findVariations(hand) != 1)
            return 0;
        Map<String, Integer> cardFrequencies = new HashMap<>();
        for (Card card : hand.getCards()) {
            String rank = card.getRank();
            cardFrequencies.put(rank, cardFrequencies.getOrDefault(rank, 0) + 1);
        }
        for (Card card : hand.getCards()) {
            String rank = card.getRank();
            if (cardFrequencies.get(rank) == 4)
                return card.getValue() * 4;
        }
        return 0;
    }

    public static int fullHouse(Hand hand) {
        if (findVariations(hand) != 1 || fourOfAKind(hand) > 0)
            return 0;
        return hand.getCards().stream().mapToInt(Card::getValue).sum();
    }

    public static int threeOfAKind(Hand hand) {
        if (findVariations(hand) != 2)
            return 0;
        else {
            Map<String, Integer> cardFrequencies = new HashMap<>();
            for (Card card : hand.getCards()) {
                String rank = card.getRank();
                cardFrequencies.put(rank, cardFrequencies.getOrDefault(rank, 0) + 1);
            }
            for (Card card : hand.getCards()) {
                String rank = card.getRank();
                if (cardFrequencies.get(rank) == 3)
                    return card.getValue() * 3;
            }
            return 0;
        }
    }

    public static int twoPair(Hand hand) {
        if (findVariations(hand) != 2 || threeOfAKind(hand) > 0)
            return 0;
        else {
            Map<String, List<Card>> cardGroups = groupValues(hand);
            int pairCount = 0;
            int score = 0;
            for (List<Card> group : cardGroups.values()) {
                if (group.size() == 2) {
                    pairCount++;
                    score += group.getFirst().getValue() * 2;
                }
            }
            if (pairCount == 2)
                return score;
            else
                return 0;
        }
    }

    public static int onePair(Hand hand) {
        if (findVariations(hand) != 3) return 0;

        Map<String, List<Card>> cardGroups = groupValues(hand);
        int maxPairValue = 0;
        for (List<Card> group : cardGroups.values()) {
            if (group.size() == 2) {
                int pairValue = group.getFirst().getValue() * 2;
                if (pairValue > maxPairValue) maxPairValue = pairValue;
            }
        }
        return maxPairValue;
    }

    private static Map<String, List<Card>> groupValues(Hand hand) {
        Map<String, List<Card>> cardGroups = new HashMap<>();
        for (Card card : hand.getCards()) {
            String rank = card.getRank();
            cardGroups.computeIfAbsent(rank, k -> new ArrayList<>()).add(card);
        }
        return cardGroups;
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