/**
 * 
 */
package edu.jenks.cb.tokenpass.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import edu.jenks.dist.cb.tokenpass.AbstractTokenPass;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted
 *
 */
public class TokenPassTest extends Testable {
	
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {int.class};
	private static final Object[] DEFAULT_CONSTRUCTOR_VALUES = {10};
	
	private String studentClassName;
	private AbstractTokenPass studentTokenPass;

	/**
	 * 
	 */
	public TokenPassTest() {
	}
	
	public void testCreateBoard() {
		int playerCount = 100000;
		final int minTokens = 1;
		final int maxTokens = 10;
		NumberCount numberCount = new NumberCount(20, minTokens, maxTokens);
		int[] board = studentTokenPass.createBoard(playerCount);
		boolean tokensLegal = true;
		for(int index = board.length - 1; index >= 0 && tokensLegal; index--) {
			int tokens = board[index];
			if(tokens < 1 || tokens > 10) {
				tokensLegal = false;
				feedbackLogger.log(Level.WARNING, "Fail - initial tokens for each player must be between 1 and 10; found player with " + tokens);
			} else
				numberCount.tallyNumber(tokens);
		}
		if(tokensLegal) {
			totalPoints += 5;
			feedbackLogger.log(Level.INFO, "Pass - each player has a legal number of tokens.");
			boolean ratiosAcceptable = true;
			double minRatio = 1;
			double maxRatio = 0;
			for(int tokens = maxTokens; tokens >= minTokens && ratiosAcceptable; tokens--) {
				double ratio = numberCount.getCount(tokens) / (double)playerCount;
				if(ratio < .08) {
					ratiosAcceptable = false;
					feedbackLogger.log(Level.WARNING, "Fail - randomness is suspicious - not enough " + tokens);
				}
				if(ratio < minRatio)
					minRatio = ratio;
				else if(ratio > maxRatio)
					maxRatio = ratio;
			}
			if(ratiosAcceptable) {
				double ratioSpread = maxRatio - minRatio;
				if(ratioSpread < .001)
					feedbackLogger.log(Level.WARNING, "Fail - randomness is suspicious - ratio spread too narrow: " + ratioSpread);
				else {
					totalPoints += 10;
					feedbackLogger.log(Level.INFO, "Pass - acceptable randomness");
				}
			}
		}
	}
	
	private void testDistributeCurrentPlayerTokens(int[] initialBoard, int[] expectedBoard, int currentPlayer, String failMessage) {
		studentTokenPass.setBoard(initialBoard);
		studentTokenPass.setCurrentPlayer(currentPlayer);
		studentTokenPass.distributeCurrentPlayerTokens();
		if(currentPlayer == studentTokenPass.getCurrentPlayer() && Arrays.equals(expectedBoard, studentTokenPass.getBoard())) {
			totalPoints += 10;
			feedbackLogger.log(Level.INFO, "Pass");
		} else
			feedbackLogger.log(Level.WARNING, "Fail - " + failMessage);
	}
	
	public void testDistributeCurrentPlayerTokens() {
		// test 1
		int[] initialBoard1 = {3, 2, 6, 10};
		int currentPlayer = 2;
		int[] expectedBoard1 = {5, 3, 1, 12};
		testDistributeCurrentPlayerTokens(initialBoard1, expectedBoard1, currentPlayer, "given scenario");
		
		// test 2
		int[] initialBoard2 = {7, 1, 5, 3, 8};
		currentPlayer = 4;
		int[] expectedBoard2 = {9, 3, 7, 4, 1};
		testDistributeCurrentPlayerTokens(initialBoard2, expectedBoard2, currentPlayer, "given scenario");
	}
	
	private boolean testPlayGame(int[] board, int currentPlayer, int expWinner, int expRound, String message) {
		boolean pass = false;
		studentTokenPass.setBoard(board);
		studentTokenPass.setRound(1);
		int actWinner = studentTokenPass.playGame(currentPlayer);
		int actRound = studentTokenPass.getRound();
		if(expWinner == actWinner && expRound == actRound) {
			feedbackLogger.log(Level.INFO, "Pass - " + message);
			pass = true;
		} else
			feedbackLogger.log(Level.WARNING, "Fail - winner or round did not match - " + message);
		return pass;
	}
	
	public void testPlayGame() {
		//test 1
		int[] board1 = {1, 2, 3, 4};
		int currentPlayer = 2;
		int expWinner = 3;
		int expRound = 6;
		if(testPlayGame(board1, currentPlayer, expWinner, expRound, "given"))
			totalPoints += 3;
		
		//test 2
		int[] board2 = {3, 9, 10, 2, 5, 5, 2, 8};
		currentPlayer = 5;
		expWinner = 4;
		expRound = 24;
		if(testPlayGame(board2, currentPlayer, expWinner, expRound, "custom"))
			totalPoints += 2;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".TokenPass";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.cb.tokenpass.AbstractTokenPass");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		studentTokenPass = (AbstractTokenPass)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, DEFAULT_CONSTRUCTOR_VALUES);
		totalPoints += 60;
	}
}
