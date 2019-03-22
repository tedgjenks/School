/**
 * 
 */
package edu.jenks.cb.test;
import static java.lang.System.out;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import edu.jenks.dist.cb.TestableArray;
import edu.jenks.test.Testable;
import edu.jenks.util.CollectionUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Jenks
 *
 */
public class ArrayTesterTest extends Testable {
	
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final short[] RANDOM_LATIN_SQUARE_SEED_VALUES;
	
	static {
		final short NUM_VALUES = 1000;
		RANDOM_LATIN_SQUARE_SEED_VALUES = new short[NUM_VALUES];
		for(short index = NUM_VALUES - 1; index >= 0; index--)
			RANDOM_LATIN_SQUARE_SEED_VALUES[index] = index;
	}
	
	private String studentClassName;
	private TestableArray studentArrayTester, solutionArrayTester;
	
	public void test04IsLatin() {
		String message = "isLatin";
		int points = 3;
		boolean allPass = areLatinExamplesCorrect(message, points);
		
		final String duplicateMessage = message + " duplicate first row";
		testDuplicates:
		for(int testsPerSideLength = 1000; testsPerSideLength > 0; testsPerSideLength--) {
			for(int sideLength = 22; sideLength >= 2; sideLength -= 4) {
				int[][] square = generateDuplicateFirstRow(sideLength);
				if(!isLatinCorrect(duplicateMessage, points, false, square)) {
					allPass = false;
					break testDuplicates;
				}
			}
		}
		
		final String firstRowElementMissingMessage = message + " first row element missing in other rows";
		testMissing:
		for(int testsPerSideLength = 1000; testsPerSideLength > 0; testsPerSideLength--) {
			for(int sideLength = 22; sideLength >= 2; sideLength -= 4) {
				int[][] square = generateRowMissingFirstRowElement(sideLength);
				if(!isLatinCorrect(firstRowElementMissingMessage, points, false, square)) {
					allPass = false;
					break testMissing;
				}
			}
		}
		
		final String latinSquaresMessage = message + " Latin squares";
		for(int sideLength = 30; sideLength >= 2; sideLength--) {
			int[][] square = generateLatinSquare(sideLength);
			if(!isLatinCorrect(latinSquaresMessage, points, true, square)) {
				allPass = false;
				break;
			}
		}
		
		final String randomSquaresNoneMissing = message + " random squares - no missing elements";
		for(int sideLength = 10; sideLength >= 2; sideLength--) {
			//int countLatinSquares = 0;
			final long totalIterations = (long)Math.pow(2, sideLength * 1.5);
			for(long iterations = totalIterations; iterations > 0; iterations--) {
				int[][] square = generateRandomLatinSquare(sideLength);
				//printArray(square);
				boolean exp = solutionArrayTester.isLatin(square);
				/*if(exp)
					countLatinSquares++;*/
				if(!isLatinCorrect(randomSquaresNoneMissing, points, exp, square)) {
					allPass = false;
					break;
				}
			}
			//out.println(countLatinSquares + " random Latin squares produced for side length " + sideLength + " out of " + totalIterations + " generated.");
		}
		
		if(allPass) {
			logPass(message + " random");
			totalPoints += points;
		}
	}
	
	private int[][] generateRandomLatinSquare(int sideLength) {
		int[][] square = new int[sideLength][sideLength];
		List<Integer> values = new LinkedList<>();
		Map<Integer, Set<Integer>> withhold = new HashMap<>();
		for(int rowIndex = sideLength - 1; rowIndex >= 0; rowIndex--) {
			populateIndexes(values, sideLength);
			for(int colIndex = sideLength - 1; colIndex >= 0; colIndex--) {
				Integer value = null;
				for(int index = RANDOM.nextInt(values.size()), iterations = 0; value == null; index--, iterations++) {
					if(index < 0)
						index = values.size() - 1;
					if(iterations >= values.size() || !withholdValue(withhold, colIndex, values.get(index)))
						value = values.remove(index);
				}
				square[rowIndex][colIndex] = value;
				if(rowIndex > 0) {
					if(!withhold.containsKey(colIndex))
						withhold.put(colIndex, new HashSet<>());
					withhold.get(colIndex).add(square[rowIndex][colIndex]);
				}
			}
			assert values.isEmpty();
		}
		return square;
	}
	
	private boolean withholdValue(Map<Integer, Set<Integer>> withhold, int column, int value) {
		boolean retVal = false;
		if(withhold.containsKey(column)) {
			Set<Integer> valuesToWithhold = withhold.get(column);
			retVal = valuesToWithhold.contains(value);
		}
		return retVal;
	}
	
	private void populateIndexes(List<Integer> list, int length) {
		for(int index = length - 1; index >= 0; index--)
			list.add((int)RANDOM_LATIN_SQUARE_SEED_VALUES[index]);
	}
	
	private int[][] generateLatinSquare(int sideLength) {
		int[][] square = new int[sideLength][sideLength];
		int startSeedIndex = RANDOM.nextInt(sideLength); // 1 through side length
		final int maxSeedIndex = sideLength - 1;
		for(int rowIndex = sideLength - 1; rowIndex >= 0; rowIndex--, startSeedIndex--) {
			if(startSeedIndex < 0)
				startSeedIndex = maxSeedIndex;
			for(int colIndex = sideLength - 1, seedIndex = startSeedIndex; colIndex >= 0; colIndex--, seedIndex--) {
				if(seedIndex < 0)
					seedIndex = maxSeedIndex;
				square[rowIndex][colIndex] = RANDOM_LATIN_SQUARE_SEED_VALUES[seedIndex];
			}
		}
		return square;
	}
	
	private int[][] generateRowMissingFirstRowElement(int sideLength) {
		int[][] square = new int[sideLength][sideLength];
		int rowWithMissingElement = RANDOM.nextInt(sideLength - 1) + 1; // 1 through side length
		final int maxSeedIndex = sideLength - 1;
		for(int rowIndex = sideLength - 1, startSeedIndex = maxSeedIndex; rowIndex >= 0; rowIndex--, startSeedIndex--) {
			int offset = rowIndex == rowWithMissingElement ? startSeedIndex + sideLength : 0;
			for(int colIndex = sideLength - 1, seedIndex = startSeedIndex; colIndex >= 0; colIndex--, seedIndex--) {
				if(seedIndex < 0)
					seedIndex = maxSeedIndex + offset;
				square[rowIndex][colIndex] = RANDOM_LATIN_SQUARE_SEED_VALUES[seedIndex + offset];
			}
		}
		return square;
	}
	
	private int[][] generateDuplicateFirstRow(int sideLength) {
		int[][] square = new int[sideLength][sideLength];
		final int firstRowMax = sideLength - 1;
		for(int colIndex = sideLength - 1; colIndex >= 0; colIndex--)
			square[0][colIndex] = RANDOM.nextInt(firstRowMax);
		final int maxSeedIndex = firstRowMax;
		for(int rowIndex = sideLength - 1, startSeedIndex = maxSeedIndex; rowIndex > 0; rowIndex--, startSeedIndex--) {
			for(int colIndex = sideLength - 1, seedIndex = startSeedIndex; colIndex >= 0; colIndex--, seedIndex--) {
				if(seedIndex < 0)
					seedIndex = maxSeedIndex;
				square[rowIndex][colIndex] = RANDOM_LATIN_SQUARE_SEED_VALUES[seedIndex];
			}
		}
		return square;
	}
	
	private boolean areLatinExamplesCorrect(String message, int points) {
		boolean allPass = true;
		
		int[][] square = {{1, 2, 3}, {2, 3, 1}, {3, 1, 2}};
		if(!isLatinCorrect(message, points, true, square))
			allPass = false;
		
		square = new int[][]{{10, 30, 20, 0}, {0, 20, 30, 10}, {30, 0, 10, 20}, {20, 10, 0, 30}};
		if(!isLatinCorrect(message, points, true, square))
			allPass = false;
		
		square = new int[][]{{1, 2, 1}, {2, 1, 1}, {1, 1, 2}};
		if(!isLatinCorrect(message, points, false, square))
			allPass = false;
		
		square = new int[][]{{1, 2, 3}, {3, 1, 2}, {7, 8, 9}};
		if(!isLatinCorrect(message, points, false, square))
			allPass = false;
		
		square = new int[][]{{1, 2}, {1, 2}};
		if(!isLatinCorrect(message, points, false, square))
			allPass = false;
		
		if(allPass) {
			logPass(message + " examples");
			totalPoints += points;
		}
		return allPass;
	}
	
	private boolean isLatinCorrect(String message, int points, boolean exp, int[][] square) {
		int[][] squareCopy = CollectionUtil.deepCopy(square);
		boolean act = studentArrayTester.isLatin(square);
		boolean pass = exp == act;
		if(!pass)
			logFail(message + " " + Arrays.deepToString(square), exp, act, points);
		else if(!Arrays.deepEquals(square, squareCopy)) {
			pass = false;
			logFail(message + " - destruction of persistent data.");
		}
		return pass;
	}
	
	public void test03ContainsDuplicates() {
		String message = "containsDuplicates";
		int points = 3;
		boolean allPass = true;
		
		int[] arr = {0};
		if(!duplicatesCorrect(message, points, false, arr))
			allPass = false;
		
		arr = new int[]{0, 1};
		if(!duplicatesCorrect(message, points, false, arr))
			allPass = false;
		
		arr[1] = 0;
		if(!duplicatesCorrect(message, points, true, arr))
			allPass = false;
		
		arr = new int[]{0, 1, 2};
		if(!duplicatesCorrect(message, points, false, arr))
			allPass = false;
		
		arr[0] = 1; // 1, 1, 2
		if(!duplicatesCorrect(message, points, true, arr))
			allPass = false;
		
		arr = new int[]{0, 1, 1};
		if(!duplicatesCorrect(message, points, true, arr))
			allPass = false;
		
		arr = new int[]{2, 1, 3, 0};
		if(!duplicatesCorrect(message, points, false, arr))
			allPass = false;
		
		arr[0] = 3; // 3, 1, 3, 0
		if(!duplicatesCorrect(message, points, true, arr))
			allPass = false;
		
		arr[0] = 2; // 2, 1, 3, 0
		arr[1] = 3; // 2, 3, 3, 0
		if(!duplicatesCorrect(message, points, true, arr))
			allPass = false;
		
		arr[1] = 1; // 2, 1, 3, 0
		arr[3] = 3; // 2, 1, 3, 3
		if(!duplicatesCorrect(message, points, true, arr))
			allPass = false;
		
		final int length = RANDOM.nextInt(20) + 20;
		final int maxValue = length * 20;
		arr = new int[length];
		for(int tests = 1000; tests > 0 && allPass; tests--) {
			for(int index = arr.length - 1; index >= 0; index--)
				arr[index] = RANDOM.nextInt(maxValue);
			boolean exp = solutionArrayTester.containsDuplicates(arr);
			if(!duplicatesCorrect(message, points, exp, arr))
				allPass = false;
		}
		
		if(allPass) {
			logPass(message);
			totalPoints += points;
		} else
			continueTesting = false;
	}
	
	private boolean duplicatesCorrect(String message, int points, boolean exp, int[] arr) {
		int[] arrCopy = Arrays.copyOf(arr, arr.length);
		boolean act = studentArrayTester.containsDuplicates(arr);
		boolean pass = exp == act;
		if(!pass)
			logFail(message + " " + Arrays.toString(arr), exp, act, points);
		else if(!Arrays.equals(arr, arrCopy)) {
			pass = false;
			logFail(message + " - destruction of persistent data.");
		}
		return pass;
	}
	
	public void test02HasAllValues() {
		String message = "hasAllValues";
		int points = 3;
		boolean allPass = true;
		
		int[] arr1 = {}, arr2 = {};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		
		arr1 = new int[] {0};
		arr2 = new int[] {0};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		
		arr1 = new int[] {0, 0};
		arr2 = new int[] {0, 0};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		
		arr1 = new int[] {0, 1};
		arr2 = new int[] {1, 0};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		
		arr1 = new int[] {0, 1, 1};
		arr2 = new int[] {1, 0, 1};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		
		arr1 = new int[] {0, 1, 1};
		arr2 = new int[] {0, 0, 1};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		
		arr1 = new int[] {0, 1, 1, 2};
		arr2 = new int[] {0, 0, 2, 2};
		if(!hasAllValuesCorrect(message, points, false, arr1, arr2))
			allPass = false;
		
		arr2 = new int[] {0, 1, 2, 2};
		if(!hasAllValuesCorrect(message, points, true, arr1, arr2))
			allPass = false;
		
		if(allPass) {
			logPass(message);
			totalPoints += points;
		} else
			continueTesting = false;
	}
	
	private boolean hasAllValuesCorrect(String message, int points, boolean exp, int[] arr1, int[] arr2) {
		int[] arr1Copy = Arrays.copyOf(arr1, arr1.length), arr2Copy = Arrays.copyOf(arr2, arr2.length);
		inputToStudentCode = "Arg1: " + Arrays.toString(arr1) + "; Arg2: " + Arrays.toString(arr2);
		boolean act = studentArrayTester.hasAllValues(arr1, arr2);
		boolean pass = exp == act;
		if(!pass)
			logFail(message + " " + Arrays.toString(arr1) + " " + Arrays.toString(arr2), exp, act, points);
		else if(!Arrays.equals(arr1, arr1Copy) || !Arrays.equals(arr2, arr2Copy)) {
			pass = false;
			logFail(message + " - destruction of persistent data.");
		}
		inputToStudentCode = null;
		return pass;
	}
	
	public void test01GetColumn() {
		String message = "getColumn";
		int points = 2;
		boolean allPass = true;
		int[][] arr2D = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 5, 3}};
		int[][] origCopyArr2D = CollectionUtil.deepCopy(arr2D);
		int[][] exp = {{0, 3, 6, 9}, {1, 4, 7, 5}, {2, 5, 8, 3}};
		for(int colIndex = arr2D[0].length - 1; colIndex >= 0; colIndex--) {
			if(!columnCorrect(arr2D, colIndex, exp[colIndex], message, points))
				allPass = false;
			if(!Arrays.deepEquals(arr2D, origCopyArr2D)) {
				allPass = false;
				logFail(message + " - destruction of persistent data");
			}
		}
		if(allPass) {
			logPass(message);
			totalPoints += points;
		} else
			continueTesting = false;
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
		return 28;
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
	
	private void printArray(int[][] array) {
		for(int index = 0; index < array.length; index++)
			out.println(Arrays.toString(array[index]));
	}

}
