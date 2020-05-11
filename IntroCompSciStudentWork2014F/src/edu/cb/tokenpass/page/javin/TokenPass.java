package edu.cb.tokenpass.page.javin;
import edu.jenks.dist.cb.tokenpass.*;
import java.util.*;
public class TokenPass extends AbstractTokenPass{
	public TokenPass(int playerCount) {
		setBoard(createBoard(playerCount));
	}
	public int[] createBoard(int arg0) {
		// TODO Auto-generated method stub
		int[] board = new int[arg0];
		for(int i = 0; i < arg0; i++) {
			board[i] = random();
		}
		setBoard(board);
		return board;
	}

	public void distributeCurrentPlayerTokens() {
		int tokens = getBoard()[getCurrentPlayer()];
		getBoard()[getCurrentPlayer()] = 0;
		int player = getCurrentPlayer() + 1;
		for(int i = tokens; i > 0; i--) {
			if(player >= getBoard().length) {
				player = 0;
			}
			getBoard()[player] = getBoard()[player] + 1;
			player++;
		}
		
	}

	public int playGame(int arg0) {
		setCurrentPlayer(arg0);
		setRound(0);
		while(true) {
			setRound(getRound() + 1);
			if(isGameOver()) {
				break;
			}
			if(getRound() == 5) {
				for(int player : getBoard()) {
					player--;
				}
			}
			if(getCurrentPlayer() + 1 >= getBoard().length) {
				getBoard()[0]--; 
 			}else {
 				getBoard()[getCurrentPlayer() + 1]--;
 			}
			distributeCurrentPlayerTokens();
		}
		return findMax();
	}
	
	public static void main(String[] args) {
		
	}
	private boolean isGameOver() {
		int zeroes = 0;
		for(int player : getBoard()) {
			if(player == 0) {
				zeroes++;
			}
		}
		return zeroes == getBoard().length - 1;
	}
	private int random() {
		int rand = (int)(1 + (Math.random() * 10));
		return rand;
	}
	private int findMax() {
		int max = 0;
		int pos = 0;
		for(int i = 0; i < getBoard().length; i++) {
			if(getBoard()[i] > max) {
				pos = i;
				max = getBoard()[i];
			}
		}
		return pos;
	}
}
