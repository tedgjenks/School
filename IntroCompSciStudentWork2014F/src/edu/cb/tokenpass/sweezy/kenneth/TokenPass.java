package edu.cb.tokenpass.sweezy.kenneth;
import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

public class TokenPass extends AbstractTokenPass {
	private int pCount;
	
	public static void main(String[] args) {
		TokenPass testing = new TokenPass(10);
		printArray(testing.createBoard(10));
	}
	
	private static void printArray(int[] arr) {
		System.out.print("[");
		for(Object o : arr) {
			System.out.print(o + ", ");
		}
	}
	
	public TokenPass(int playerCount) {
		super();
		pCount = playerCount;
	}

	public int[] createBoard(int playerCount) {
		int[] board = new int[playerCount];
		for(int i = 0; i < playerCount; i++) {
			board[i] = (int)((Math.random() * 10) + 1);
		}
		return board;
	}

	public void distributeCurrentPlayerTokens() {
		
	}

	public int playGame(int arg0) {
		return 0;
	}

}
