/**
 * 
 */
package edu.jenks.util.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;

import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;
import edu.jenks.dist.util.Map;

/**
 * @author Jenks
 *
 */
public class HashtableTest extends Testable {
	
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {int.class};
	private static final Object[] DEFAULT_CONSTRUCTOR_VALUES = new Object[1];
	private static final int INITIAL_CAPACITY = 5;
	private static final java.util.Map<String, Integer> INIT_MAP_1_7 = new java.util.HashMap<>(14);
	private static final java.util.Map<String, Integer> INIT_MAP_9_16 = new java.util.HashMap<>(14);
	
	static {
		INIT_MAP_1_7.put("One", 1);
		INIT_MAP_1_7.put("Two", 2);
		INIT_MAP_1_7.put("Three", 3);
		INIT_MAP_1_7.put("Four", 4);
		INIT_MAP_1_7.put("Five", 5);
		INIT_MAP_1_7.put("Six", 6);
		INIT_MAP_1_7.put("Seven", 7);
		
		INIT_MAP_9_16.put("Nine", 9);
		INIT_MAP_9_16.put("Ten", 10);
		INIT_MAP_9_16.put("Eleven", 11);
		INIT_MAP_9_16.put("Twelve", 12);
		INIT_MAP_9_16.put("Thirteen", 13);
		INIT_MAP_9_16.put("Fourteen", 14);
		INIT_MAP_9_16.put("Fifteen", 15);
		INIT_MAP_9_16.put("Sixteen", 16);
	}
	
	private final Map<String, Integer> SOLUTION_MAP = new edu.util.jenks.ted.Hashtable<>(INITIAL_CAPACITY);
	private Map<String, Integer> studentMap;
	private String studentClassName;
	
	public HashtableTest() {
		DEFAULT_CONSTRUCTOR_VALUES[0] = INITIAL_CAPACITY;
	}
	
	// points: 3
	public void test37GetValues() {
		String message = "testGetValues";
		int points = 3;
		Integer[] actValues = studentMap.getValues();
		Integer[] expValues = SOLUTION_MAP.getValues();
		arraysEqual(message, points, actValues, expValues);
	}
	
	// points: 3
	public void test36GetKeys() {
		String message = "testGetKeys";
		int points = 3;
		String[] actKeys = studentMap.getKeys();
		String[] expKeys = SOLUTION_MAP.getKeys();
		arraysEqual(message, points, actKeys, expKeys);
	}
	
	private void arraysEqual(String message, int points, Object[] actKeys, Object[] expKeys) {
		boolean pass = false;
		if(actKeys != null) {
			Arrays.sort(actKeys);
			Arrays.sort(expKeys);
			pass = Arrays.equals(actKeys, expKeys);
		}
		if(pass) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message, Arrays.toString(expKeys), Arrays.toString(actKeys), points);
	}
	
	// points: 3
	public void test40ClearAndIsEmpty() {
		String message = "testClearAndIsEmpty";
		int points = 1;
		String subMessage = message + " not empty";
		if(!studentMap.isEmpty()) {
			logPass(subMessage);
			totalPoints += points;
		} else {
			logFail(subMessage, false, true, points);
			continueTesting = false;
		}
		
		subMessage = message + " clear";
		SOLUTION_MAP.clear();
		studentMap.clear();
		if(mapsEqual()) {
			logPass(subMessage);
			totalPoints += points;
		} else {
			logFail(subMessage, SOLUTION_MAP, studentMap, points);
			continueTesting = false;
		}
		
		subMessage = message + " empty";
		if(studentMap.isEmpty()) {
			logPass(subMessage);
			totalPoints += points;
		} else {
			logFail(subMessage, true, false, points);
			continueTesting = false;
		}
	}
	
	// points: 3
	public void test01GetArrayIndexFromKey() {
		String message = "testGetArrayIndexFromKey";
		int points = 3;
		if(compareHash(message, points, INIT_MAP_1_7.keySet().iterator()) && compareHash(message, points, INIT_MAP_9_16.keySet().iterator())) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message);
	}
	
	private boolean compareHash(String message, int points, Iterator<String> keys) {
		boolean pass = true;
		while(pass && keys.hasNext()) {
			String key = keys.next();
			int exp = SOLUTION_MAP.getArrayIndexFromKey(key);
			int act = studentMap.getArrayIndexFromKey(key);
			pass = exp == act;
			if(!pass)
				logFail(message, exp, act, points);
		}
		return pass;
	}
	
	// points: 10
	public void test35Get() {
		String message = "testGet";
		int points = 10;
		Integer i14 = 14;
		Integer i15 = 15;
		Integer i16 = 16;
		if(i14.equals(studentMap.get("Fourteen")) && i15.equals(studentMap.get("Fifteen")) && i16.equals(studentMap.get("Sixteen")) &&
				null == studentMap.get("Three") && null == studentMap.get("Eighty")) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message);
	}
	
	// points: 3
	public void test30ContainsValue() {
		String message = "testContainsValue";
		int points = 3;
		if(studentMap.containsValue(5) && !studentMap.containsValue(3)) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message);
	}
	
	// points: 3
	public void test25ContainsKey() {
		String message = "testContainsKey";
		int points = 3;
		if(studentMap.containsKey("Five") && !studentMap.containsKey("Three")) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message);
	}
	
	// points: 8
	public void test20Remove() {
		String message = "testRemove";
		int points = 8;
		boolean pass = true;
		pass = new Integer(3).equals(removeFromMaps("Three"));
		if(pass) {
			pass = null == removeFromMaps("Not a number") && null == removeFromMaps("Three");
			pass = mapsEqual();
		}
		if(pass) {
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_MAP, studentMap, points);
		}
	}
	
	// points: 12
	public void test15Put() {
		String message = "testPut";
		int points = 4;
		boolean pass = false;
		putInitMap(INIT_MAP_1_7);
		if(mapsEqual()) {
			totalPoints += points;
			putInMaps("One", 11);
			putInMaps("Eight", 8);
			if(mapsEqual()) {
				totalPoints += points;
				putInitMap(INIT_MAP_9_16);
				putInMaps("Two", 22);
				if(mapsEqual()) {
					totalPoints += points;
					pass = true;
				}
			}
		}
		if(pass)
			logPass(message);
		else {
			logFail(message, SOLUTION_MAP, studentMap, points);
			continueTesting = false;
		}
	}
	
	// points: 1
	public void test10Size() {
		String message = "testSize";
		int points = 1;
		int expVal = 0, actVal = studentMap.size();
		if(expVal == actVal) {
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message, expVal, actVal, points);
		}
	}
	
	// points: 1
	public void test05GetCapacity() {
		String message = "testGetCapacity";
		int points = 1;
		if(INITIAL_CAPACITY == studentMap.getCapacity()) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message, INITIAL_CAPACITY, studentMap.getCapacity(), points);
			continueTesting = false;
		}
	}

	@Override
	public int getPointsAvailable() {
		return 100;
	}
	
	private void putInitMap(java.util.Map<String, Integer> initMap) {
		Iterator<String> keys = initMap.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			putInMaps(key, initMap.get(key));
		}
	}
	
	private Integer removeFromMaps(Object key) {
		SOLUTION_MAP.remove(key);
		return studentMap.remove(key);
	}
	
	private void putInMaps(String key, Integer value) {
		SOLUTION_MAP.put(key, value);
		studentMap.put(key, value);
	}
	
	private boolean mapsEqual() {
		int solutionSize = SOLUTION_MAP.size();
		boolean equal = SOLUTION_MAP.getCapacity() == studentMap.getCapacity() && solutionSize == studentMap.size();
		if(equal && solutionSize > 0) { // verify all elements present
			String[] expKeys = SOLUTION_MAP.getKeys();
			for(int index = expKeys.length - 1; index >= 0 && equal; index--) {
				String expKey = expKeys[index];
				Integer expVal = SOLUTION_MAP.get(expKey);
				Integer actVal = studentMap.get(expKey);
				equal = expVal.equals(actVal);
			}
		}
		return equal;
	}

	@Override
	public java.util.Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".Hashtable";
		java.util.Map<String, String> map = new java.util.HashMap<String, String>();
		map.put(studentClassName, "java.lang.Object");
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentMap = (Map<String, Integer>)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, DEFAULT_CONSTRUCTOR_VALUES);
		totalPoints += 50;
	}

}
