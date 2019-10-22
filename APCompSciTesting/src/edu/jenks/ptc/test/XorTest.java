/**
 * 
 */
package edu.jenks.ptc.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.ptc.Xorable;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;
import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
public class XorTest extends Testable {
	
	private final Xorable SOLUTION = new edu.ptc.jenks.ted.Xor();
	private Xorable studentXor;
	private String studentClassName;
	
	public void test10tXorBinaryStringOne() {
		int points = 2;
		String message = "testOne";
		if(studentXor.xorBinaryString("1") && !studentXor.xorBinaryString("0")) {
			logPass(message);
			totalPoints += points;
		} else {
			continueTesting = false;
			logFail(message);
		}
	}
	
	public void test20XorBinaryStringTwo() {
		int points = 2;
		String message = "testTwo";
		boolean passAll = true;
		String[] inputs = {"11", "10", "01", "00"};
		for(int index = inputs.length - 1; index >= 0; index--) {
			String inputMessage = message + ": " + inputs[index];
			if(SOLUTION.xorBinaryString(inputs[index]) == studentXor.xorBinaryString(inputs[index])) {
				logPass(inputMessage);
				totalPoints += points;
			} else {
				passAll = false;
				logFail(inputMessage);
			}
		}
		
		if(!passAll)
			continueTesting = false;
	}
	
	public void test30XorBinaryStringRandom() {
		int points = 4, numTests = 10, numChars = 3;
		String message = "testRandom";
		for(int test = numTests; test > 0; test--, numChars++) {
			String input = StringUtil.buildRandomString(numChars, '0', '1');
			String inputMessage = message + ": " + input;
			if(SOLUTION.xorBinaryString(input) == studentXor.xorBinaryString(input)) {
				logPass(inputMessage);
				totalPoints += points;
			} else {
				logFail(inputMessage);
				continueTesting = false;
				break;
			}
		}
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
		studentClassName = studentPackage + ".Xor";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "java.lang.Object");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentXor = (Xorable)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 50;
	}

}
