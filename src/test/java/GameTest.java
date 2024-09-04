import fr.mb.poker.enumeration.Combo;
import fr.mb.poker.enumeration.Rank;
import fr.mb.poker.enumeration.Suit;
import fr.mb.poker.model.Card;
import fr.mb.poker.model.Game;
import fr.mb.poker.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setup() {
        game = new Game(2);
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    @Test
    public void testWinnerDeterminationByComboPriorityWithTieOnPoints() {
        setupPlayersWithTieOnPointsAndDifferentCombos();

        Player actualWinner = game.andTheWinnerIs();

        Player expectedWinner = player1;
        assertEquals(expectedWinner, actualWinner);
    }

    @Test
    public void testWinnerDeterminationByHighCard() {
        setupPlayersWithHighCardDifference();

        Player actualWinner = game.andTheWinnerIs();

        Player expectedWinner = player1;
        assertEquals(expectedWinner, actualWinner);
    }

    @Test
    public void testWinnerDeterminationByHighCardAndSuit() {
        setupPlayersWithHighCardAndSuitDifference();

        Player actualWinner = game.andTheWinnerIs();

        Player expectedWinner = player1;
        assertEquals(expectedWinner, actualWinner);
    }

    private void setupPlayersWithTieOnPointsAndDifferentCombos() {
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.TWO));
        player1.getHand().addCard(new Card(Suit.CLUBS, Rank.THREE));
        player1.getHand().addCard(new Card(Suit.SPADES, Rank.FOUR));
        player1.getHand().addCard(new Card(Suit.DIAMONDS, Rank.FIVE));
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.SIX));

        player2.getHand().addCard(new Card(Suit.HEARTS, Rank.TWO));
        player2.getHand().addCard(new Card(Suit.CLUBS, Rank.TWO));
        player2.getHand().addCard(new Card(Suit.SPADES, Rank.TWO));
        player2.getHand().addCard(new Card(Suit.DIAMONDS, Rank.FIVE));
        player2.getHand().addCard(new Card(Suit.HEARTS, Rank.SIX));

        player1.setPoints(30);
        player1.setWinnerCombo(Combo.STRAIGHT); // Scala

        player2.setPoints(30);
        player2.setWinnerCombo(Combo.THREE_OF_A_KIND); // Tris

        game.setPlayers(List.of(player1, player2));
    }

    private void setupPlayersWithHighCardDifference() {
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        player1.getHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        player1.getHand().addCard(new Card(Suit.SPADES, Rank.QUEEN));
        player1.getHand().addCard(new Card(Suit.DIAMONDS, Rank.JACK));
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN));

        player2.getHand().addCard(new Card(Suit.HEARTS, Rank.KING));
        player2.getHand().addCard(new Card(Suit.CLUBS, Rank.QUEEN));
        player2.getHand().addCard(new Card(Suit.SPADES, Rank.JACK));
        player2.getHand().addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        player2.getHand().addCard(new Card(Suit.HEARTS, Rank.NINE));

        player1.setPoints(30);
        player1.setWinnerCombo(Combo.HIGH_CARD);
        player2.setPoints(30);
        player2.setWinnerCombo(Combo.HIGH_CARD);

        game.setPlayers(List.of(player1, player2));
    }

    private void setupPlayersWithHighCardAndSuitDifference() {
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        player1.getHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        player1.getHand().addCard(new Card(Suit.SPADES, Rank.QUEEN));
        player1.getHand().addCard(new Card(Suit.DIAMONDS, Rank.JACK));
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN));

        player2.getHand().addCard(new Card(Suit.SPADES, Rank.ACE));
        player2.getHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        player2.getHand().addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
        player2.getHand().addCard(new Card(Suit.HEARTS, Rank.JACK));
        player2.getHand().addCard(new Card(Suit.CLUBS, Rank.TEN));

        player1.setPoints(30);
        player1.setWinnerCombo(Combo.HIGH_CARD);
        player2.setPoints(30);
        player2.setWinnerCombo(Combo.HIGH_CARD);

        game.setPlayers(List.of(player1, player2));
    }
}