package edu.jenks.cb.scrambler.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import edu.cb.scrambler.jenks.ted.WordScrambler;
import edu.jenks.dist.cb.scrambler.Scrambler;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class ScramblerTest extends Testable {
	
	private static final String EXPECTED_SUPERCLASS = "java.lang.Object";
	private static final String[] TEST_WORDS = {"TAN", "ABRACADABRA", "WHOA",
		"AARDVARK", "EGGS", "A", "", "BAKER", "EEL"};
	private static final String[] TEST_LIST = {"TAN", "ABRACADABRA", "WHOA",
		"APPLE", "EGGS", "", "DAPPER"};
		
	private final List<String> SOLUTION_LIST = new ArrayList<String>();	
	private Scrambler studentScrambler, solutionScrambler = new WordScrambler();
	
	public ScramblerTest() {
		for(String word : TEST_LIST)
			SOLUTION_LIST.add(word);
		solutionScrambler.scrambleOrRemove(SOLUTION_LIST);
	}
	
	public void testScrambleWord() {
		int passed = 0;
		for(String testWord : TEST_WORDS) {
			if(solutionScrambler.scrambleWord(testWord).equals(studentScrambler.scrambleWord(testWord)))
				passed++;
		}
		totalPoints += passed;
		feedbackLogger.log(Level.INFO, passed + " passed out of " + TEST_WORDS.length);
	}
	
	public void testScrambleOrRemove() {
		List<String> studentList = new ArrayList<String>();
		for(String word : TEST_LIST)
			studentList.add(word);
		studentScrambler.scrambleOrRemove(studentList);
		int minLength = Math.min(SOLUTION_LIST.size(), studentList.size());
		int points = 0;
		for(int index = 0; index < minLength; index++) {
			if(SOLUTION_LIST.get(index).equals(studentList.get(index)))
				points++;
		}
		totalPoints += points;
		feedbackLogger.log(Level.INFO, points + " passed out of " + SOLUTION_LIST.size());
	}
	
	@Override
	public void setUp() {
		super.setUp();
		try {
			studentScrambler = (Scrambler)ReflectionUtil.newInstance(studentPackage + ".WordScrambler");
			feedbackLogger.log(Level.INFO, "scrambler instantiated");
		} catch (Exception e) {
			feedbackLogger.log(Level.SEVERE, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
	}

	@Override
	public void verifySuperClass() {
		continueTesting = false;
		if(studentScrambler != null) {
			// verify that Object is the superclass
			String superclassName = studentScrambler.getClass().getSuperclass().getName();
			continueTesting = EXPECTED_SUPERCLASS.equals(superclassName);
			if(!continueTesting)
				feedbackLogger.log(Level.WARNING, "Actual superclass did not match expected superclass");
			else
				feedbackLogger.log(Level.FINE, "Superclass validated.");
		}
	}

	@Override
	public int getPointsAvailable() {
		return TEST_WORDS.length + SOLUTION_LIST.size();
	}

}
