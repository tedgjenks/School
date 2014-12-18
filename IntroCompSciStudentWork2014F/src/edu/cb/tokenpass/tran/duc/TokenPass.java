package edu.cb.tokenpass.tran.duc;

import edu.jenks.dist.cb.tokenpass.*;

import java.util.*;

public class TokenPass extends AbstractTokenPass {
	
	private Random numberSetter = new Random();
	
	public TokenPass(int playerCount) {
		setBoard(createBoard(playerCount));
		int currentPlayer = numberSetter.nextInt(playerCount);
		setCurrentPlayer(currentPlayer);
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] newBoard = new int[playerCount];
		for(int index = 0; index < playerCount; index++){
			newBoard[index] = numberSetter.nextInt(11);
			while(newBoard[index] == 0){
				newBoard[index] = numberSetter.nextInt(11);
			}
		}
		return newBoard;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int currentPlayer = getCurrentPlayer();
		int count = getBoard()[currentPlayer];
		getBoard()[currentPlayer] = 0;
		currentPlayer = currentPlayer + 1;
		for(;count > 0; count--){
			if(currentPlayer > getBoard().length - 1){
				currentPlayer = 0;
			}
			(getBoard()[currentPlayer]) = (getBoard()[currentPlayer]) + 1;
			currentPlayer++;
		}
	}

	@Override
	public int playGame(int arg0) {
		
		return 0;
	}

}
