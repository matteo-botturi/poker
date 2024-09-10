package fr.mb.poker.outils;

import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.model.Card;
import fr.mb.poker.model.Hand;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Classe utilitaire pour évaluer les différentes combinaisons de mains de poker.
 * <p>
 * Cette classe fournit des méthodes statiques pour évaluer et calculer les points
 * pour chaque combinaison possible dans une main de poker, y compris les cas spéciaux
 * comme la quinte flush royale et la quinte basse avec un As.
 *
 * @author matteo
 */
public class HandEvaluator {

    /** Score maximum pour une quinte flush royale. */
    private static final int ROYAL_FLUSH_SCORE = 60;

    /** Score pour une quinte basse (A-2-3-4-5) avec un As évalué à 1. */
    private static final int LOW_STRAIGHT_SCORE = 15;

    /**
     * Quinte flush royale
     *
     * @param hand la main à évaluer
     * @return le score de la quinte flush royale, ou 0
     */
    public static int royalFlush(Hand hand) {
        return isFlush(hand) && calculateStraightScore(hand) == ROYAL_FLUSH_SCORE ? flushScore(hand) : 0;
    }

    /**
     * Quinte flush
     *
     * @param hand la main à évaluer
     * @return le score de la quinte flush, ou 0
     */
    public static int straightFlush(Hand hand) {
        int straightScore = straight(hand);
        return isFlush(hand) && straightScore > 0 ? straightScore : 0;
    }

    /**
     * Carré
     *
     * @param hand la main à évaluer
     * @return le score du carré, ou 0
     */
    public static int fourOfAKind(Hand hand) {
        return calculateOfAKind(hand, 4);
    }

    /**
     * Full
     *
     * @param hand la main à évaluer
     * @return le score du full, ou 0
     */
    public static int fullHouse(Hand hand) {
        if (findVariations(hand) != 1 || fourOfAKind(hand) > 0)
            return 0;
        return hand.getCards().stream().mapToInt(Card::getValue).sum();
    }

    /**
     * Couleur
     *
     * @param hand la main à évaluer
     * @return le score de la couleur, ou 0
     */
    public static int flush(Hand hand) {
        return isFlush(hand) ? flushScore(hand) : 0;
    }

    /**
     * Quinte
     *<p>
     * Cette méthode gère également la quinte basse avec un As = 1
     *
     * @param hand la main à évaluer
     * @return le score de la quinte, ou 0 si la main ne correspond pas
     */
    public static int straight(Hand hand) {
        if (isLowStraight(hand)) {
            adjustAceForLowStraight(hand);
            return LOW_STRAIGHT_SCORE;
        }
        return calculateStraightScore(hand);
    }

    /**
     * Brelan
     *
     * @param hand la main à évaluer
     * @return le score du brelan, ou 0
     */
    public static int threeOfAKind(Hand hand) {
        return calculateOfAKind(hand, 3);
    }

    /**
     * Double paire
     *
     * @param hand la main à évaluer
     * @return le score de la double paire, ou 0
     */
    public static int twoPair(Hand hand) {
        return calculatePairScore(hand, 2, 2);
    }

    /**
     * Paire
     *
     * @param hand la main à évaluer
     * @return le score de la paire, ou 0
     */
    public static int onePair(Hand hand) {
        return calculatePairScore(hand, 3, 1);
    }

    /**
     * Carte haute.
     *
     * @param hand la main à évaluer
     * @return la valeur de la carte la plus haute
     */
    public static int highCard(Hand hand) {
        if (findVariations(hand) != 4)
            return 0;
        return hand.getCards().stream().mapToInt(Card::getValue).max().orElse(0);
    }

    /**
     * Calcule le score d'une quinte.
     *
     * @param hand la main à évaluer
     * @return le score de la quinte, ou 0
     */
    public static int calculateStraightScore(Hand hand) {
        List<Card> cards = hand.getCards();
        int score = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            Card currentCard = cards.get(i);
            Card nextCard = cards.get(i + 1);
            if (!areConsecutives(currentCard, nextCard))
                return 0;
            score += currentCard.getValue();
        }
        return score + cards.getLast().getValue();
    }

    /**
     * Vérifie si une main correspond à une quinte basse (A-2-3-4-5).
     *
     * @param hand la main à évaluer
     * @return true si la main est une quinte basse, sinon false
     */
    private static boolean isLowStraight(Hand hand) {
        List<Card> cards = hand.getCards();
        return cards.stream()
                .mapToInt(Card::getValue)
                .filter(value -> value >= 2 && value <= 5)
                .distinct()
                .count() == 4 && isAcePresent(hand);
    }

    /**
     * Ajuste la valeur de l'As à 1 pour gérer les quintes basses (A-2-3-4-5).
     *
     * @param hand la main à ajuster
     */
    private static void adjustAceForLowStraight(Hand hand) {
        hand.getCards().stream()
                .filter(card -> card.getRank() == Rank.ACE)
                .forEach(card -> card.setValue(1));
        hand.sortHand();
    }

    /**
     * Calcule le score pour un brelan ou un carré
     *
     * @param hand la main à évaluer
     * @param count le nombre de cartes de même valeur à rechercher
     * @return le score de la combinaison, ou 0
     */
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

    /**
     * Vérifie si une main est une couleur (flush).
     *
     * @param hand la main à évaluer
     * @return true si toutes les cartes sont de la même couleur, sinon false
     */
    private static boolean isFlush(Hand hand) {
        List<Card> cards = hand.getCards();
        return cards.stream().allMatch(card -> card.getSuit().equals(cards.getFirst().getSuit()));
    }

    /**
     * Calcule le score d'une couleur (flush) en additionnant les valeurs des cartes.
     *
     * @param hand la main à évaluer
     * @return le score de la couleur
     */
    private static int flushScore(Hand hand) {
        return hand.getCards().stream().mapToInt(Card::getValue).sum();
    }

    /**
     * Calcule le score pour une paire ou une double paire dans une main.
     *
     * @param hand la main à évaluer
     * @param expectedVariations le nombre de variations attendu dans la main (2 pour la double paire, 3 pour la paire)
     * @param pairCountRequired le nombre de paires requises pour la combinaison
     * @return le score de la paire ou de la double paire, ou 0
     */
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

    /**
     * Trouve le nombre de variations des valeurs (différences) dans une main.
     * 1 variations = Carré ou Full
     * 2 variations = Double Paire ou Brelan
     * 3 variations = Paire
     * 4 variations = Carte Haute
     *
     * @param hand la main à évaluer
     * @return le nombre de variations entre les cartes de la main
     */
    public static int findVariations(Hand hand) {
        List<Card> cards = hand.getCards();
        return (int) IntStream.range(0, cards.size() - 1)
                .filter(i -> cards.get(i).getValue() != cards.get(i + 1).getValue())
                .count();
    }

    /**
     * Vérifie si une main contient un As.
     *
     * @param hand la main à évaluer
     * @return true si la main contient un As, sinon false
     */
    private static boolean isAcePresent(Hand hand) {
        return hand.getCards().stream().anyMatch(card -> card.getRank() == Rank.ACE);
    }

    /**
     * Vérifie si deux cartes sont consécutives.
     *
     * @param currentCard la carte courante
     * @param nextCard la carte suivante
     * @return true si les cartes sont consécutives, sinon false
     */
    public static boolean areConsecutives(Card currentCard, Card nextCard) {
        return currentCard.getValue() == nextCard.getValue() - 1;
    }
}