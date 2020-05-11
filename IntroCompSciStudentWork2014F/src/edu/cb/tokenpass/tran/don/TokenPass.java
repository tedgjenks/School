package edu.cb.tokenpass.tran.don;

import edu.jenks.dist.cb.tokenpass.*;
import java.util.*;

public class TokenPass extends AbstractTokenPass {
	public static void main(String args[]) {
		for(int i = 0; i < 100; i++) {
			//System.out.print((int)(Math.random() * 4) + ", ");
		}
		TokenPass w = new TokenPass(4);
		w.playGame(1);
	}
	public TokenPass(int playerCount) {
		setBoard(createBoard(playerCount));
		setCurrentPlayer((int)(Math.random() * playerCount));
		//System.out.println(playGame(getCurrentPlayer()));
	}
	
	public int[] createBoard(int arg0) {
		int[] ia = new int[arg0];
		for(int i = 0; i < ia.length; i++) {
			ia[i] = (int)(Math.random() * 10) + 1;
		}
		return ia;
	}

	public void distributeCurrentPlayerTokens() {
		int tokens = getBoard()[getCurrentPlayer()];
		getBoard()[getCurrentPlayer()] = 0;
		int curPlayCheck = getCurrentPlayer() + 1;
		while(tokens > 0) {
			if(curPlayCheck > getBoard().length - 1) {
				curPlayCheck = 0;
			}
			getBoard()[curPlayCheck] = getBoard()[curPlayCheck] + 1;
			curPlayCheck++;
			tokens--;
		}
	}

	public int playGame(int currentPlayer) {
		setRound(1);
		setCurrentPlayer(currentPlayer);
		/*
		getBoard()[0] = 1;
		getBoard()[1] = 2;
		getBoard()[2] = 3;
		getBoard()[3] = 4;
		setCurrentPlayer(2);
		*/
		//setCurrentPlayer(4);
		int maxPlayer = getBoard().length;
		while(true) {
			System.out.println(getRound() + " - " + getCurrentPlayer());
			for(int i : getBoard()) {
				System.out.print(i + ", ");
			}
			System.out.println("");
			int totalZeroTok = 0;
			int playerWithTokInd = 0;
			for(int i = 0; i < getBoard().length; i++) {
				if(getBoard()[i] == 0) {
					totalZeroTok++;
				} else {
					playerWithTokInd = i;
				}
			}
			if(totalZeroTok == maxPlayer - 1) {
				return playerWithTokInd;
			}
			if(getRound() % 5 == 0) {
				for(int i = 0; i < getBoard().length; i++) {
					if(getBoard()[i] > 0) {
						getBoard()[i] = getBoard()[i] - 1;
					}
				}
			} else {
				int curPlayCheck = getCurrentPlayer() + 1;
				while(true) {
					if(curPlayCheck > maxPlayer - 1) {
						curPlayCheck = 0;
					}
					if(getBoard()[curPlayCheck] > 0) {
						getBoard()[curPlayCheck] = getBoard()[curPlayCheck] - 1;
						break;
					}
				}
			}
			for(int i : getBoard()) {
				System.out.print(i + ", ");
			}
			System.out.println("");
			
			distributeCurrentPlayerTokens();
			for(int i : getBoard()) {
				System.out.print(i + ", ");
			}
			System.out.println("");
			setRound(getRound() + 1);
			setCurrentPlayer(getCurrentPlayer() + 1);
			if(getCurrentPlayer() > maxPlayer - 1) {
				setCurrentPlayer(0);
			}
		}
		//return 0;
	}

}
