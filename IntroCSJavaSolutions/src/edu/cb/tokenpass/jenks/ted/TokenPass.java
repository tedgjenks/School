/**
 * 
 */
package edu.cb.tokenpass.jenks.ted;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

/**
 * @author Ted
 *
 */
public class TokenPass extends AbstractTokenPass {
	
	private boolean printGame = true;
	
	/** 
	 * Creates the board array to be of size <code>playerCount</code> and fills it with
	 * random integer values from 1 to 10, inclusive. <br/>
	 * Initializes currentPlayer to a random integer value in the range between 0 and <code>playerCount-1</code>, inclusive.
	 * @param playerCount the number of players
	 */
	public TokenPass(int playerCount) {
		setBoard(createBoard(playerCount));
	}

	/**
	 * @see edu.jenks.dist.cb.tokenpass.AbstractTokenPass#distributeCurrentPlayerTokens()
	 */
	@Override
	public void distributeCurrentPlayerTokens() {
		int[] board = getBoard();
		int index = getCurrentPlayer();
		int tokens = board[index];
		board[index] = 0;
		for(; tokens > 0; tokens--) {
			if(++index >= board.length)
				index = 0;
			board[index] = board[index] + 1;
		}
	}

	/**
	 * @see edu.jenks.dist.cb.tokenpass.AbstractTokenPass#createBoard(int)
	 */
	@Override
	public int[] createBoard(int playerCount) {
		int[] board = new int[playerCount];
		for(int index = board.length - 1; index >= 0; index--)
			board[index] = (int)(Math.random() * 10) + 1;
		return board;
	}
	
	private int findNextPlayerWithTokens(final int initialPlayer) {
		int[] board = getBoard();
		int currentPlayer = initialPlayer;
		boolean found = false;
		do {
			if(++currentPlayer >= board.length)
				currentPlayer = 0;
			if(board[currentPlayer] > 0)
				found = true;
		} while(!found);
		return currentPlayer;
	}
	
	private void printBoard() {
		int[] board = getBoard();
		for(int index = 0; index < board.length; index++)
			System.out.print(board[index] + ", ");
		System.out.println("round " + getRound());
	}
	
	private void removeTokenFromAll() {
		int[] board = getBoard();
		boolean run = false;
		for(int index = board.length - 1; index >= 0 && !run; index--) {
			if(board[index] > 1)
				run = true;
		}
		if(run) {
			for(int index = board.length - 1; index >= 0; index--) {
				if(board[index] > 0)
					board[index] = board[index] - 1;
			}
			if(printGame) {
				System.out.println("Everybody lost a token!");
				printBoard();
			}
		} else if(printGame)
			System.out.println("Remove all skipped - nobody has more than one.");
	}
	
	private void removeTokenFromNextPlayer() {
		int[] board = getBoard();
		int index = findNextPlayerWithTokens(getCurrentPlayer());
		board[index] = board[index] - 1;
		if(printGame) {
			System.out.println("Token removed from " + index);
			printBoard();
		}
	}
	
	private int findWinner() {
		int[] board = getBoard();
		int firstIndexOverZero = -1, secondIndexOverZero = -1;
		for(int index = board.length - 1; index >= 0 && secondIndexOverZero < 0; index--) {
			if(board[index] > 0) {
				if(firstIndexOverZero < 0)
					firstIndexOverZero = index;
				else
					secondIndexOverZero = index;
			}
		}
		return secondIndexOverZero < 0 ? firstIndexOverZero : -1;
	}

	/**
	 * @see edu.jenks.dist.cb.tokenpass.AbstractTokenPass#playGame(int)
	 */
	@Override
	public int playGame(int currentPlayer) {
		setCurrentPlayer(currentPlayer);
		if(printGame) {
			System.out.println("Current player - " + currentPlayer);
			printBoard();
		}
		int winner = findWinner();
		if(winner < 0) {
			int round = getRound();
			if(round % 5 == 0)
				removeTokenFromAll();
			else
				removeTokenFromNextPlayer();
			distributeCurrentPlayerTokens();
			if(printGame) {
				System.out.println("Tokens distributed");
				printBoard();
			}
			setRound(round + 1);
			System.out.println();
			setCurrentPlayer(findNextPlayerWithTokens(currentPlayer));
			winner = playGame(getCurrentPlayer());
		}
		return winner;
	}
	
	public static void main(String[] args) {
		TokenPass tp = new TokenPass(5);
		int[] board1 = {1, 2, 3, 4};
		tp.setBoard(board1);
		tp.setRound(1);
		int winner = tp.playGame(2);
		System.out.println("Winner is: " + winner + "; round is " + tp.getRound());
		
		int[] board2 = {3, 9, 10, 2, 5, 5, 2, 8};
		tp.setBoard(board2);
		tp.setRound(1);
		winner = tp.playGame(5);
		System.out.println("Winner is: " + winner + "; round is " + tp.getRound());
	}

}
