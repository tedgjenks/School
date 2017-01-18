/**
 * 
 */
package edu.jenks.barrons.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.barrons.matrix.AbstractArrayUtil;
import edu.jenks.dist.barrons.matrix.AbstractMatrix;
import edu.jenks.test.Testable;
import edu.jenks.util.CollectionUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class MatrixTest extends Testable {
	private static final Class<?>[] MATRIX_CONSTRUCTOR_PARAM_TYPES = {int[][].class};
	
	private AbstractArrayUtil studentArrayUtil, solutionArrayUtil;

	/**
	 * 
	 */
	public MatrixTest() {
		solutionArrayUtil = new edu.barrons.matrix.jenks.ted.ArrayUtil();
	}
	
	// 19 points
	public void test03ReverseMatrix() {
		int points = 10;
		//example
		int[][] exp = createArray2D(3, 2);
		int[][] act = CollectionUtil.deepCopy(exp);
		AbstractMatrix solutionMatrix = createSolutionMatrix(exp);
		AbstractMatrix studentMatrix = createStudentMatrix(act);
		solutionMatrix.reverseMatrix();
		studentMatrix.reverseMatrix();
		if(Arrays.deepEquals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseMatrix", Arrays.toString(exp), Arrays.toString(act), points);
			return;
		}
		
		points = 3;
		exp = createArray2D(1, 3);
		act = CollectionUtil.deepCopy(exp);
		solutionMatrix = createSolutionMatrix(exp);
		studentMatrix = createStudentMatrix(act);
		solutionMatrix.reverseMatrix();
		studentMatrix.reverseMatrix();
		if(Arrays.deepEquals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseMatrix", Arrays.toString(exp), Arrays.toString(act), points);
			return;
		}
		
		exp = createArray2D(2, 4);
		act = CollectionUtil.deepCopy(exp);
		solutionMatrix = createSolutionMatrix(exp);
		studentMatrix = createStudentMatrix(act);
		solutionMatrix.reverseMatrix();
		studentMatrix.reverseMatrix();
		if(Arrays.deepEquals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseMatrix", Arrays.toString(exp), Arrays.toString(act), points);
			return;
		}
		
		exp = createArray2D(4, 5);
		act = CollectionUtil.deepCopy(exp);
		solutionMatrix = createSolutionMatrix(exp);
		studentMatrix = createStudentMatrix(act);
		solutionMatrix.reverseMatrix();
		studentMatrix.reverseMatrix();
		if(Arrays.deepEquals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseMatrix", Arrays.toString(exp), Arrays.toString(act), points);
			return;
		}
	}
	
	// 11 points
	public void test02ReverseAllRows() {
		int points = 5;
		//example
		int[][] act = createArray2D(3, 4);
		int[][] exp = CollectionUtil.deepCopy(act);
		AbstractMatrix studentMatrix = createStudentMatrix(act);
		AbstractMatrix solutionMatrix = createSolutionMatrix(exp);
		studentMatrix.reverseAllRows();
		solutionMatrix.reverseAllRows();
		if(Arrays.deepEquals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseAllRows", Arrays.toString(exp), Arrays.toString(act), points);
			return;
		}
		
		points = 3;
		act = createArray2D(5, 1);
		exp = CollectionUtil.deepCopy(act);
		studentMatrix = createStudentMatrix(act);
		solutionMatrix = createSolutionMatrix(exp);
		studentMatrix.reverseAllRows();
		solutionMatrix.reverseAllRows();
		if(Arrays.deepEquals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseAllRows", Arrays.toString(exp), Arrays.toString(act), points);
			return;
		}
		
		act = createArray2D(4, 5);
		exp = CollectionUtil.deepCopy(act);
		studentMatrix = createStudentMatrix(act);
		solutionMatrix = createSolutionMatrix(exp);
		studentMatrix.reverseAllRows();
		solutionMatrix.reverseAllRows();
		if(Arrays.deepEquals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseAllRows", Arrays.toString(exp), Arrays.toString(act), points);
			return;
		}
	}
	
	// 20 points
	public void test01ReverseArray() {
		int points = 10;
		// example
		int[] act = {2, 7, 5, 1, 0};
		int[] exp = Arrays.copyOf(act, act.length);
		studentArrayUtil.reverseArray(act);
		solutionArrayUtil.reverseArray(exp);
		if(Arrays.equals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseArray example", Arrays.toString(exp), Arrays.toString(act), points);
			continueTesting = false;
			return;
		}
		
		act = createArray(1);
		exp = Arrays.copyOf(act, act.length);
		studentArrayUtil.reverseArray(act);
		solutionArrayUtil.reverseArray(exp);
		points = 5;
		if(Arrays.equals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseArray example", Arrays.toString(exp), Arrays.toString(act), points);
			continueTesting = false;
			return;
		}
		
		act = createArray(4);
		exp = Arrays.copyOf(act, act.length);
		studentArrayUtil.reverseArray(act);
		solutionArrayUtil.reverseArray(exp);
		if(Arrays.equals(exp, act))
			totalPoints += points;
		else {
			logFail("reverseArray example", Arrays.toString(exp), Arrays.toString(act), points);
			continueTesting = false;
			return;
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
		Map<String, String> map = new HashMap<String, String>(4);
		map.put(studentPackage + ".ArrayUtil", "edu.jenks.dist.barrons.matrix.AbstractArrayUtil");
		map.put(studentPackage + ".Matrix", "edu.jenks.dist.barrons.matrix.AbstractMatrix");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentArrayUtil = (AbstractArrayUtil)ReflectionUtil.newInstance(studentPackage + ".ArrayUtil");
		int[][] arr = {{1, 2}, {3, 4}};
		AbstractMatrix matrix = createStudentMatrix(arr);
		if(matrix == null)
			throw new InstantiationException(studentPackage + ".Matrix could not be created");
		totalPoints += 50;
	}
	
	private AbstractMatrix createStudentMatrix(int[][] arr) {
		Object[] args = {arr};
		AbstractMatrix matrix = null;
		try {
			matrix = (AbstractMatrix)ReflectionUtil.newInstance(studentPackage + ".Matrix", MATRIX_CONSTRUCTOR_PARAM_TYPES, args);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logFail("Matrix could not be instantiated.");
		}
		return matrix;
	}

	private AbstractMatrix createSolutionMatrix(int[][] arr) {
		return new edu.barrons.matrix.jenks.ted.Matrix(arr);
	}
	
	private int[][] createArray2D(int rows, int cols) {
		int[][] arr = new int[rows][cols];
		int value = 1;
		for(int rowIndex = 0; rowIndex < rows; rowIndex++) {
			for(int colIndex = 0; colIndex < cols; colIndex++)
				arr[rowIndex][colIndex] = value++;
		}
		return arr;
	}
	
	private int[] createArray(int size) {
		int[] arr = new int[size];
		for(int index = size - 1; index >= 0; index--)
			arr[index] = index + 1;
		return arr;
	}
}
