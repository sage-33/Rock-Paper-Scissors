
public class RPSGame {

	private int roundsTotal; // number of rounds to play
	private int roundsPlayed; // number of rounds that have completed
	private String roundWinner; // the winner of the current round
	private int oneWins; // number of rounds won by first player
	private int twoWins; // number of rounds won by second player
	private String oneMove; // first player's move
	private String twoMove; // second player's move
	private String winner; // which player won?

	
	/**
	 * Constructs an RPS that runs a round of Rock, Paper, Scissors
	 * 
	 * @param numRounds the number of rounds
	 */
	public RPSGame(int numRounds) {
		roundsTotal = numRounds;
	}

	// done
	/**
	 * Initializes the order the game will run
	 */
	public void playGame() {
		initializeGame();
		while (gameOver() == false) {
			chooseRPS();
			advancePlay();
			showGame();

		}
		
		judgeAndReport();

	}

	// done
	/**
	 * Initializes the game by setting the variables to a starting point of 0
	 */
	public void initializeGame() {
		roundsPlayed = 0;
		oneWins = 0;
		twoWins = 0;
		winner = "";
	}

	// done
	/**
	 * Returns <code>true</code> if the number of rounds played is also the total
	 * rounds wanted to be played
	 * 
	 * @return <code>true</code> if the number of rounds played is also the total
	 *         rounds wanted to be played; <code>false</code> otherwise gameOver is
	 *         false
	 * 
	 */
	public boolean gameOver() {
		return (roundsPlayed == roundsTotal);
	}

	// done
	/**
	 * Simulates a round of Rock Paper Scissors
	 */
	public void advancePlay() {
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

	// done
	/**
	 * Returns Rock, Paper, or Scissors randomly
	 * 
	 * @return rock, paper, or scissors
	 */
	private String chooseRPS() {
		double num = Math.random();
		if (num <= .33) {
			return ("Rock");
		} else if (num <= .66) {
			return ("Paper");
		} else {
			return ("Scissors");
		}

	}

	//done
	/**
	 * Prints out what the move of each player was and who won
	 */
	public void showGame() {
		System.out.println(oneMove + " vs. " + twoMove + ": " + roundWinner + " wins.");
	}

	//done
	/**
	 * Prints out who wins depending on how many rounds each player won
	 */
	public void judgeAndReport() {
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
