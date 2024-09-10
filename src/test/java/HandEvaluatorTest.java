import fr.mb.poker.model.Card;
import fr.mb.poker.model.Hand;
import fr.mb.poker.outils.HandEvaluator;
import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.enumeration.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author matteo
 */
public class HandEvaluatorTest {

    /** La main de poker utilisée pour les tests. */
    private Hand hand;

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