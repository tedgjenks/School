package edu.cb.tokenpass.gottlieb.matthew;

import edu.jenks.dist.cb.tokenpass.*;
import java.util.*;

public class TokenPass extends AbstractTokenPass{

	public TokenPass(int playerCount) {
		int[] board = createBoard(playerCount);
		setBoard(board);
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] newBoard= new int[playerCount];
		for (int i= 0; i < playerCount; i++){
			int number= (int) (Math.random()*10);
			number+=1;
			newBoard[i]= number;
		}
		return newBoard;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int[] currentBoard = getBoard();
		int currentPlayer = getCurrentPlayer();
		int currentTokens = currentBoard[currentPlayer];
		int playerIndex = currentPlayer +1;
		currentBoard[currentPlayer] = 0;
		while (currentTokens>0){
			if (playerIndex == currentBoard.length){
				playerIndex = 0;
			}
			currentBoard[playerIndex]++;
			currentTokens-=1;
			playerIndex+=1;
		}	
	}
	@Override
	public int playGame(int currentPlayer) {
		// TODO Auto-generated method stub
		return 0;
	}
}