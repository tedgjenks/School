package edu.cb.tokenpass.hollingsworth.james;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

public class TokenPass extends AbstractTokenPass {

	public TokenPass(int playerCount) {
		this.setBoard(this.createBoard(playerCount));
	}

	public int[] createBoard(int playerCount) {
		int[] board = new int[playerCount];
		for(int i = 0; i < playerCount; i++) {
			board[i] = (int) (Math.random() * 9 + 1);
		}
		return board;
	}

	public void distributeCurrentPlayerTokens() {
		int playerIndex = (this.getCurrentPlayer() + 1) % this.getBoard().length;
		int numTokens = this.getBoard()[this.getCurrentPlayer()];
		this.getBoard()[this.getCurrentPlayer()] = 0;
		for(int i = 0; i < numTokens; i++) {
			this.getBoard()[playerIndex]++;
			playerIndex = (playerIndex + 1) % this.getBoard().length;
		}
	}

	public int playGame(int currentPlayer) {
		this.setCurrentPlayer(currentPlayer);
		while(true) {
			// Find winner by counting number of people without tokens
			int numOut = 0;
			for(int tokens : this.getBoard()) {
				if(tokens == 0) numOut++;
			}
			if(numOut == this.getBoard().length - 1) break;
			
			// Take a token from players if round is divisible by 5
			if(this.getRound() % 5 == 0 && this.getRound() != 0) {
				for(int i = 0; i < this.getBoard().length; i++) {
					if(this.getBoard()[i] > 0)
						this.getBoard()[i]--;
				}
			}
			// Take token from next player with tokens
			else {
				int i = (this.getCurrentPlayer() + 1) % this.getBoard().length;
				while(i != this.getCurrentPlayer() && this.getBoard()[i] == 0) {
					i = (i + 1) % this.getBoard().length;
				}
				if(i != this.getCurrentPlayer()) this.getBoard()[i]--;
			}
			
			this.distributeCurrentPlayerTokens();
			this.setCurrentPlayer((this.getCurrentPlayer() + 1) % this.getBoard().length);
			this.setRound(this.getRound() + 1);

			// Print state of the game
			for(int i = 0; i < this.getBoard().length; i++) System.out.print(this.getBoard()[i] + " ");
			System.out.println(", " + this.getCurrentPlayer() + ", " + this.getRound());
		}
		return this.getCurrentPlayer();
	}

	public static void main(String[] args) {
		TokenPass p = new TokenPass(5);
		for(int i = 0; i < p.getBoard().length; i++) System.out.print(p.getBoard()[i] + " ");
		System.out.println();
		System.out.println(p.playGame(0));
	}

}
