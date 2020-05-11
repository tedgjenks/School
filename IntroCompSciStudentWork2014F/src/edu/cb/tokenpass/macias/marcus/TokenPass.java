package edu.cb.tokenpass.macias.marcus;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

import java.util.*;

public class TokenPass extends AbstractTokenPass {
	public static void main(String[] args) {
		TokenPass run = new TokenPass(2);
		System.out.println("Current Player: " + run.getCurrentPlayer());
		
		//run.board();
		System.out.println(run.playGame(run.getCurrentPlayer()));
	}

	// private int[] board;
	public void board() {
		for (int i = 0; i < getBoard().length; i++) {
			System.out.println(i + ": Tokens: " + getBoard()[i] + "  ");
		}
		System.out.println("");
	}

	public TokenPass(int playerCount) {
		setBoard(createBoard(playerCount));
		int randNum = (int) (Math.random() * (playerCount - 1));
		this.setCurrentPlayer(randNum);
	}

	public void distributeCurrentPlayerTokens() {
		int amountOfTokens = getBoard()[getCurrentPlayer()];
		int temp = amountOfTokens;
		int i = getCurrentPlayer() + 1;
		if(i >= getBoard().length) {
			i = 0;
		}
		while (amountOfTokens > 0) {

			getBoard()[i] = getBoard()[i] + 1;
			amountOfTokens--;

			i++;
			if (i >= getBoard().length) {
				i = 0;
			}
		}
		getBoard()[getCurrentPlayer()] -= temp;

	}

	public int[] createBoard(int playerCount) {

		int[] actualBoard = new int[playerCount];
		for (int i = 0; i < actualBoard.length; i++) {
			// Random randNum = new Random();
			int randNum = (int) (Math.random() * 10 + 1);
			actualBoard[i] = randNum;
		}
		return actualBoard;

	}

	public int foundWinner() {
		int winner = -1;
		int playersWithTokens = 0;
		for (int i = 0; i < getBoard().length; i++) {
			if (getBoard()[i] > 0) {
				winner = i;
				playersWithTokens++;
			}
			if (playersWithTokens > 1) {
				return -1;
			}
		}
		return winner;

	}

	public int playGame(int currentPlayer) {
		setCurrentPlayer(currentPlayer);
		setRound(1);
		while(true) {
			
			//Step 1
			if(foundWinner() != -1) {
				return foundWinner();
			}
			//Step 2
			if(getRound()%5 == 0) {
				for (int i = 0; i < getBoard().length; i++) {
					if (getBoard()[i] != 0) {
						getBoard()[i] = getBoard()[i] - 1;
					}
				}
			//Step 3
			}else {
				int i = getCurrentPlayer() + 1;
				if(i == getBoard().length) {
					i = 0;
				}
				while(true) {
					if(getBoard()[i] > 0) {
						getBoard()[i] = getBoard()[i] - 1;
						break;
					}else {
						i++;
						if(i >= getBoard().length) {
							i = 0;
						}
					}
				}
			}
			//Step 4
			distributeCurrentPlayerTokens();
			setRound(getRound() + 1);
			if (getCurrentPlayer() == getBoard().length - 1) {
				setCurrentPlayer(0);
			} else {
				setCurrentPlayer(getCurrentPlayer() + 1);
			}
			board();
		}
		
		
	}

}
