package edu.cb.tokenpass.tran.duc;

import edu.jenks.dist.cb.tokenpass.*;

import java.util.*;

public class TokenPass extends AbstractTokenPass {
	
	private Random numberSetter = new Random();
	
	public TokenPass(int playerCount) {
		setBoard(createBoard(playerCount));
		int currentPlayer = numberSetter.nextInt(playerCount);
		setCurrentPlayer(currentPlayer);
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] newBoard = new int[playerCount];
		for(int index = 0; index < playerCount; index++){
			newBoard[index] = numberSetter.nextInt(11);
			while(newBoard[index] == 0){
				newBoard[index] = numberSetter.nextInt(11);
			}
		}
		return newBoard;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		int currentPlayer = getCurrentPlayer();
		if(getBoard()[currentPlayer]  == 0){
			return;
		}
		int count = getBoard()[currentPlayer];
		int nextPlayer = currentPlayer;
		getBoard()[currentPlayer] = 0;
		currentPlayer = currentPlayer + 1;
		for(;count > 0; count--){
			if(currentPlayer > getBoard().length - 1){
				currentPlayer = 0;
			}
			(getBoard()[currentPlayer]) = (getBoard()[currentPlayer]) + 1;
			currentPlayer++;
		}
	}

	@Override
	public int playGame(int currentPlayer) {
		int winner = 0;
		setRound(1);
		setCurrentPlayer(currentPlayer);
		int count = 0;
		int removeCount = 0;
		while(1==1){
			count = 0;
			for(int index = 0; index < getBoard().length; index++){
				if(getBoard()[index] == 0){
					count++;
				}
			}
			if(count == getBoard().length - 1){
				break;
			}
			if(getRound() % 5 == 0){
				for(int index = 0; index < getBoard().length; index++){
					if(getBoard()[index] > 0){
						getBoard()[index]--;
					}
				}
			}else{
				removeCount = getCurrentPlayer() + 1;
				for(int index = 0; index < getBoard().length; index++){
					if(removeCount > getBoard().length - 1){
						removeCount = 0;
					}
					if(getBoard()[removeCount] > 0){
						getBoard()[removeCount]--;
						break;
					}
				}
			}
			if(getBoard()[getCurrentPlayer()] == 0){
				setCurrentPlayer(getCurrentPlayer() + 1);
				if(getCurrentPlayer() > getBoard().length - 1){
					setCurrentPlayer(0);
				}
			}else{
				distributeCurrentPlayerTokens();
				setCurrentPlayer(getCurrentPlayer() + 1);
				if(getCurrentPlayer() > getBoard().length - 1){
					setCurrentPlayer(0);
				}
			}
			setRound(getRound() + 1);
		}
		winner = getCurrentPlayer();
		return winner;
	}

}
