package edu.cb.tokenpass.grenci.david;

import edu.jenks.dist.cb.tokenpass.*;
import java.util.*;

public class TokenPass extends AbstractTokenPass {
	
	public static void main(String[] args) {
		TokenPass tp = new TokenPass(4);
		int[] initialBoard = {3,2,6,10};
		int[] expectedBoard = {5, 3, 1, 12};
		int expCurrentPlayer = 2;
		tp.setCurrentPlayer(expCurrentPlayer);
		tp.setBoard(initialBoard);
		tp.distributeCurrentPlayerTokens();
		System.out.println("expBoard: " + Arrays.toString(expectedBoard));
		System.out.println("actBoard: " + Arrays.toString(tp.getBoard()));
		System.out.println(tp.getCurrentPlayer());
	}
	
	public int random( ){
		int random = ((int)Math.random() * 10) + 1;
		return random;
		
	}

	public TokenPass(int playerCount) {
		
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] player = new int[playerCount];
		for(int index = 0; index < player.length; index++) {
			player[index] = random();
		}
		setBoard(player);
		return player;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int[] board = getBoard();
		int player = getCurrentPlayer();
		int tokensGiving = board[player];
		board[player] = 0;
		player++;
		for(; tokensGiving > 0; player++,tokensGiving--) {
			try {
				board[player] = board[player] + 1;

			}
			catch (IndexOutOfBoundsException ex) {
				player = 0;
				board[player] += 1;
				
			}
		}
	}

	@Override
	public int playGame(int arg0) {
		
		return 0;
	}

}
