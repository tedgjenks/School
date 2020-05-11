/**
 * 
 */
package edu.jenks.dist.cb.tokenpass;

/**
 * @author JenksT
 *
 */
public abstract class AbstractTokenPass {

	private int[] board;
	private int currentPlayer;
	private int round;

	/**
	 * 
	 */
	public AbstractTokenPass() {}

	/**
	 * Creates a board array to be of size <code>playerCount</code> and fills it with
	 * random integer values from 1 to 10, inclusive. 
	 * @param playerCount
	 * @return the new board array
	 */
	public abstract int[] createBoard(int playerCount);
	
	/** 
	 * Distributes the tokens from the current player's position one at a time to each player in the game. <br>
	 * Distribution begins with the next position and continues until all the tokens
	 * have been distributed. If there are still tokens to distribute when the player at the
	 * highest position is reached, the next token will be distributed to the player at position 0. <br>
	 * <b>Precondition</b>: <code>currentPlayer</code> has at least one token. <br>
	 * <b>Postcondition</b>: <code>currentPlayer</code> has not changed.
	 */
	public abstract void distributeCurrentPlayerTokens();
	
	/**
	 * Play Token Pass until only one player has any tokens remaining. <br>
	 * The play works as follows: <br>
	 * - Begin by looking for a winner (only one player has tokens). <br>
	 * - Remove one token from all players after every fifth round. <br>
	 * - Otherwise, find the next player who has tokens after <code>currentPlayer</code>, and remove one token. <br>
	 * - Distribute the tokens from <code>currentPlayer</code>. <br>
	 * - The round ends. Repeat the process for the next round. <br>
	 * <b>Preconditions</b>: <br>
	 *  - <code>currentPlayer</code> has at least one token. <br>
	 *  - <code>round</code> is 1. <br>
	 * <b>Inconditions</b>: <br>
	 * - players never have negative tokens. <br>
	 * <b>Postcondition</b>: <code>round</code> has been updated to reflect the number of rounds that were played.
	 * @param currentPlayer
	 * @return the winning player
	 */
	public abstract int playGame(int currentPlayer);

	/**
	 * @return the board
	 */
	public int[] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(int[] board) {
		this.board = board;
	}

	/**
	 * @return the currentPlayer
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * @param round the round to set
	 */
	public void setRound(int round) {
		this.round = round;
	}

}
