package edu.cb.tokenpass.smith.eli;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

import java.util.*;

public class TokenPass extends AbstractTokenPass {

	public static void main(String[] args){
		TokenPass tp = new TokenPass(4);
		int[] initialBoard = {3,2,6,10};
		int[] expectedBoard = {5,3,1,12};
		int expCurrentPlayer = 2;
		tp.setCurrentPlayer(expCurrentPlayer);
		tp.setBoard(initialBoard);
		tp.distributeCurrentPlayerTokens();
		System.out.println("expBoard: " + Arrays.toString(expectedBoard));
		System.out.println("actBoard: " + Arrays.toString(tp.getBoard()));
		System.out.println(tp.getCurrentPlayer());
	}
	
	public TokenPass(int playerCount) {
		createBoard(playerCount);
		int player = (int)(Math.random()*(10) +1);
		setCurrentPlayer(player);
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] newBoard = new int[playerCount];
		for(int index = 0; index < playerCount; index++){
			int randomNumber = (int)(Math.random()*(10) +1);
			newBoard[index] = randomNumber;
		}
		setBoard(newBoard);
		return newBoard;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int[] board = getBoard();
		int currentPlayer = getCurrentPlayer();
		int boardIndex = currentPlayer + 1;
		int currentTokens = board[currentPlayer];
		board[currentPlayer] = 0;
		for(; currentTokens > 0; boardIndex++, currentTokens--){
			if (boardIndex == board.length){
				boardIndex = 0;
			}
			board[boardIndex] += 1;
			System.out.println(Arrays.toString(getBoard()) + board[currentPlayer]);
		}
	}


	@Override
	public int playGame(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
