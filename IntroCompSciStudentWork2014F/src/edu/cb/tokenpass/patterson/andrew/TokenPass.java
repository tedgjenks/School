package edu.cb.tokenpass.patterson.andrew;

import java.util.*;

import edu.jenks.dist.cb.tokenpass.*;

public class TokenPass extends AbstractTokenPass{

	public TokenPass(int playerCount) {
		/*int[] cb = createBoard(playerCount);
		setBoard(cb);*/
	}

	@Override
	public int[] createBoard(int playerCount) {
		int[] bc= new int [playerCount];
		for (int index=0; index <= playerCount; index ++) {
			int number = (int)(Math.random()*10);
			number+=1;
			bc[index]=number;
		}
		return bc;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		
	}

	@Override
	public int playGame(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
