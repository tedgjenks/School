package edu.jenks.array.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.array.List;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class MutableArrayTest extends Testable {
	
	private String studentClassName;

	public MutableArrayTest() {}
	
	public void test02ToArray() {
		int points = 2;
		String message = "toArray";
		int expLength = 5;
		Integer[] exp = new Integer[expLength];
		List sList = studentInstance(expLength);
		int e0 = 0;
		sList.add(e0);
		exp[0] = e0;
		Object[] act = sList.toArray();
		boolean pass = false;
		if(Arrays.equals(exp, act)) {
			act[1] = 10;
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
		} else
			logFail(message + " - testing aborted.");
	}
	
	public void test01SingleAdd() {
		int points = 1;
		String message = "single add";
		String exp = "e0";
		List sList = studentInstance(1);
		sList.add(exp);
		String act = sList.get(0).toString();
		if(exp.equals(act)) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message + " - testing aborted", exp, act, points);
			continueTesting = false;
		}
	}

	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<>();
		studentClassName = studentPackage + ".MutableArray";
		map.put(studentClassName, "edu.jenks.dist.array.List");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List studentInstance = studentInstance(5);
		if(studentInstance != null)
			totalPoints += 50;
		else {
			continueTesting = false;
			logFail("object creation");
		}
	}
	
	private List studentInstance(int capacity) {
		List instance = null;
		try {
			instance = (List)ReflectionUtil.newInstance(studentClassName);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logException("studentInstance", e);
		}
		return instance;
	}

}
