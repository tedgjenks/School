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
		
		
	}

	@Override
	public int playGame(int currentPlayer) {
		return 0;
	}

}
