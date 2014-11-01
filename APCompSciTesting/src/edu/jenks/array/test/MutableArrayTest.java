/**
 * 
 */
package edu.jenks.array.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.logging.Level;

import edu.jenks.array.dist.List;
import edu.jenks.test.TestPackageList;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author JenksT
 *
 */
public class MutableArrayTest extends Testable {
	
	private static final MutableArrayTest SINGLETON = new MutableArrayTest();
	private final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {int.class};
	
	/*@Override
	public String[] getTestMethods() {
		String[] testMethods = {"testConstructor", "testToArray", "testAdd",
				"testAddAtIndex", "testClear", "testRemove", "testSet"};
		return testMethods;
	}*/
	
	public void testToArray() {
		List list = createAndLoadList(5);
		Object[] arrayCopy = list.toArray();
		Object[] expected1 = {0, 1, 2, 3, 4};
		boolean pass = Arrays.equals(expected1, arrayCopy);
		if(pass) { // verify array is a copy
			arrayCopy[0] = -1;
			pass = Arrays.equals(expected1, list.toArray());
			if(pass) {
				list.clear();
				pass = (list.toArray().length == 0);
			} else
				logger.log(Level.INFO, "Fail - toArray() should return a copy");
		}
		if(pass) {
			totalPoints++;
			logger.log(Level.FINE, "Pass (1)");
		} else {
			logger.log(Level.INFO, "Fail");
		}
	}
	
	public void testSet() {
		boolean pass = true;
		List list = createAndLoadList(5);
		try {
			list.set(-1, -1);
			pass = false;
			logger.log(Level.INFO, "Fail - index check");
		} catch(IndexOutOfBoundsException e1) {
			try {
				list.set(list.size(), 0);
				pass = false;
				logger.log(Level.INFO, "Fail - index check");
			} catch(IndexOutOfBoundsException e2) {
				final int frontValue = -1, innerValue = 22, backValue = 44;
				int frontReplaced = (int)list.set(0, frontValue);
				int innerReplaced = (int)list.set(2, innerValue);
				int backReplaced = (int)list.set(list.size() - 1, backValue);
				int[] expected = {frontValue, 1, innerValue, 3, backValue};
				pass = ((0 == frontReplaced && 2 == innerReplaced && 4 == backReplaced)
						&& hasSameElements(expected, list));
			}
		}
		if(pass) {
			totalPoints++;
			logger.log(Level.FINE, "Pass (1)");
		} else
			logger.log(Level.INFO, "Fail");
	}
	
	public void testRemove() {
		final int frontIndex = 0, innerIndex = 5, backIndex = 9;
		List list = createAndLoadList(backIndex + 1);
		int backValue = (int)list.remove(backIndex);
		int innerValue = (int)list.remove(innerIndex);
		int frontValue = (int)list.remove(frontIndex);
		boolean pass = (backValue == backIndex && innerValue == innerIndex && frontValue == frontIndex);
		if(pass) {
			int[] expected = {1, 2, 3, 4, 6, 7, 8};
			pass = hasSameElements(expected, list);
			if(pass) {
				totalPoints++;
				logger.log(Level.FINE, "Pass (1)");
			} else
				logger.log(Level.INFO, "Fail - wrong elements remaining");
		} else
			logger.log(Level.INFO, "Fail - return values incorrect");
	}
	
	public void testClear() {
		final int initSize = 5;
		List list = createAndLoadList(initSize);
		boolean pass = (list.size() == initSize && !list.isEmpty());
		if(pass) {
			list.clear();
			pass = (list.size() == 0 && list.isEmpty());
			if(pass) {
				try {
					list.get(0);
					pass = false;
					logger.log(Level.INFO, "Fail - list is empty, should not be able to get element");
				} catch(IndexOutOfBoundsException e) {
					totalPoints += 3;
					logger.log(Level.FINE, "Pass (3) - clear, size, and empty tests");
				}
			} else
				logger.log(Level.INFO, "Fail - size or empty test (expected - empty)");
		} else
			logger.log(Level.INFO, "Fail - size or empty test (expected - not empty)");
	}
	
	public void testAddAtIndex() {
		final int front = -1, back = 5, inner = 11;
		int[] expected = {front, 0, 1, inner, 2, 3, 4, back};
		List list = createAndLoadList(back);
		list.add(back, back); // add back
		list.add(2, 11);
		list.add(0, front);
		boolean pass = hasSameElements(expected, list);
		if(pass) {
			totalPoints++;
			logger.log(Level.FINE, "Pass (1)");
		} else
			logger.log(Level.INFO, "Fail");
	}
	
	public void testAdd() {
		final int initialSize = 5;
		List list = createAndLoadList(initialSize);
		final int desiredSize = (int)Math.pow(10, 6);
		boolean performanceAcceptable = true;
		final int maxSeconds = 10;
		long startTime = System.currentTimeMillis();
		for(int index = initialSize; index < desiredSize && performanceAcceptable; index++) {
			list.add(index);
			if(index % 1000 == 0) { // test elapsed time
				long elapsedTimeSeconds = (System.currentTimeMillis() - startTime) / 1000;
				if(elapsedTimeSeconds > maxSeconds) {
					performanceAcceptable = false;
					logger.log(Level.INFO, "Fail due to performance.  Operation took longer than " + maxSeconds + " seconds to process " + (index - initialSize) + " adds");
				}
			}
		}
		if(performanceAcceptable) {
			logger.log(Level.FINE, "Pass - add large number of elements.");
			int[] expected = new int[desiredSize];
			for(int index = desiredSize - 1; index >= 0; index--)
				expected[index] = index;
			boolean pass = hasSameElements(expected, list);
			if(pass) {
				totalPoints++;
				logger.log(Level.FINE, "Pass (1)");
			} else
				logger.log(Level.INFO, "Fail");
		}
	}
	
	public void test1Constructor() {
		final int size = 5;
		List list = createAndLoadList(size);
		int[] expected = {0, 1, 2, 3, 4};
		boolean pass = hasSameElements(expected, list);
		if(pass)
			totalPoints += 2;
		else
			continueTesting = false;
		logger.log(pass ? Level.FINE : Level.INFO, (pass ? "Pass (2)" : "Fail") + " - constructor and get");
	}
	
	@Override
	public void setUp() {
		super.setUp();
		verifySuperclass(Object.class.getName(), createList(0));
	}
	
	private List createList(int size) {
		Object[] args = {size};
		List list = null;
		try {
			list = (List)ReflectionUtil.newInstance(studentPackage + ".MutableArray", CONSTRUCTOR_PARAM_TYPES, args);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.log(Level.WARNING, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
		return list;
	}
	
	private List createAndLoadList(int size) {
		List list = createList(size);
		for(int index = 0; index < size; index++)
			list.add(index);
		return list;
	}
	
	private boolean hasSameElements(int[] expected, List actual) {
		boolean same = true;
		if(expected.length != actual.size())
			same = false;
		for(int index = expected.length - 1; same && index >= 0; index--) {
			if(expected[index] != (int)actual.get(index))
				same = false;
		}
		return same;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Begin main");
		TestPackageList.testPackages(SINGLETON, SINGLETON.getLogFilePath());
		System.out.println("End main");

	}

	@Override
	public String getLogFilePath() {
		return LOG_FILE_PATH_START + "array/test/log.txt";
	}

	@Override
	public Testable getSingleton() {
		return SINGLETON;
	}
}
