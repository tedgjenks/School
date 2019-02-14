/**
 * 
 */
package edu.jenks.cb.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import edu.jenks.dist.cb.wordpair.*;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Jenks
 *
 */
public class WordPairListTest extends Testable {
	
	private static final Class<?>[] WORD_PAIR_LIST_CONSTRUCTOR_PARAM_TYPES = {String[].class};
	private static final String[] EXAMPLE_1 = {"one", "two", "three"};
	private static final String[] EXAMPLE_2 = {"the", "more", "the", "merrier"};
	private static final String[] EXAMPLE_3 = {"the", "red", "fox", "the", "red"};
	private static final String[] EXAMPLE_4 = {"the", "house", "on", "haunted", "hill", "is", "the", "scariest", "house", "on", "the", "hill"};
	
	private String studentClassName;
	private AbstractWordPairList studentWordPairList, solutionWordPairList;
	
	public void test08Example4NumMatches() {
		testNumMatches(1, "example 4 num matches", solutionWordPairList.numMatches());
	}
	
	public void test07Example4Const() {
		studentWordPairList = instantiateStudentWordPairList(EXAMPLE_4);
		solutionWordPairList = instantiateSolutionWordPairList(EXAMPLE_4);
		List<WordPair> expected = solutionWordPairList.allPairs;
		testConst(1, "example 4 constructor", expected);
	}
	
	public void test06Example3NumMatches() {
		testNumMatches(1, "example 3 num matches", 2);
	}
	
	public void test05Example3Const() {
		studentWordPairList = instantiateStudentWordPairList(EXAMPLE_3);
		WordPair[] words = {new WordPair("the", "red"), new WordPair("the", "fox"), new WordPair("the", "the"),
				new WordPair("the", "red"), new WordPair("red", "fox"), new WordPair("red", "the"),
				new WordPair("red", "red"), new WordPair("fox", "the"), new WordPair("fox", "red"),
				new WordPair("the", "red")};
		List<WordPair> expected = new ArrayList<WordPair>(Arrays.asList(words));
		testConst(1, "example 3 constructor", expected);
	}
	
	public void test04Example2NumMatches() {
		testNumMatches(1, "example 2 num matches", 1);
	}
	
	public void test03Example2Const() {
		studentWordPairList = instantiateStudentWordPairList(EXAMPLE_2);
		WordPair[] words = {new WordPair("the", "more"), new WordPair("the", "the"), new WordPair("the", "merrier"),
				new WordPair("more", "the"), new WordPair("more", "merrier"), new WordPair("the", "merrier")};
		List<WordPair> expected = new ArrayList<WordPair>(Arrays.asList(words));
		testConst(1, "example 2 constructor", expected);
	}
	
	public void test02Example1NumMatches() {
		testNumMatches(1, "example 1 num matches", 0);
	}
	
	public void test01Example1Const() {
		WordPair[] words = {new WordPair("one", "two"), new WordPair("one", "three"), new WordPair("two", "three")};
		List<WordPair> expected = new ArrayList<WordPair>(Arrays.asList(words));
		testConst(1, "example 1 constructor", expected);
	}
	
	private void testConst(int points, String message, List<WordPair> expected) {
		List<WordPair> actual = new ArrayList<>(studentWordPairList.allPairs);
		if(verifyExpectedInActual(expected, actual)) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message, expected, studentWordPairList.allPairs, points);
			continueTesting = false;
		}
	}
	
	private void testNumMatches(int points, String message, int expected) {
		int actual = studentWordPairList.numMatches();
		if(expected == actual) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message, expected, actual, points);
			continueTesting = false;
		}
	}
	
	private boolean verifyExpectedInActual(List<WordPair> expected, List<WordPair> actual) {
		boolean pass = actual != null && actual.size() == expected.size();
		if(pass) {
			for(WordPair wp : expected) {
				if(!actual.remove(wp))
					pass = false;
			}
		}
		return pass;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 16;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".WordPairList";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.cb.wordpair.AbstractWordPairList");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		solutionWordPairList = instantiateSolutionWordPairList(EXAMPLE_1);
		studentWordPairList = instantiateStudentWordPairList(EXAMPLE_1);
		totalPoints += 8;
	}
	
	private AbstractWordPairList instantiateSolutionWordPairList(String[] words) {
		return new edu.cb.wordpair.jenks.ted.WordPairList(words);
	}
	
	private AbstractWordPairList instantiateStudentWordPairList(String[] words) {
		AbstractWordPairList instance = null;
		Object[] constructorArgs = {words};
		try {
			instance = (AbstractWordPairList)ReflectionUtil.newInstance(studentClassName, WORD_PAIR_LIST_CONSTRUCTOR_PARAM_TYPES, constructorArgs);
		} catch(Exception e) {
			logFail("instantiate");
		}
		return instance;
	}

}
