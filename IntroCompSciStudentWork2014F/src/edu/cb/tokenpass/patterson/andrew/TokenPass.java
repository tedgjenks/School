package edu.cb.tokenpass.patterson.andrew;

import java.util.*;

import edu.jenks.dist.cb.tokenpass.*;

public class TokenPass extends AbstractTokenPass{

	public static void main(String[] arg0){
		TokenPass tp= new TokenPass(4);
		int[] initialized = {3,2,6,10};
		int[] expected= {5,3,1,12};
		int expectedCurrentPlayer=2;
		tp.setCurrentPlayer(expectedCurrentPlayer);
		tp.setBoard(initialized);
		tp.distributeCurrentPlayerTokens();
		System.out.println("expBoard: " + Arrays.toString(expected));
		System.out.println("actBoard: " + Arrays.toString(tp.getBoard()));
		System.out.println(tp.getCurrentPlayer());
	}
	public TokenPass(int playerCount) {
		int[] cb = createBoard(playerCount);
		setBoard(cb);
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] bc= new int [playerCount];
		for (int index=0; index < playerCount; index ++) {
			int number = (int)(Math.random()*10);
			number+=1;
			bc[index]=number;
		}
		return bc;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int[] currentBoard= getBoard();
		int currentPlayer = getCurrentPlayer();
		int currentTotal = currentBoard[currentPlayer];
		int indexPlayer= currentPlayer + 1;
		currentBoard[currentPlayer]=0;
		while(currentTotal >0){
			if(indexPlayer == currentBoard.length){
				indexPlayer= 0;
			}
			currentBoard[indexPlayer]++;
			currentTotal -= 1;
			indexPlayer += 1;
			
		}
		
	}

	@Override
	public int playGame(int currentPlayer) {
		int winner = 0;
		int currentPlayer2= getCurrentPlayer();
		int[]currentBoard=getBoard();
		for(int index=0; index<currentBoard.length; index++){
			if (currentBoard[index] != 0){
				distributeCurrentPlayerTokens();
				setCurrentPlayer(currentPlayer2 +1);
			}
		}
		for(int index2= 0; index2< currentBoard.length; index2++){
			if(currentBoard[index2]==0){
				winner = getCurrentPlayer();
			}
		}
		return winner;
	}
	

}
