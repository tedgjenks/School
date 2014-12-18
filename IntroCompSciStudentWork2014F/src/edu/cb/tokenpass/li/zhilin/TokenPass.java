package edu.cb.tokenpass.li.zhilin;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

public class TokenPass extends AbstractTokenPass {

	public TokenPass(int players) {
		
	}

	@Override
	public int[] createBoard(int playercount) {
		int[] newBoard = new int [playercount];
		for (int index = playercount; index < playercount; index++){
			newBoard[index] =  1 + (int)(Math.random() * ( 11 - 1));
		}
		setBoard(newBoard);
		return newBoard;
	}
	
	@Override
	public void distributeCurrentPlayerTokens() {
		int board[] = getBoard();
		int startplayer = getCurrentPlayer();
		int disPlayer = board[startplayer];
		board[startplayer] = 0;
		for(;disPlayer > 0; disPlayer--){
			startplayer++;
		if(startplayer > board.length){
			startplayer = 0;
		}
			board[startplayer] = board[startplayer] + 1;
		}
		
	}

	@Override
	public int playGame(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
