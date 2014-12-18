package edu.cb.tokenpass.carter.noah;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;

import java.util.*;

public class TokenPass extends AbstractTokenPass {

	public TokenPass(int playerCount) {
		setBoard(createBoard(playerCount));
	}
	public static void main(String [] args) {
		//TokenPass tp = new TokenPass(4);
		//System.out.println(Arrays.toString(tp.createBoard(4)));
		//tp.distributeCurrentPlayerTokens();
		//System.out.println(Arrays.toString(tp.getBoard()));
	}
	@Override
	public int[] createBoard(int playerCount) {
		int size = playerCount;
		int[] boardArray = new int[size];
		Random rn = new Random();
    	for(int x = 0; x < playerCount; x++){
    		int randomInt = rn.nextInt(10) + 1;
    		boardArray[x] = randomInt;
	    }
	    return boardArray;
		} 
	
	@Override
	public void distributeCurrentPlayerTokens() {
		int[] something = getBoard();
		int tokens = something[getCurrentPlayer()];
        something[getCurrentPlayer()] = 0;
        int newIndex = getCurrentPlayer() + 1;
        while(tokens > 0)
        {if(newIndex == something.length)
                newIndex = 0;
            something[newIndex] ++;
            tokens --;
            newIndex ++;
            //System.out.println(Arrays.toString(something));
		}
        setBoard(something);
	}

	@Override
	public int playGame(int currentPlayer) {
		// TODO Auto-generated method stub
		return 0;
	}

}
