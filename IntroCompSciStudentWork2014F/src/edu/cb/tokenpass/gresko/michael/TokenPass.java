package edu.cb.tokenpass.gresko.michael;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;
import java.util.*;

public class TokenPass extends AbstractTokenPass {

	public TokenPass(int playerCount) {
		createBoard(playerCount);
		int currentPlayer = (int)(Math.random() * (playerCount + 1));
		setCurrentPlayer(currentPlayer);
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] board = new int[playerCount];
		for(int lenIndex = 0; lenIndex < playerCount; lenIndex++) {
			int randomNumber = 1 + (int)(Math.random() * ( 11 - 1 ));
			board[lenIndex] = randomNumber;
		}
		setBoard(board);
		return board;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int playerCount = getBoard().length;
		int current = getCurrentPlayer();
		int tokenAmount = getBoard()[current];
		getBoard()[current] = 0;
		int reciever = current + 1;
		for(;tokenAmount > 0; tokenAmount--){
			if (reciever > playerCount - 1) {
				reciever = 0;
			}
			getBoard()[reciever] = getBoard()[reciever] + 1;
			reciever++;
		}
		
	}

	@Override
	public int playGame(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
