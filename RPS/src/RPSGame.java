
public class RPSGame {

    private int roundsTotal; // number of rounds to play
    private int roundsPlayed; // number of rounds that have completed
    private String roundWinner; // the winner of the current round
    private int oneWins; // number of rounds won by first player
    private int twoWins; // number of rounds won by second player
    private String oneMove; // first player's move
    private String twoMove; // second player's move
    private String winner; // which player won?

    public RPSGame(int numRounds) {
        roundsTotal = numRounds;
    }

    public void playGame() {
        // TODO: Implement this method
    }

    private void initializeGame() {
        roundsPlayed = 0;
        oneWins = 0;
        twoWins = 0;
        winner = "";
    }

    private boolean gameOver() {
        return (roundsPlayed == roundsTotal);
    }

    private void advancePlay() {
        roundsPlayed++;

        oneMove = chooseRPS();
        twoMove = chooseRPS();

        if ((oneMove.equals("Rock") && twoMove.equals("Scissors"))
                || (oneMove.equals("Scissors") && twoMove.equals("Paper"))
                || (oneMove.equals("Paper") && twoMove.equals("Rock"))) {
            oneWins++;
            roundWinner = "Player One";
        } else if ((twoMove.equals("Rock") && oneMove.equals("Scissors"))
                || (twoMove.equals("Scissors") && oneMove.equals("Paper"))
                || (twoMove.equals("Paper") && oneMove.equals("Rock"))) {
            twoWins++;
            roundWinner = "Player Two";
        } else {
            roundWinner = "Nobody";
        }
    }

    private String chooseRPS() {
        // TODO: implement this method. Use Math.random() and the values .33 and .66 to
        // make equally likely random choices of "Rock", "Paper", "Scissors".
    }

    private void showGame() {
        System.out.println(oneMove + " vs. " + twoMove + ": " + roundWinner + " wins.");
    }

    private void judgeAndReport() {
        if (oneWins > twoWins) {
            winner = "Player One";
        } else if (oneWins < twoWins) {
            winner = "Player Two";
        } else {
            winner = "Nobody";
        }

        System.out.println();
        System.out.println("Player One rounds won: " + oneWins);
        System.out.println("Player Two rounds won: " + twoWins);
        System.out.println("Winner?: " + winner);
    }

}
