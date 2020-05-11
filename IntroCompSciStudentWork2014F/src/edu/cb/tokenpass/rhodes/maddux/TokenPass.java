package edu.cb.tokenpass.rhodes.maddux;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

public class TokenPass extends AbstractTokenPass {

	public static void main(String[] args) {
		TokenPass test = new TokenPass(5);
		test.setCurrentPlayer((int) (Math.random() * (test.getBoard().length)));
		System.out.println("Current Player: " + test.getCurrentPlayer());
		test.printArr(test.getBoard());
		test.distributeCurrentPlayerTokens();
		test.printArr(test.getBoard());
		System.out.println("Winner: " + test.playGame(test.getCurrentPlayer()));
		test.printArr(test.getBoard());
		
	}

	public TokenPass(int playerCount) {
		setBoard(createBoard(playerCount));
		setCurrentPlayer(((int) (Math.random() * playerCount)));
	}

	public int[] createBoard(int playerCount) {
		int[] arr = new int[playerCount];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ((int) (Math.random() * 10) + 1);
		}
		return arr;
	}

	public void distributeCurrentPlayerTokens() {
		int[] arr = getBoard();
		int numTokens = arr[getCurrentPlayer()];
		arr[getCurrentPlayer()] = 0;
		int currInd = getCurrentPlayer()+1;
		while (numTokens > 0) {
			if (currInd <= arr.length - 1) {
				arr[currInd]++;
				numTokens--;
			} else {
				currInd = -1;
			}
			currInd++;
		}
		setBoard(arr);
	}

	public int playGame(int currentPlayer) {
		setRound(1);
		setCurrentPlayer(currentPlayer);
		while (gameLive()) {
			if (getRound() % 5 == 0) {
				removeFull();
			} else {
				removeAfter();
			}
			distributeCurrentPlayerTokens();
			setRound(getRound()+1);
		}
		for(int i = 0; i < getBoard().length; i++) {
			if(getBoard()[i] != 0) {
				return i; 
			}
		}
		return 0;
	}

	public void removeFull() {
		int[] arr = getBoard();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				arr[i]--;
			}
		}
		setBoard(arr);
	}

	public void removeAfter() {
		int currInd = getCurrentPlayer() + 1;
		while(true) {
			if(currInd == getBoard().length) {
				currInd = 0;
			}
			if(getBoard()[currInd] > 0) {
				getBoard()[currInd]--;
				break;
			}
			currInd++;
		}
	}

	public boolean gameLive() {
		int countPlayerZeros = 0;
		for (int i : getBoard()) {
			if (i == 0) {
				countPlayerZeros++;
			}
		}
		if (countPlayerZeros == getBoard().length - 1) {
			return false;
		}
		return true;
	}

	public void printArr(int[] arr) {
		System.out.print("[" + arr[0] + ", ");
		for (int i = 1; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.print(arr[arr.length - 1] + "]");
		System.out.println();
	}
}
