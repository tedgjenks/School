package edu.cb.tokenpass.latham.chase;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;
import java.util.*;

public class TokenPass extends AbstractTokenPass {
	int [] Players = {1,2};
    int[] array1 = {1,2,3,4,5,6,7,8,9,10};
    ///int [] arrayBoard = {Players++};
    int [] Token = {1,2,3,4,5,6,7,8,9,10};
    
	public TokenPass(int playerCount) {
		for (int Tokens = 10; Tokens < Token.length; Tokens++) {
			for (int Token : Players) {
				Players.equals(Token++);
			}
		}
	}
	@Override
	public int[] createBoard(int playerCount) {
		for (int Players = 0; Players < array1.length; Players++) {
			int j = array1[Players];
			for (int Player : array1) {
				Players = playerCount;
			}
			
		}
		return null;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		for (int Player : Token) {
			
		}

	}

	@Override
	public int playGame(int arg0) {
		
		return 0;
	}

}
