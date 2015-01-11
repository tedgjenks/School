package edu.cb.tokenpass.scates.collin;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

import java.util.*;

public class TokenPass extends AbstractTokenPass {
	int[] arrayList = {1,2,3,4,5,6,7,8,9,10};
	int[] players;
	int[] Board = players;
	int tokens = 20;
	int playCount = 0;
	
	public static void main(String[] args){
		TokenPass tp = new TokenPass(4);
		int[] initalBoard = {3,2,6,10};
		int[] expectBoard = {5,3,1,12};
		int expCurrentPlayer = 2;
		tp.setCurrentPlayer(expCurrentPlayer);
		tp.setBoard(initalBoard);
		tp.distributeCurrentPlayerTokens();
		System.out.println("expBoard:" + Arrays.toString(expectBoard));
		System.out.println("actBoard:" + Arrays.toString(tp.getBoard()));
		System.out.println(tp.getCurrentPlayer());
	}

	public TokenPass(int playerCount) {
		int [] board = createBoard(playerCount);
		setBoard(Board);
		
	}

	@Override
	public int[] createBoard(int playerCount) {
		int [] boardCreated = new int [playerCount];
		for (int i = 0; i < playerCount; i++) {
			playCount = (int)(Math.random()*10);
			playCount += 1;
			boardCreated[i] = playCount;
		
		
		}
		return boardCreated;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int [] currentBoard = getBoard();
		int currentPlayer = getCurrentPlayer();
		int currentTotal = currentBoard[currentPlayer];
		int indexPlayer = currentPlayer + 1;
		currentBoard[currentPlayer] = 0;
		while(currentTotal > 0) {
			if(indexPlayer == currentBoard.length) {
				indexPlayer = 0;
			}
			currentBoard[indexPlayer]++;
			currentTotal-=1;
			indexPlayer +=1;
		}
		
	}

	@Override
	public int playGame(int currentPlayer) {
		
		return 0;
	}
}