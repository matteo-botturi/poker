import fr.mb.poker.Card;
import fr.mb.poker.CardValueComparator;
import fr.mb.poker.Hand;
import fr.mb.poker.HandEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandEvaluatorTest {
    private Hand hand;
    private Hand hand2;
    private Hand hand3;
    private Hand hand4;
    private static final String H = "Hearts";
    private static final String C = "Clubs";
    private static final String D = "Diamonds";
    private static final String S = "Spades";

    @BeforeEach
    void setUp() {
        hand = new Hand();
        hand2 = new Hand();
        hand3 = new Hand();
        hand4 = new Hand();
    }

    @Test
    void testFlush() {
        //Flush
        hand.addCardToHand(new Card(H, "2"));
        hand.addCardToHand(new Card(H, "K"));
        hand.addCardToHand(new Card(H, "4"));
        hand.addCardToHand(new Card(H, "7"));
        hand.addCardToHand(new Card(H, "J"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(37, HandEvaluator.flush(hand));

        //Not Flush
        hand2.addCardToHand(new Card(H, "2"));
        hand2.addCardToHand(new Card(H, "K"));
        hand2.addCardToHand(new Card(S, "4"));
        hand2.addCardToHand(new Card(H, "7"));
        hand2.addCardToHand(new Card(H, "J"));

        hand2.getCards().sort(new CardValueComparator());
        assertEquals(0, HandEvaluator.flush(hand2));
    }


    @Test
    void testStraightFlush() {
        //Straight Flush
        hand.addCardToHand(new Card(D, "2"));
        hand.addCardToHand(new Card(D, "5"));
        hand.addCardToHand(new Card(D, "3"));
        hand.addCardToHand(new Card(D, "4"));
        hand.addCardToHand(new Card(D, "6"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(20, HandEvaluator.straightFlush(hand));

        //Not Straight
        hand2.addCardToHand(new Card(D, "2"));
        hand2.addCardToHand(new Card(D, "5"));
        hand2.addCardToHand(new Card(D, "3"));
        hand2.addCardToHand(new Card(D, "4"));
        hand2.addCardToHand(new Card(D, "7"));

        hand2.getCards().sort(new CardValueComparator());
        assertEquals(0, HandEvaluator.straightFlush(hand2));

        //NotFlush
        hand3.addCardToHand(new Card(D, "2"));
        hand3.addCardToHand(new Card(C, "5"));
        hand3.addCardToHand(new Card(D, "3"));
        hand3.addCardToHand(new Card(D, "4"));
        hand3.addCardToHand(new Card(D, "6"));

        hand3.getCards().sort(new CardValueComparator());
        assertEquals(0, HandEvaluator.straightFlush(hand3));

        //Straight with A = 1
        hand4.addCardToHand(new Card(D, "2"));
        hand4.addCardToHand(new Card(D, "5"));
        hand4.addCardToHand(new Card(D, "3"));
        hand4.addCardToHand(new Card(D, "4"));
        hand4.addCardToHand(new Card(D, "A"));

        hand4.getCards().sort(new CardValueComparator());
        assertEquals(15, HandEvaluator.straightFlush(hand4));
    }

    @Test
    void testRoyalFlush() {
        //Royal Flush
        hand.addCardToHand(new Card(H, "10"));
        hand.addCardToHand(new Card(H, "J"));
        hand.addCardToHand(new Card(H, "Q"));
        hand.addCardToHand(new Card(H, "K"));
        hand.addCardToHand(new Card(H, "A"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(60, HandEvaluator.royalFlush(hand));

        //Not Royal
        hand2.addCardToHand(new Card(H, "10"));
        hand2.addCardToHand(new Card(H, "J"));
        hand2.addCardToHand(new Card(H, "Q"));
        hand2.addCardToHand(new Card(H, "K"));
        hand2.addCardToHand(new Card(H, "9"));

        hand2.getCards().sort(new CardValueComparator());
        assertEquals(0, HandEvaluator.royalFlush(hand2));

        //Royal Not Flush
        hand3.addCardToHand(new Card(H, "10"));
        hand3.addCardToHand(new Card(H, "J"));
        hand3.addCardToHand(new Card(D, "Q"));
        hand3.addCardToHand(new Card(H, "K"));
        hand3.addCardToHand(new Card(H, "A"));

        hand3.getCards().sort(new CardValueComparator());
        assertEquals(0, HandEvaluator.royalFlush(hand3));
    }

    @Test
    void testFourOfAKind() {
        hand.addCardToHand(new Card(H, "A"));
        hand.addCardToHand(new Card(D, "A"));
        hand.addCardToHand(new Card(C, "A"));
        hand.addCardToHand(new Card(H, "J"));
        hand.addCardToHand(new Card(S, "A"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(56, HandEvaluator.fourOfAKind(hand));
    }

    @Test
    void testFullHouse() {
        hand.addCardToHand(new Card(H, "7"));
        hand.addCardToHand(new Card(D, "7"));
        hand.addCardToHand(new Card(C, "7"));
        hand.addCardToHand(new Card(H, "J"));
        hand.addCardToHand(new Card(S, "J"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(43, HandEvaluator.fullHouse(hand));
    }

    @Test
    public void testStraight() {
        //Straight "normal"
        hand.addCardToHand(new Card(H, "7"));
        hand.addCardToHand(new Card(D, "8"));
        hand.addCardToHand(new Card(C, "9"));
        hand.addCardToHand(new Card(H, "10"));
        hand.addCardToHand(new Card(S, "J"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(45, HandEvaluator.straight(hand));

        //Straight A = 1
        hand2.addCardToHand(new Card(H, "A"));
        hand2.addCardToHand(new Card(D, "3"));
        hand2.addCardToHand(new Card(C, "2"));
        hand2.addCardToHand(new Card(H, "5"));
        hand2.addCardToHand(new Card(S, "4"));

        hand2.getCards().sort(new CardValueComparator());
        assertEquals(15, HandEvaluator.straight(hand2));
    }

    @Test
    void testThreeOfAKind() {
        //Three-in-a-Kind
        hand.addCardToHand(new Card(H, "4"));
        hand.addCardToHand(new Card(D, "4"));
        hand.addCardToHand(new Card(C, "8"));
        hand.addCardToHand(new Card(H, "7"));
        hand.addCardToHand(new Card(S, "4"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(12, HandEvaluator.threeOfAKind(hand));

        //FullHouse
        hand2.addCardToHand(new Card(H, "7"));
        hand2.addCardToHand(new Card(D, "7"));
        hand2.addCardToHand(new Card(C, "7"));
        hand2.addCardToHand(new Card(H, "J"));
        hand2.addCardToHand(new Card(S, "J"));

        hand2.getCards().sort(new CardValueComparator());
        assertEquals(0, HandEvaluator.threeOfAKind(hand2));
    }

    @Test
    void testTwoPair() {
        //Two Pair
        hand.addCardToHand(new Card(H, "Q"));
        hand.addCardToHand(new Card(D, "2"));
        hand.addCardToHand(new Card(C, "9"));
        hand.addCardToHand(new Card(H, "Q"));
        hand.addCardToHand(new Card(S, "2"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(28, HandEvaluator.twoPair(hand));

        //4-in-a-Kind
        hand2.addCardToHand(new Card(H, "A"));
        hand2.addCardToHand(new Card(D, "A"));
        hand2.addCardToHand(new Card(C, "A"));
        hand2.addCardToHand(new Card(H, "J"));
        hand2.addCardToHand(new Card(S, "A"));

        hand2.getCards().sort(new CardValueComparator());
        assertEquals(0, HandEvaluator.twoPair(hand2));
    }

    @Test
    void testOnePair() {
        //One Pair
        hand.addCardToHand(new Card(H, "3"));
        hand.addCardToHand(new Card(D, "A"));
        hand.addCardToHand(new Card(C, "3"));
        hand.addCardToHand(new Card(H, "9"));
        hand.addCardToHand(new Card(S, "4"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(HandEvaluator.onePair(hand), 6);

        //0 Pair
        hand2.addCardToHand(new Card(H, "3"));
        hand2.addCardToHand(new Card(D, "A"));
        hand2.addCardToHand(new Card(C, "Q"));
        hand2.addCardToHand(new Card(H, "9"));
        hand2.addCardToHand(new Card(S, "4"));

        hand2.getCards().sort(new CardValueComparator());
        assertEquals(0, HandEvaluator.onePair(hand2));
    }

    @Test
    void testHighCard() {
        hand.addCardToHand(new Card(H, "8"));
        hand.addCardToHand(new Card(D, "7"));
        hand.addCardToHand(new Card(C, "9"));
        hand.addCardToHand(new Card(H, "K"));
        hand.addCardToHand(new Card(S, "J"));

        hand.getCards().sort(new CardValueComparator());
        assertEquals(13, HandEvaluator.highCard(hand));
    }
}