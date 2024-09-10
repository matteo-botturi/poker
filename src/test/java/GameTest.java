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

/**
 * @author matteo
 */
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

    /**
     * Teste la détermination du gagnant en cas d'égalité de points, mais avec des combinaisons de priorités différentes.
     */
    @Test
    public void testWinnerDeterminationByComboPriorityWithTieOnPoints() {
        setupPlayersWithTieOnPointsAndDifferentCombos();
        Player actualWinner = game.andTheWinnerIs();
        Player expectedWinner = player1;
        assertEquals(expectedWinner, actualWinner);
    }

    /**
     * Teste la détermination du gagnant en fonction de la carte haute, en cas d'égalité de points.
     */
    @Test
    public void testWinnerDeterminationByHighCard() {
        setupPlayersWithHighCardDifference();
        Player actualWinner = game.andTheWinnerIs();
        Player expectedWinner = player1;
        assertEquals(expectedWinner, actualWinner);
    }

    /**
     * Teste la détermination du gagnant en fonction de la carte haute et de la couleur, en cas d'égalité de points et de cartes.
     */
    @Test
    public void testWinnerDeterminationByHighCardAndSuit() {
        setupPlayersWithHighCardAndSuitDifference();
        Player actualWinner = game.andTheWinnerIs();
        Player expectedWinner = player1;
        assertEquals(expectedWinner, actualWinner);
    }

    /**
     * Configure les mains des joueurs pour simuler une égalité de points avec des combinaisons de priorités différentes.
     */
    private void setupPlayersWithTieOnPointsAndDifferentCombos() {
        player1.setPoints(30);
        player1.setWinnerCombo(Combo.STRAIGHT);

        player2.setPoints(30);
        player2.setWinnerCombo(Combo.THREE_OF_A_KIND);

        game.setPlayers(List.of(player1, player2));
    }

    /**
     * Configure les mains des joueurs pour simuler une différence sur la carte haute en cas d'égalité de points.
     */
    private void setupPlayersWithHighCardDifference() {
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        player1.getHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        player1.getHand().addCard(new Card(Suit.SPADES, Rank.QUEEN));
        player1.getHand().addCard(new Card(Suit.DIAMONDS, Rank.JACK));
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN));

        player1.setPoints(30);
        player1.setWinnerCombo(Combo.HIGH_CARD);

        player2.getHand().addCard(new Card(Suit.HEARTS, Rank.KING));
        player2.getHand().addCard(new Card(Suit.CLUBS, Rank.QUEEN));
        player2.getHand().addCard(new Card(Suit.SPADES, Rank.JACK));
        player2.getHand().addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        player2.getHand().addCard(new Card(Suit.HEARTS, Rank.NINE));

        player2.setPoints(30);
        player2.setWinnerCombo(Combo.HIGH_CARD);

        game.setPlayers(List.of(player1, player2));
    }

    /**
     * Configure les mains des joueurs pour simuler une différence sur la carte haute et la couleur en cas d'égalité de points et de cartes.
     */
    private void setupPlayersWithHighCardAndSuitDifference() {
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        player1.getHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        player1.getHand().addCard(new Card(Suit.SPADES, Rank.QUEEN));
        player1.getHand().addCard(new Card(Suit.DIAMONDS, Rank.JACK));
        player1.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN));

        player1.setPoints(30);
        player1.setWinnerCombo(Combo.HIGH_CARD);

        player2.getHand().addCard(new Card(Suit.SPADES, Rank.ACE));
        player2.getHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        player2.getHand().addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
        player2.getHand().addCard(new Card(Suit.HEARTS, Rank.JACK));
        player2.getHand().addCard(new Card(Suit.CLUBS, Rank.TEN));

        player2.setPoints(30);
        player2.setWinnerCombo(Combo.HIGH_CARD);

        game.setPlayers(List.of(player1, player2));
    }
}