package fr.mb.poker;

public class Game{
    private List<Player> players;
    private Deck deck;

    public Game(int numberOfPlayers) {
        players = new ArrayList<>(numberOfPlayers);
        deck = new Deck(numberOfPlayers);
        for (int i = 1; i <= numberOfPlayers; i++)
            players.add(new Player("Player " + i));
        Statistics.updateNumberOfGames(numberOfPlayers);
    }

    public void start() {
        dealerCards();
        printOverview();
        determineScore();
        andTheWinnerIs();
    }

    private void andTheWinnerIs() {
        Optional<Player> winner = players.stream().max(Comparator.comparingInt(Player::getPoints));
        if(winner.isPresent()) {
            Player winningPlayer = winner.get();
            System.out.println("\nAND THE WINNER IS...\n");
            System.out.println(winningPlayer);
            System.out.printf("%nthat wins with a %s combo!!!", winningPlayer.getWinnerCombo());
        }
    }

    private void determineScore() {
        int maxScore = 0;
        int winningCombo = -1;

        for (Player player : players) {
            CalculateScore score = new CalculateScore(player.getHand());
            for (int i = 0; i < 10; i++) {
                int comboScore = score.getTotalMethods(i);
                if (comboScore > maxScore) {
                    maxScore = comboScore;
                    winningCombo = i;
                }
            }
            player.setPoints(maxScore);
            player.setWinnerCombo(Rules.stringCombinations(winningCombo));
            Statistics.updateCombo(winningCombo);
        }
    }

    private void dealerCards() {
        for (Player player : players) {
            for (int i = 0; i < 5; i++)
                player.getHand().addCardToHand(deck.dealCard());
            Collections.sort(player.getHand().getCards(), new CardValueComparator());
        }
    }

    public void printOverview() {
        for (Player giocatore : players) {
            System.out.println(giocatore);
        }
    }
}
