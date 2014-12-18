package edu.cb.tokenpass.hackett.joshua;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

public class TokenPass extends AbstractTokenPass {

	public int random() {
		int randy = (int)(Math.random()* 10) + 1;
		return randy;
	}
	
	public TokenPass(int playerCount) {
		
	}
	
	@Override
	public int[] createBoard(int playerCount) {
		int[] pc = new int[playerCount];
		for(int index = 0; index < pc.length; index++) {
			pc[index] = random();
		}
		setBoard(pc);
		return pc;
	}

	@Override
	public void distributeCurrentPlayerTokens() {
		//for(int index = 0; index < pc.length; index++) {
			
		

	}

	@Override
	public int playGame(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
