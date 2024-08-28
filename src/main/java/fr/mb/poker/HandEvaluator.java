package fr.mb.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandEvaluator {


    public static int flush(Hand hand) {
        List<Card> cards = hand.getCards();
        int score = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (i > 0 && !cards.get(i).getSuit().equals(cards.get(i - 1).getSuit()))
                return 0;
            score += cards.get(i).getValue();
        }
        return score;
    }

    public static int straight(Hand hand) {
        if(hasAce(hand) && aceOut(hand) == 14) {
            return 15;
        }
        return straightResearch(hand);
    }

    public static int aceOut(Hand hand) {
        int score = 0;
        for(int i = 0; i < hand.getCards().size() - 2; i++) {
            Card courrentCard = hand.getCards().get(i);
            Card nextCard = hand.getCards().get(i + 1);
            if(isConsecutive(courrentCard, nextCard))
                score += courrentCard.getValue();
            else
                return 0;
        }
        score += hand.getCards().get(hand.getCards().size() - 2).getValue();
        return score;
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
        for(int i = 0; i < hand.getCards().size(); i++) {
            if(hand.getCards().get(i).getRank().equals("A"))
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
        if(flush(hand) > 0 && straightResearch(hand) == 60)
            return flush(hand);
        return 0;
    }

    public static int fourOfAKind(Hand hand) {
        if(findVariations(hand) != 1)
            return 0;
        else {
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
    }

    public static int fullHouse(Hand hand) {
        if(findVariations(hand) != 1 || fourOfAKind(hand) > 0)
            return 0;
        else {
            int score = 0;
            for(Card card : hand.getCards()) {
                score += card.getValue();
            }
            return score;
        }
    }

    public static int threeOfAKind(Hand hand) {
        if(findVariations(hand) != 2)
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
            Map<String, List<Card>> cardGroups = new HashMap<>();
            for (Card card : hand.getCards()) {
                String rank = card.getRank();
                cardGroups.computeIfAbsent(rank, k -> new ArrayList<>()).add(card);
            }
            int pairCount = 0;
            int score = 0;
            for (List<Card> group : cardGroups.values()) {
                if (group.size() == 2) {
                    pairCount++;
                    score += group.get(0).getValue() * 2;
                }
            }
            if (pairCount == 2)
                return score;
            else
                return 0;
        }
    }

    public static int onePair(Hand hand) {
        if(findVariations(hand) != 3)
            return 0;
        else {
            Map<String, List<Card>> cardGroups = new HashMap<>();
            for (Card card : hand.getCards()) {
                String rank = card.getRank();
                cardGroups.computeIfAbsent(rank, k -> new ArrayList<>()).add(card);
            }
            int maxPairValue = 0;
            for (List<Card> group : cardGroups.values()) {
                if (group.size() == 2) {
                    int pairValue = group.get(0).getValue() * 2;
                    if (pairValue > maxPairValue) {
                        maxPairValue = pairValue;
                    }
                }
            }
            return maxPairValue;
        }
    }

    public static int highCard(Hand hand) {
        if(findVariations(hand) != 4) {
            return 0;
        }else {
            int max = 0;
            for(Card card : hand.getCards()) {
                if(card.getValue() > max)
                    max = card.getValue();
            }
            return max;
        }
    }

    public static int findVariations(Hand hand) {
        int variations = 0;
        for(int i = 0; i < hand.getCards().size() - 1; i++) {
            boolean condition = hand.getCards().get(i).getValue() == hand.getCards().get(i+1).getValue();
            if(!condition)
                variations++;
        }
        return variations;
    }

    public static boolean isConsecutive(Card courrentCard, Card nextCard) {
        return courrentCard.getValue() == nextCard.getValue() - 1;
    }
}
