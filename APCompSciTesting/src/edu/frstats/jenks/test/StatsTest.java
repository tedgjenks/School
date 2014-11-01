package edu.frstats.jenks.test;

import java.util.List;
import java.util.logging.Level;

import edu.frstats.jenks.dist.ScoreInfo;
import edu.frstats.jenks.dist.StatsRecorder;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class StatsTest extends Testable {
	
	private static final StatsTest SINGLETON = new StatsTest();
	
	// 2 points
	public void test40RecordMultipleScores() {
		String testMessage = "record multiple scores";
		StatsRecorder expected = new edu.frstats.jenks.Stats();
		StatsRecorder actual = createInstance();
		int[] scores = new int[10000];
		for(int index = scores.length - 1; index >= 0; index--)
			scores[index] = (int)(Math.random() * 100);
		expected.recordScores(scores);
		actual.recordScores(scores);
		if(assertEqualStatsRecorder(expected, actual)) {
			totalPoints += 2;
			logger.log(Level.FINE, "Pass - " + testMessage);
		} else {
			continueTesting = false;
			logger.log(Level.INFO, "Fail - " + testMessage);
		}
	}
	
	// 2 points
	public void test30RecordRandomScores() {
		String testMessage = "record random scores";
		StatsRecorder expected = new edu.frstats.jenks.Stats();
		StatsRecorder actual = createInstance();
		for(int count = 10000; count > 0; count--) {
			int randomScore = (int)(Math.random() * 100);
			addScore(randomScore, expected, actual);
		}
		if(assertEqualStatsRecorder(expected, actual)) {
			totalPoints += 2;
			logger.log(Level.FINE, "Pass - " + testMessage);
		} else {
			continueTesting = false;
			logger.log(Level.INFO, "Fail - " + testMessage);
		}
	}
	
	// 2 points
	public void test20ExistingScores() {
		StatsRecorder expected = new edu.frstats.jenks.Stats();
		StatsRecorder actual = createInstance();
		
		for(int score = 100; score > 0; score -= 10) {
			if(!addScore(score, expected, actual)) {
				return;
			}
		}
		
		String testMessage = "increment head";
		if(!testAddScore(testMessage, 10, expected, actual))
			return;
		
		testMessage = "increment tail";
		if(!testAddScore(testMessage, 100, expected, actual))
			return;
		
		testMessage = "increment middle";
		if(!testAddScore(testMessage, 50, expected, actual))
			return;
		
		totalPoints += 2;
		logger.log(Level.FINE, "Pass - existing scores");
	}
	
	// 3 points
	public void test10NewScores() {
		StatsRecorder expected = new edu.frstats.jenks.Stats();
		StatsRecorder actual = createInstance();
				
		String testMessage = "empty";
		if(!assertEqualStatsRecorder(expected, actual)) {
			logger.log(Level.INFO, "Fail - " + testMessage);
			continueTesting = false;
			return;
		}
		
		testMessage = "add at end";
		for(int score = 60; score <= 100; score += 10) {
			if(!testAddScore(testMessage, score, expected, actual))
				return;
		}
		
		testMessage = "add at beginning";
		for(int score = 50; score > 0; score -= 10) {
			if(!testAddScore(testMessage, score, expected, actual))
				return;
		}
		
		testMessage = "add in middle";
		if(!testAddScore(testMessage, 45, expected, actual))
			return;
		
		totalPoints += 3;
		logger.log(Level.FINE, "Pass - new scores");
	}
	
	public void test5CreateInstance() {
		createInstance();
	}
	
	private boolean testAddScore(String testMessage, int score, StatsRecorder expected, StatsRecorder actual) {
		if(!addScore(score, expected, actual) || !assertEqualStatsRecorder(expected, actual)) {
			logger.log(Level.INFO, "Fail - " + testMessage);
			continueTesting = false;
		}
		return continueTesting;
	}
	
	private boolean addScore(int score, StatsRecorder expected, StatsRecorder actual) {
		boolean scoreInfoCreationMatch = expected.record(score) == actual.record(score);
		if(!scoreInfoCreationMatch)
			logger.log(Level.INFO, "Fail - record return value incorrect");
		return scoreInfoCreationMatch;
	}
	
	private static boolean assertEqualStatsRecorder(StatsRecorder expected, StatsRecorder actual) {
		List<ScoreInfo> expectedList;
		if(expected == null || actual == null || (expectedList = expected.getScoreList()) == null)
			throw new IllegalArgumentException("Neither expected nor actual should not be null.");
		List<ScoreInfo> actualList = actual.getScoreList();
		boolean equal = false;
		if(actualList != null) {
			int expectedSize = expectedList.size(), actualSize = actualList.size();
			if(expectedSize == actualSize) {
				equal = true;
				for(int index = expectedSize - 1; index >= 0 && equal; index--)
					equal = assertEqualScoreInfo(expectedList.get(index), actualList.get(index));
			}
		}
		return equal;
	}
	
	private static boolean assertEqualScoreInfo(ScoreInfo expected, ScoreInfo actual) {
		if(expected == null)
			throw new IllegalArgumentException("Expected should not be null.");
		boolean equal = actual != null &&
				expected.getScore() == actual.getScore() &&
				expected.getFrequency() == actual.getFrequency();
		return equal;
	}

	@Override
	public String getLogFilePath() {
		return "src/edu/frstats/jenks/test/log.txt";
	}

	@Override
	public Testable getSingleton() {
		return SINGLETON;
	}
	
	@Override
	public void setUp() {
		super.setUp();
		verifySuperclass(Object.class.getName(), createInstance());
	}
	
	private StatsRecorder createInstance() {
		StatsRecorder statsRecorder = null;
		try {
			statsRecorder = (StatsRecorder)ReflectionUtil.newInstance(studentPackage + ".Stats");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			logger.log(Level.WARNING, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
		return statsRecorder;
	}
	
	public static void main(String[] args) {
		SINGLETON.execute();
	}
}
