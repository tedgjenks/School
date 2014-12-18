package edu.cb.tokenpass.grenci.david;

import edu.jenks.dist.cb.tokenpass.*;
import java.util.*;

public class TokenPass extends AbstractTokenPass {
	
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
		int currPlayer = player;
		int index  = 0;
		int tokensGiving = board[player];
		board[player] = 0;
		for(; index < board.length; index++) {
			try {
				board[player]++;
			}
			catch (IndexOutOfBoundsException ex) {
				index = 0;
				
			}
		}
	}

	@Override
	public int playGame(int arg0) {
		
		return 0;
	}

}
