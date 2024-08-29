package fr.mb.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandEvaluator {

    public static int flush(Hand hand) {
        List<Card> cards = hand.getCARDS();
        int score = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (i > 0 && !cards.get(i).getSUIT().equals(cards.get(i - 1).getSUIT()))
                return 0;
            score += cards.get(i).getVALUE();
        }
        return score;
    }

    public static int straight(Hand hand) {
        if (hasAce(hand) && aceOut(hand) == 14) {
            return 15;
        }
        return straightResearch(hand);
    }

    public static int aceOut(Hand hand) {
        int score = 0;
        for (int i = 0; i < hand.getCARDS().size() - 2; i++) {
            Card courrentCard = hand.getCARDS().get(i);
            Card nextCard = hand.getCARDS().get(i + 1);
            if (isConsecutive(courrentCard, nextCard))
                score += courrentCard.getVALUE();
            else
                return 0;
        }
        score += hand.getCARDS().get(hand.getCARDS().size() - 2).getVALUE();
        return score;
    }

    public static int straightResearch(Hand hand) {
        List<Card> cards = hand.getCARDS();
        int score = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            Card currentCard = cards.get(i);
            Card nextCard = cards.get(i + 1);
            if (!isConsecutive(currentCard, nextCard))
                return 0;
            score += currentCard.getVALUE();
        }
        return score + cards.getLast().getVALUE();
    }

    private static boolean hasAce(Hand hand) {
        for (int i = 0; i < hand.getCARDS().size(); i++) {
            if (hand.getCARDS().get(i).getRANK().equals("A"))
                return true;
        }
        return false;
    }

    public static int straightFlush(Hand hand) {
        if (flush(hand) > 0 && straight(hand) > 0) {
            return straight(hand);
        }
        return 0;
    }

    public static int royalFlush(Hand hand) {
        if (flush(hand) > 0 && straightResearch(hand) == 60)
            return flush(hand);
        return 0;
    }

    public static int fourOfAKind(Hand hand) {
        if (findVariations(hand) != 1) return 0;
        Map<String, Integer> cardFrequencies = new HashMap<>();
        for (Card card : hand.getCARDS()) {
            String rank = card.getRANK();
            cardFrequencies.put(rank, cardFrequencies.getOrDefault(rank, 0) + 1);
        }
        for (Card card : hand.getCARDS()) {
            String rank = card.getRANK();
            if (cardFrequencies.get(rank) == 4)
                return card.getVALUE() * 4;
        }
        return 0;
    }

    public static int fullHouse(Hand hand) {
        if (findVariations(hand) != 1 || fourOfAKind(hand) > 0)
            return 0;
        else {
            int score = 0;
            for (Card card : hand.getCARDS()) {
                score += card.getVALUE();
            }
            return score;
        }
    }

    public static int threeOfAKind(Hand hand) {
        if (findVariations(hand) != 2)
            return 0;
        else {
            Map<String, Integer> cardFrequencies = new HashMap<>();
            for (Card card : hand.getCARDS()) {
                String rank = card.getRANK();
                cardFrequencies.put(rank, cardFrequencies.getOrDefault(rank, 0) + 1);
            }
            for (Card card : hand.getCARDS()) {
                String rank = card.getRANK();
                if (cardFrequencies.get(rank) == 3)
                    return card.getVALUE() * 3;
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
                    score += group.getFirst().getVALUE() * 2;
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
                int pairValue = group.getFirst().getVALUE() * 2;
                if (pairValue > maxPairValue) maxPairValue = pairValue;
            }
        }
        return maxPairValue;
    }

    private static Map<String, List<Card>> groupValues(Hand hand) {
        Map<String, List<Card>> cardGroups = new HashMap<>();
        for (Card card : hand.getCARDS()) {
            String rank = card.getRANK();
            cardGroups.computeIfAbsent(rank, k -> new ArrayList<>()).add(card);
        }
        return cardGroups;
    }

    public static int highCard(Hand hand) {
        if (findVariations(hand) != 4) {
            return 0;
        } else {
            int max = 0;
            for (Card card : hand.getCARDS()) {
                if (card.getVALUE() > max)
                    max = card.getVALUE();
            }
            return max;
        }
    }

    public static int findVariations(Hand hand) {
        int variations = 0;
        for (int i = 0; i < hand.getCARDS().size() - 1; i++) {
            boolean condition = hand.getCARDS().get(i).getVALUE() == hand.getCARDS().get(i + 1).getVALUE();
            if (!condition)
                variations++;
        }
        return variations;
    }

    public static boolean isConsecutive(Card courrentCard, Card nextCard) {
        return courrentCard.getVALUE() == nextCard.getVALUE() - 1;
    }
}