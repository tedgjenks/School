package edu.cb.tokenpass.kinney.ben;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

public class TokenPass extends AbstractTokenPass {

	public TokenPass(int playerCount) {
		//TODO Auto-generated constructor stub
		int [] board = createBoard(playerCount);
		setBoard(board);
	}

	@Override
	public int[] createBoard(int playerCount) {
		//TODO Auto-generated constructor stub
		int [] boardCreated = new int [playerCount];
		for (int i = 0; i < playerCount; i++){
			int number = (int)(Math.random()*10);
			number += 1;
			boardCreated[i] = number;
			
			
		}
		return boardCreated;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		//TODO Auto-generated constructor stub
		int [] currentBoard = getBoard();
		int currentPlayer = getCurrentPlayer();
		int currentTotal = currentBoard [currentPlayer];
		int indexPlayer = currentPlayer + 1;
		currentBoard[currentPlayer] = 0;
		while(currentTotal > 0){
			if(indexPlayer == currentBoard.length){
				indexPlayer = 0;
			}
			currentBoard[indexPlayer]++;
			currentTotal-=1;
			indexPlayer +=1;
		}

	}

	@Override
	public int playGame(int arg0) {
		//TODO Auto-generated constructor stub
		return 0;
	}
	
}