package edu.cb.tokenpass.medlock.robert;

import java.util.*;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

public class TokenPass extends AbstractTokenPass {

	public TokenPass(int playerCount) {

	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] board = new int[playerCount];
		for(int Index = 0; Index < playerCount; Index++) {
			int random = (int)(Math.random()* 10) + 1;
			board[Index] = random;
		}
		setBoard(board);
		return board;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		// TODO Auto-generated method stub

	}

	@Override
	public int playGame(int currentPlayer) {
		// TODO Auto-generated method stub
		return 0;
	}

}
