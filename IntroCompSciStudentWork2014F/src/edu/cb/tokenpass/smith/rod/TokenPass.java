package edu.cb.tokenpass.smith.rod;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

import java.util.*;

public class TokenPass extends AbstractTokenPass {

	public TokenPass(int playerCount) {
	}

	public int[] createBoard(int playerCount) {
		int [] players = new int [playerCount];
		for(int index = 0; index < players.length; index++) {
			players[index] = 1 + (int)(Math.random() * (11 - 1));
				
		}
		setBoard(players);
		return players;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int[] board = getBoard();
		int player = getCurrentPlayer();
		int distribute = board[player];
		board[player] = 0;
		for (;distribute > 0; distribute--){
			try {
			player++;
			board[player] = board[player] + 1;
			}
			catch(IndexOutOfBoundsException r) {
				player = 0;
				board[player] = board[player] + 1;
			}
			
		}
		
	}

	@Override
	public int playGame(int currentPlayer) {
		return 0;
	}

}
