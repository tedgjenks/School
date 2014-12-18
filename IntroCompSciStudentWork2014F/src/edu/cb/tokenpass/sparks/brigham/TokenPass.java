package edu.cb.tokenpass.sparks.brigham;
import java.util.*;
import edu.jenks.dist.cb.tokenpass.*;
public class TokenPass extends AbstractTokenPass {
	public TokenPass(int playerCount) {
		int[] board = createBoard(playerCount);
		setBoard(board);
	}
	@Override
	public int[] createBoard(int playerCount) {
		int[] boardCreated = new int[playerCount];
		for(int i = 0; i< playerCount; i++){
			int number = (int)(Math.random()*10);
			number += 1;
			boardCreated[i]= number;
		}
		return boardCreated;
	}
	@Override
	public void distributeCurrentPlayerTokens() {
	int[] currentBoard = getBoard();
	int currentPlayer = getCurrentPlayer();
	int currentTotal = currentBoard[currentPlayer];
	int indexPlayer = currentPlayer + 1;
	currentBoard[currentPlayer] = 0;
	while(currentTotal > 0){
		if(indexPlayer == currentBoard.length){
			indexPlayer = 0;
		}
		currentBoard[indexPlayer]++;
		currentTotal -= 1;
		indexPlayer += 1;
	}
	}
	@Override
	public int playGame(int currentPlayer) {
		int winner = 0;
		int currentPlayer2 = getCurrentPlayer();
		int[] currentBoard = getBoard();
		for(int index = 0; index < currentBoard.length; index++){
			if (currentBoard[index] != 0){
				distributeCurrentPlayerTokens();
				setCurrentPlayer(currentPlayer2+1);
			}
		}
		for(int index2 = 0; index2< currentBoard.length; index2++){
			if(currentBoard[index2] == 0){
				winner = currentPlayer;
			} 
		}
		return winner;
	}
}