package edu.jenks.palindrome.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.palindrome.AbstractPalindromeChecker;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class PalindromeCheckerTest extends Testable {
	
	private String studentClassName;
	private AbstractPalindromeChecker studentPC, solutionPC = new edu.palindrome.jenks.ted.PalindromeChecker();

	public void testIsPalindrome() {
		int points = 5;
		testPhrase("mom", points);
		testPhrase("Mom", points);
		testPhrase("Dad!", points);
		testPhrase("A Man, a plan, a canal: Panama. -> A Man, a plan, a canal: Panama.", points);
		testPhrase("Rude", points);
		testPhrase("%%", points);
		testPhrase("Racecar", points);
		testPhrase("Racecarr", points);
		testPhrase("Are we not pure? \"No sir!\" Panama’s moody Noriega brags. \"It is garbage!\" Irony dooms a man; a prisoner up to new era.", points);
		testPhrase("bmbamb", points);
	}
	
	private void testPhrase(String phrase, int points) {
		boolean expected = solutionPC.isPalindrome(phrase);
		boolean actual = studentPC.isPalindrome(phrase);
		System.out.println(phrase + ": " + expected);
		if(expected == actual) {
			totalPoints += points;
			logPass(phrase);
		} else
			logFail(phrase, String.valueOf(expected), String.valueOf(actual), points);
	}
	
	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".PalindromeChecker";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.palindrome.AbstractPalindromeChecker");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentPC = (AbstractPalindromeChecker)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 50;
		logPass("object creation");
	}

}
