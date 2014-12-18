package edu.cb.tokenpass.wilbanks.tanner;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

public class TokenPass extends AbstractTokenPass {

	public TokenPass(int players) {
		
		
	}
	
	
	@Override
	public int[] createBoard(int playerCount) {
		int[] players =new int[playerCount];
		for(int index=0; index<players.length;index++){
			players[index]= 1 + (int)(Math.random() * ( 11 - 1 ));
		}
		setBoard(players);
		return players;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int starter = getCurrentPlayer();
		int[] players = getBoard();
		int distribute = players[starter];
		players[starter] = 0;
		for (;distribute > 0; distribute--){
			try {
				starter++;
				players[starter] = players[starter]+1; 
			}
			catch (IndexOutOfBoundsException e){
				starter = 0;
				players[starter] = players[starter] + 1;
			}
		}/*
		if (tests starter + 1 > player count)
		if so, make starter 0
		add one to board[starter]
		increment starter
		*/
		
	}

	@Override
	public int playGame(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
