/**
 * 
 */
package edu.jenks.barrons.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.barrons.AbstractSentence;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;
import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
public class SentenceTest extends Testable {
	
	private static final String I_LOVE_YOU = "I love you!",
			CAT = "The cat sat on the mat.",
			WHY = "Why?",
			BIRD = "The bird flew away.",
			WOW = "Wow!",
			HI = "Hi! How are you?";
	private static final Random RANDOM	= new Random(System.currentTimeMillis());
	
	private AbstractSentence studentSentence, solutionSentence = new edu.barrons.jenks.ted.Sentence();

	/**
	 * 
	 */
	public SentenceTest() {}
	
	// 20 points
	public void test03GetWords() {
		final String method = "getWords";
		boolean passAll = true;
		int points = 10;
		String[] exp;
		
		exp = new String[]{"The", "bird", "flew", "away."};
		if(!passGetWords(method, BIRD, exp, points))
			passAll = false;
		exp = new String[]{"Wow!"};
		if(!passGetWords(method, WOW, exp, points))
			passAll = false;
		exp = new String[]{"Hi!", "How", "are", "you?"};
		if(!passGetWords(method + " (basic)", HI, exp, points))
			passAll = false;
		
		if(passAll)
			totalPoints += points;
		
		String sentence = buildRandomString();
		solutionSentence.setSentence(sentence);
		if(!passGetWords(method + " (random)", sentence, solutionSentence.getWords(), points))
			passAll = false;
		else
			totalPoints += points;
		
		if(passAll)
			logPass(method);
	}
	
	private boolean passGetWords(String message, String sentence, String[] exp, int points) {
		studentSentence.setSentence(sentence);
		String[] act = studentSentence.getWords();
		boolean pass = Arrays.equals(exp, act);
		if(!pass)
			logFail(message, Arrays.toString(exp), Arrays.toString(act), points);
		return pass;
	}
	
	// 20 points
	public void test02CountWords() {
		final String method = "countWords";
		boolean passAll = true;
		int points = 10;
		
		if(!passCountWords(method, I_LOVE_YOU, 3))
			passAll = false;
		if(!passCountWords(method, CAT, 6))
			passAll = false;
		if(!passCountWords(method, WHY, 1))
			passAll = false;
		
		if(passAll)
			totalPoints += points;
		
		String sentence = buildRandomString();
		solutionSentence.setSentence(sentence);
		if(!passCountWords(method, sentence, solutionSentence.countWords()))
			passAll = false;
		else
			totalPoints += points;
		
		if(passAll)
			logPass(method);
	}
	
	private boolean passCountWords(String message, String sentence, int exp) {
		studentSentence.setSentence(sentence);
		boolean pass = exp == studentSentence.countWords();
		if(!pass)
			logFail(message);
		return pass;
	}
	
	// 20 points
	public void test01GetBlankPositions() {
		final String method = "getBlankPositions";
		boolean passAll = true;
		int points = 10;
		List<Integer> exp;
		
		exp = new ArrayList<Integer>(Arrays.asList(1, 6));
		if(!passBlankPositions(method, I_LOVE_YOU, exp))
			passAll = false;
		exp = new ArrayList<Integer>(Arrays.asList(3, 7, 11, 14, 18));
		if(!passBlankPositions(method, CAT, exp))
			passAll = false;
		exp = new ArrayList<Integer>();
		if(!passBlankPositions(method, WHY, exp))
			passAll = false;
		
		if(passAll)
			totalPoints += points;
		
		String sentence = buildRandomString();
		solutionSentence.setSentence(sentence);
		exp = solutionSentence.getBlankPositions();
		if(!passBlankPositions(method, sentence, exp))
			passAll = false;
		else
			totalPoints += points;
		
		if(passAll)
			logPass(method);
	}
	
	private boolean passBlankPositions(String message, String sentence, List<Integer> exp) {
		studentSentence.setSentence(sentence);
		List<Integer> act = studentSentence.getBlankPositions();
		boolean pass = exp.equals(act);
		if(!pass)
			logFail(message + " - " + sentence);
		return pass;
	}
	
	private String buildRandomString() {
		int numberWords = RANDOM.nextInt(5) + 3;
		StringBuilder sb = new StringBuilder(numberWords * 5);
		for(; numberWords > 1; numberWords--) {
			sb.append(StringUtil.buildString('a', RANDOM.nextInt(5) + 2));
			sb.append(" ");
		}
		return sb.append(StringUtil.buildString('a', RANDOM.nextInt(5) + 2)).toString();
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 100;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<String, String>(4);
		map.put(studentPackage + ".Sentence", AbstractSentence.class.getName());
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentSentence = (AbstractSentence)ReflectionUtil.newInstance(studentPackage + ".Sentence");
		totalPoints += 40;
	}

}
