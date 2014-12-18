package edu.cb.tokenpass.tarasidis.john;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;


public class TokenPass extends AbstractTokenPass{

	public TokenPass(int playerCount) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] boardList = new int[playerCount];
		for (int i = 0; i < playerCount; i++) {
			double randomNum = Math.random();
			double betterNum = randomNum * 10;
			int bestNum = (int) (betterNum + 1);
			boardList[i] = bestNum;
		}
		return boardList;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int[] board = getBoard();
		int playerIndex = getCurrentPlayer();
		int numOfTokens = board[playerIndex];
		board[playerIndex] = 0;
		for (int i = playerIndex + 1; numOfTokens > 0; i++) {
			if (i == board.length) {
				i = 0;
			}
			board[i] += 1;
			numOfTokens--;
			//System.out.println("index: " + i);
			//System.out.println("number of tokens: " + numOfTokens);
		}
	}

	@Override
	public int playGame(int arg0) {
		createBoard(arg0);
		distributeCurrentPlayerTokens();
		return getCurrentPlayer();
	}

}
