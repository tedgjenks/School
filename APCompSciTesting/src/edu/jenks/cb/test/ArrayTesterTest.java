/**
 * 
 */
package edu.jenks.cb.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import edu.jenks.dist.cb.TestableArray;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Jenks
 *
 */
public class ArrayTesterTest extends Testable {
	
	private String studentClassName;
	private TestableArray studentArrayTester, solutionArrayTester;
	
	public void test02HasAllValues() {
		String message = "hasAllValues";
		int points = 2;
		boolean allPass = true;
		int[] arr1 = {}, arr2 = {};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		arr1 = new int[] {0};
		arr2 = new int[] {0};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		if(allPass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	private boolean hasAllValuesCorrect(String message, int points, boolean exp, int[] arr1, int[] arr2) {
		boolean pass = true;
		boolean act = studentArrayTester.hasAllValues(arr1, arr2);
		pass = exp == act;
		if(!pass)
			logFail(message + " " + Arrays.toString(arr1) + " " + Arrays.toString(arr2), exp, act, points);
		return pass;
	}
	
	public void test01GetColumn() {
		String message = "getColumn";
		int points = 2;
		boolean allPass = true;
		int[][] arr2D = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 5, 3}};
		int[][] exp = {{0, 3, 6, 9}, {1, 4, 7, 5}, {2, 5, 8, 3}};
		for(int colIndex = arr2D[0].length - 1; colIndex >= 0; colIndex--) {
			if(!columnCorrect(arr2D, colIndex, exp[colIndex], message, points))
				allPass = false;
		}
		if(allPass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	private boolean columnCorrect(int[][] arr2D, int colIndex, int[] exp, String message, int points) {
		boolean pass = true;
		int[] act = studentArrayTester.getColumn(arr2D, colIndex);
		if(!Arrays.equals(exp, act)) {
			logFail(message, Arrays.toString(exp), Arrays.toString(act), points);
			pass = false;
		}
		return pass;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 4;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".ArrayTester";
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
		solutionArrayTester = new edu.cb.jenks.ted.ArrayTester();
		studentArrayTester = (TestableArray)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 0.5 * getPointsAvailable();
	}

}
