package edu.cb.tokenpass.scates.collin;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

import java.util.*;

public class TokenPass extends AbstractTokenPass {
	int[] arrayList = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	int players = 0;
	int Board = (players++);
	int tokens = 20;
	int playCount = 0;

	public TokenPass(int playerCount) {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] createBoard(int playerCount) {
		Random random = new Random();
		for(int players = 0; players < arrayList.length; players++)
			playCount = arrayList[players];
			for(int player : arrayList) {
				player = playCount++;
			}
		
		return arrayList;
		
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		Random random = new Random();
		int player1 = 0;
		int player2 = 0;
		for(int i = 0; i < tokens; i++) {
			int result = random.nextInt(2);
			if (result == 1) {
				player1++;
			}else 
				player2++;
		}
	}

	@Override
	public int playGame(int currentPlayer) {
		// TODO Auto-generated method stub
		return 0;
	}

}
