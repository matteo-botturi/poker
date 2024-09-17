package fr.mb.poker.test;

import fr.mb.poker.model.Card;
import fr.mb.poker.model.Hand;
import fr.mb.poker.outils.HandEvaluator;
import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.enumeration.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de test pour la classe HandEvaluator.
 * <p>
 * Cette classe contient des tests unitaires pour vérifier le bon fonctionnement
 * des méthodes d'évaluation des combinaisons de poker. Chaque méthode de la classe
 * HandEvaluator est testée pour divers cas, incluant les cas valides et les cas d'erreur.
 *
 * @author matteo
 */
public class HandEvaluatorTest {

    /** La main de poker utilisée pour les tests. */
    private Hand hand;

    /**
     * Initialise une nouvelle main avant chaque test.
     */
    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    /**
     * Ajoute des cartes à une main pour faciliter les tests.
     *
     * @param hand la main à laquelle ajouter des cartes
     * @param cards les cartes à ajouter
     */
    private void addCardsToHand(Hand hand, Card... cards) {
        for (Card card : cards)
            hand.addCard(card);
    }

    /**
     * Teste la méthode flush() pour vérifier qu'elle identifie correctement une couleur.
     */
    @Test
    void testFlush() {
        // Flush
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.HEARTS, Rank.KING),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.JACK)
        );
        assertEquals(37, HandEvaluator.flush(hand));

        // Not Flush
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.HEARTS, Rank.KING),
                new Card(Suit.SPADES, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.JACK)
        );
        assertEquals(0, HandEvaluator.flush(hand));
    }

    /**
     * Teste la méthode straightFlush() pour vérifier qu'elle identifie correctement une quinte flush.
     */
    @Test
    void testStraightFlush() {
        // Straight Flush
        addCardsToHand(hand,
                new Card(Suit.DIAMONDS, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.THREE),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.SIX)
        );
        assertEquals(20, HandEvaluator.straightFlush(hand));

        // Not Straight
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.DIAMONDS, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.THREE),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.SEVEN)
        );
        assertEquals(0, HandEvaluator.straightFlush(hand));

        // Not Flush
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.DIAMONDS, Rank.TWO),
                new Card(Suit.CLUBS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.THREE),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.SIX)
        );
        assertEquals(0, HandEvaluator.straightFlush(hand));

        // Straight with A = 1
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.DIAMONDS, Rank.TWO),
                new Card(Suit.DIAMONDS, Rank.THREE),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.ACE)
        );
        assertEquals(15, HandEvaluator.straightFlush(hand));
    }

    /**
     * Teste la méthode royalFlush() pour vérifier qu'elle identifie correctement une quinte flush royale.
     */
    @Test
    void testRoyalFlush() {
        // Royal Flush
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.HEARTS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.KING),
                new Card(Suit.HEARTS, Rank.ACE)
        );
        assertEquals(60, HandEvaluator.royalFlush(hand));

        // Not Royal Flush
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.HEARTS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.KING),
                new Card(Suit.HEARTS, Rank.NINE)
        );
        assertEquals(0, HandEvaluator.royalFlush(hand));

        // Royal but Not Flush
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.KING),
                new Card(Suit.HEARTS, Rank.ACE)
        );
        assertEquals(0, HandEvaluator.royalFlush(hand));
    }

    /**
     * Teste la méthode fourOfAKind() pour vérifier qu'elle identifie correctement un carré.
     */
    @Test
    void testFourOfAKind() {
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.ACE),
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.SPADES, Rank.ACE),
                new Card(Suit.HEARTS, Rank.JACK)
        );
        assertEquals(56, HandEvaluator.fourOfAKind(hand));
    }

    /**
     * Teste la méthode fullHouse() pour vérifier qu'elle identifie correctement un full.
     */
    @Test
    void testFullHouse() {
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.DIAMONDS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.SPADES, Rank.JACK)
        );
        assertEquals(43, HandEvaluator.fullHouse(hand));
    }

    /**
     * Teste la méthode straight() pour vérifier qu'elle identifie correctement une quinte.
     */
    @Test
    public void testStraight() {
        // Straight "normal"
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.DIAMONDS, Rank.EIGHT),
                new Card(Suit.CLUBS, Rank.NINE),
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.SPADES, Rank.JACK)
        );
        assertEquals(45, HandEvaluator.straight(hand));

        // Straight A = 1
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.DIAMONDS, Rank.TWO),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.SPADES, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.ACE)
        );
        assertEquals(15, HandEvaluator.straight(hand));
    }

    /**
     * Teste la méthode threeOfAKind() pour vérifier qu'elle identifie correctement un brelan.
     */
    @Test
    void testThreeOfAKind() {
        // Three-of-a-Kind
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.CLUBS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.SPADES, Rank.EIGHT)
        );
        assertEquals(12, HandEvaluator.threeOfAKind(hand));

        // Full House
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.DIAMONDS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.SPADES, Rank.JACK)
        );
        assertEquals(0, HandEvaluator.threeOfAKind(hand));
    }

    /**
     * Teste la méthode twoPair() pour vérifier qu'elle identifie correctement une double paire.
     */
    @Test
    void testTwoPair() {
        // Two Pair
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.QUEEN),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.CLUBS, Rank.TWO),
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.HEARTS, Rank.NINE)
        );
        assertEquals(28, HandEvaluator.twoPair(hand));

        // Four-of-a-Kind
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.ACE),
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.SPADES, Rank.ACE),
                new Card(Suit.HEARTS, Rank.JACK)
        );
        assertEquals(0, HandEvaluator.twoPair(hand));
    }

    /**
     * Teste la méthode onePair() pour vérifier qu'elle identifie correctement une paire.
     */
    @Test
    void testOnePair() {
        // One Pair
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.THREE),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.DIAMONDS, Rank.ACE),
                new Card(Suit.SPADES, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.NINE)
        );
        assertEquals(6, HandEvaluator.onePair(hand));

        // 0 Pair
        hand = new Hand();
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.THREE),
                new Card(Suit.DIAMONDS, Rank.ACE),
                new Card(Suit.CLUBS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.NINE),
                new Card(Suit.SPADES, Rank.FOUR)
        );
        assertEquals(0, HandEvaluator.onePair(hand));
    }

    /**
     * Teste la méthode highCard() pour vérifier qu'elle identifie correctement la carte haute.
     */
    @Test
    void testHighCard() {
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.EIGHT),
                new Card(Suit.DIAMONDS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.NINE),
                new Card(Suit.SPADES, Rank.JACK),
                new Card(Suit.HEARTS, Rank.KING)
        );
        assertEquals(13, HandEvaluator.highCard(hand));
    }

    /**
     * Teste la méthode adjustAceForLowStraight() pour vérifier qu'elle modifie correctement
     * la valeur de l'As pour former une quinte basse (A-2-3-4-5).
     */
    @Test
    public void testAdjustAceForLowStraight() {
        addCardsToHand(hand,
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.TWO),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.SPADES, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FIVE));

        hand.sortHand();

        assertEquals(14, hand.getCards().get(4).getValue());
        assertEquals(HandEvaluator.straight(hand), 15);
        assertEquals(Rank.ACE, hand.getCards().get(0).getRank());
        assertEquals(1, hand.getCards().getFirst().getValue());
    }
}