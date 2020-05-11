package edu.cb.tokenpass.whitt.rose;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;
import java.util.*;

public class TokenPass extends AbstractTokenPass {

	public static void main(String[] args) {
		TokenPass t = new TokenPass(10);
//		System.out.println(t);
		int[] board = {3, 2, 6, 10};
		t.setBoard(board);
		for (int i = 0; i < board.length; i++) {
			System.out.println(board[i]);
		}
		t.setCurrentPlayer(2);
		t.distributeCurrentPlayerTokens();
		t.setBoard(board);
		for (int i = 0; i < board.length; i++) {
			System.out.println(board[i]);
		}
	}
	
	public TokenPass(int playerCount) {
		createBoard(playerCount);
		Random random = new Random();
		setCurrentPlayer(random.nextInt(((playerCount - 1) - 0) + 1) + 0);	
	}
	
	//Creates a board array to be of size playerCount and
	//fills it with random integer values from 1 to 10, inclusive.
	public int[] createBoard(int playerCount) {
		Random random = new Random();
		int[] board = new int[playerCount];
		
		for (int i = 0; i < playerCount; i++) {
			board[i] = random.nextInt(10) + 1;
//			System.out.println(i + ": " + board[i]);
		}
		return board;
	}

	//Distributes the tokens from the current player's position one at a time
	//to each player in the game.
	//Distribution begins with the next position and continues until all the
	//tokens have been distributed. If there are still tokens to distribute when the 
	//player at the highest position is reached, the next token will be distributed to the 
	//player at position 0.
	public void distributeCurrentPlayerTokens() {
//		int[] board = getBoard();
		// TODO Auto-generated method stub
		int curPlayer = getCurrentPlayer();
		int numTokens = getBoard()[curPlayer];
		int cur = curPlayer + 1;
		while (numTokens != 0) {
			if (cur > getBoard().length - 1) {
				cur = 0;
			}
			System.out.println(cur + ": " + getBoard()[cur]);
			getBoard()[cur]++;
			getBoard()[curPlayer]--;
			numTokens--;
			cur++;
		}
		
	}

	@Override
	public int playGame(int currentPlayer) {
		//look for winner (only one player has tokens)
		int numZero = 0;
		int notZero = 0;
		boolean won = false;
		while (won != true) {
			numZero = 0;
			notZero = 0;
			for (int i = 0; i < getBoard().length; i++) {
				if (getBoard()[i] == 0) {
					numZero++;
				}
				if (getBoard()[i] != 0) {
					notZero = i;
				}
			}
			if (numZero == getBoard().length - 2) {
				won = true;
			}
			
			//remove one token from every player after every fifth round
			if ((getRound() - 1) % 5 == 0) {
				for (int i = 0; i < getBoard().length; i++) {
					getBoard()[i]--;
				}
			}
			distributeCurrentPlayerTokens();
//			return 0;
		}
		return notZero;
	}
	
	

}
