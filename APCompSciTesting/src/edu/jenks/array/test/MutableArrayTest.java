package edu.jenks.array.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.array.List;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class MutableArrayTest extends Testable {
	
	private static final Class<?>[] CONSTRUCTOR_TYPE = {int.class};
	
	private String studentClassName;
	private Random random = new Random(System.currentTimeMillis());

	public MutableArrayTest() {}
	
	public void testClear() {
		int points = 2;
		String message = "clear";
		int size = 10;
		List actual = studentInstance(size);
		for(int index = size - 1; index >= 0; index--)
			actual.add(index);
		actual.clear();
		if(actual.isEmpty() && actual.size() == 0) {
			logPass(message);
			totalPoints += points;
		} else
			logFail(message);
	}
	
	public void testIsEmpty() {
		int points = 2;
		String message = "is empty";
		int size = 10;
		List actual = studentInstance(size);
		boolean pass = actual.isEmpty();
		if(pass) {
			for(int index = size - 1; pass && index > 0; index--) {
				actual.add(index);
				pass = !actual.isEmpty();
			}
			for(int index = actual.size() - 1; pass && index > 0; index--) {
				actual.remove(index);
				pass = !actual.isEmpty();
			}
			actual.remove(0);
			pass = actual.isEmpty();
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		} else
			logFail(message);
	}
	
	// add, set, get, remove
	public void test08IndexOutOfBounds() {
		int points = 5;
		String message = "index out of bounds";
		boolean pass = true;
		int size = 10;
		List actual = studentInstance(size);
		for(int index = 5; index > 0; index--)
			actual.add(index);
		
		//add
		try {
			actual.add(-1, -1);
			message += " - add -";
			pass = false;
		} catch(IndexOutOfBoundsException e) {}
		try {
			actual.add(actual.size() + 1, -1);
			pass = false;
		} catch(IndexOutOfBoundsException e) {}
		
		//set
		try {
			actual.set(-1, -1);
			message += " - set -";
			pass = false;
		} catch(IndexOutOfBoundsException e) {}
		try {
			actual.set(actual.size(), -1);
			pass = false;
		} catch(IndexOutOfBoundsException e) {}
		
		//get
		try {
			actual.get(-1);
			message += " - get -";
			pass = false;
		} catch(IndexOutOfBoundsException e) {}
		try {
			actual.get(actual.size());
			pass = false;
		} catch(IndexOutOfBoundsException e) {}
		
		//remove
		try {
			actual.remove(-1);
			message += " - remove -";
			pass = false;
		} catch(IndexOutOfBoundsException e) {}
		try {
			actual.remove(actual.size());
			pass = false;
		} catch(IndexOutOfBoundsException e) {}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		} else
			logFail(message);
	}
	
	public void test07Set() {
		int points = 6;
		String message = "set";
		int size = 10;
		ArrayList<Object> expected = new ArrayList<>(size);
		List actual = studentInstance(size);
		populateListsWithRandomInts(expected, actual, size);
		int indexToSet = size - 1;
		int value = random.nextInt(1000);
		expected.set(indexToSet, value);
		actual.set(indexToSet, value);
		indexToSet = 4;
		value = random.nextInt(1000);
		expected.set(indexToSet, value);
		actual.set(indexToSet, value);
		indexToSet = 0;
		value = random.nextInt(1000);
		expected.set(indexToSet, value);
		actual.set(indexToSet, value);
		continueTesting = testListEquality(points, message, expected, actual, size);
	}
	
	public void test06Remove() {
		int points = 6;
		String message = "remove";
		int size = 10;
		ArrayList<Object> expected = new ArrayList<>(size);
		List actual = studentInstance(size);
		populateListsWithRandomInts(expected, actual, size);
		int indexToRemove = size - 1; 
		expected.remove(indexToRemove);
		actual.remove(indexToRemove);
		indexToRemove = 4;
		expected.remove(indexToRemove);
		actual.remove(indexToRemove);
		indexToRemove = 0;
		expected.remove(indexToRemove);
		actual.remove(indexToRemove);
		continueTesting = testListEquality(points, message, expected, actual, size);
	}
	
	public void test05Get() {
		int points = 6;
		String message = "get";
		int size = 10;
		ArrayList<Object> expected = new ArrayList<>(size);
		List actual = studentInstance(size);
		populateListsWithRandomInts(expected, actual, size);
		boolean pass = true;
		for(int index = size - 1; index >0 && pass; index--) {
			if(!expected.get(index).equals(actual.get(index)))
				pass = false;
		}
		if(pass) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message + " testing aborted", Arrays.toString(expected.toArray()), Arrays.toString(actual.toArray()), points);
			continueTesting = false;
		}
	}
	
	public void test04AddIndex() {
		int points = 6;
		String message = "add index";
		int size = 5;
		ArrayList<Object> expected = new ArrayList<>(size);
		List actual = studentInstance(size);
		for(int index = size - 1; index >= 0; index--) { // insert front
			int element = random.nextInt();
			expected.add(0, element);
			actual.add(0, element);
		}
		String elementLast = "elementLast";
		expected.add(size - 1, elementLast);
		actual.add(size - 1, elementLast);
		String elementInside = "elementInside";
		expected.add(2, elementInside);
		actual.add(2, elementInside);
		continueTesting = testListEquality(points, message, expected, actual, 10);
	}
	
	public void test03AddTail() {
		int points = 3;
		String message = "add tail";
		int size = 10;
		ArrayList<Object> expected = new ArrayList<>(size);
		List actual = studentInstance(size);
		populateListsWithRandomInts(expected, actual, size);
		if(continueTesting = testListEquality(points, message, expected, actual, size)) {
			int nextElement = 100;
			expected.add(nextElement);
			actual.add(nextElement);
			continueTesting = testListEquality(points, message, expected, actual, size * 2);
		}
	}
	
	private void populateListsWithRandomInts(ArrayList<Object> expected, List actual, int size) {
		for(int index = size; index > 0; index--) {
			Integer element = new Integer(random.nextInt(1000));
			expected.add(element);
			actual.add(element);
		}
	}
	
	private boolean testListEquality(int points, String message, java.util.List<?> expectedList, List actualList, int expBackingLength) {
		Object[] expected = expectedList.toArray();
		Object[] actual = actualList.toArray();
		return testArrayEquality(points, message, expected, actual, expBackingLength, actualList.getBackingArray().length);
	}
	
	private boolean testArrayEquality(int points, String message, Object[] expected, Object[] actual, int expBackingLength, int actBackingLength) {
		boolean pass = false;
		if(expBackingLength == actBackingLength && Arrays.equals(expected, actual)) {
			logPass(message);
			totalPoints += points;
			pass = true;
		} else
			logFail(message, Arrays.toString(expected) + " with capactiy " + expBackingLength, Arrays.toString(actual) + " with capacity " + actBackingLength, points);
		return pass;
	}
	
	public void test02ToArray() {
		int points = 6;
		String message = "toArray";
		int expLength = 1;
		Integer[] exp = new Integer[expLength];
		List sList = studentInstance(5);
		int e0 = 0;
		sList.add(e0);
		exp[0] = e0;
		Object[] act = sList.toArray();
		boolean pass = false;
		if(Arrays.equals(exp, act)) {
			act[0] = 10;
			if(Arrays.equals(exp, sList.toArray())) { // verify copy
				logPass(message);
				pass = true;
			} else
				message += " array not a copy";
		} else
			message += " arrays not equal";
		if(pass) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message + " - testing aborted.", Arrays.toString(exp), Arrays.toString(act), points);
			continueTesting = false;
		}
	}
	
	public void test01SingleAdd() {
		int points = 3;
		String message = "single add";
		String element = "e0";
		int capacity = 1;
		List sList = studentInstance(capacity);
		sList.add(element);
		Object[] act = sList.getBackingArray();
		Object[] exp = {element};
		if(!testArrayEquality(points, message + " (size and capacity same)", exp, act, exp.length, act.length))
			continueTesting = false;
		
		capacity = random.nextInt(100) + 5;
		sList = studentInstance(capacity);
		sList.add(element);
		act = sList.getBackingArray();
		exp = new Object[capacity];
		exp[0] = element;
		if(!testArrayEquality(points, message + " (size and capacity different)", exp, act, exp.length, act.length))
			continueTesting = false;
	}

	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<>();
		studentClassName = studentPackage + ".MutableArray";
		map.put(studentClassName, "java.lang.Object");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List studentInstance = studentInstance(5);
		if(studentInstance != null)
			totalPoints += 49;
		else {
			continueTesting = false;
			logFail("object creation");
		}
	}
	
	private List studentInstance(int capacity) {
		List instance = null;
		try {
			Object[] value = {capacity};
			instance = (List)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_TYPE, value);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			logException("studentInstance", e);
		}
		return instance;
	}

}
