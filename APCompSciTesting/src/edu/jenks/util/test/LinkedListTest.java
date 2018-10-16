/**
 * 
 */
package edu.jenks.util.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import edu.jenks.dist.util.AbstractLinkedList;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Jenks
 *
 */
public class LinkedListTest extends Testable {
	private static final String[] TEST_ELEMENTS = {"E10", "E20", "E30", "E40", "E50"};
	
	private final AbstractLinkedList<String> SOLUTION_LIST = new edu.util.jenks.ted.LinkedList<>();
	private AbstractLinkedList<String> studentList;
	private String studentClassName;
	
	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 100;
	}
	
	// 1 point
	public void testAddTail02() {
		int points = 1;
		String message = "testAddTail";
		addToLists(TEST_ELEMENTS);
		if(listsEqual()) {
			logPass(message);
			totalPoints += points;
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST.toString(), studentList.toString(), points);
		}
	}
	
	// 5 points
	// pre: E10, E20, E30, E40, E50
	public void testAddIndex03() {
		int points = 5;
		String message = "testAddIndex";
		addToLists(0, "E05");
		addToLists(2, "E15");
		addToLists(7, "E60");
		if(listsEqual()) {
			logPass(message);
			totalPoints += (points - 1);
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST.toString(), studentList.toString(), points);
		}
		boolean passExceptionTest = false;
		try {
			studentList.add(-1, "");
		} catch(IndexOutOfBoundsException e) {
			try {
				studentList.add(9, "");
			} catch(IndexOutOfBoundsException e2) {
				passExceptionTest = true;
			}
		}
		message += " IndexOutOfBoundsException";
		if(passExceptionTest) {
			logPass(message);
			totalPoints++;
		} else
			logFail(message);
	}
	
	// 1 point
	// pre: E05, E10, E15, E20, E30, E40, E50, E60
	public void testAddFirst04() {
		int points = 1;
		String message = "testAddFirst", element = "E01";
		SOLUTION_LIST.addFirst(element);
		studentList.addFirst(element);
		if(listsEqual()) {
			logPass(message);
			totalPoints += points;
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST.toString(), studentList.toString(), points);
		}
	}
	
	// 1 point
	// pre: E00, EMid, E40, E10, E20, ELast
	public void testAddLast18() {
		int points = 1;
		String message = "testAddLast", element = "E70";
		SOLUTION_LIST.addLast(element);
		studentList.addLast(element);
		if(listsEqual()) {
			logPass(message);
			totalPoints += points;
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST.toString(), studentList.toString(), points);
		}
	}
	
	// 2 points
	public void testClear12() {
		int points = 2;
		String message = "testClear";
		SOLUTION_LIST.clear();
		studentList.clear();
		if(listsEqual()) {
			logPass(message);
			totalPoints += (points - 1);
		} else {
			continueTesting = false;
			logFail(message, 0, studentList.size(), points);
		}
		boolean passException = false;
		try {
			studentList.getFirst();
		} catch(NoSuchElementException e) {
			try {
				studentList.getLast();
			} catch(NoSuchElementException e2) {
				try {
					studentList.removeLast();
				} catch(NoSuchElementException e3) {
					try {
						studentList.remove();
					} catch(NoSuchElementException e4) {
						passException = true;
					}
				}
			}
		}
		message += " NoSuchElementException";
		if(passException) {
			logPass(message);
			totalPoints++;
		} else
			logFail(message);
	}
	
	// 5 points
	// pre: E01, E05, E10, E15, E20, E30, E40, E50, E60
	public void testContains06() {
		int points = 5;
		String message = "testContains";
		boolean pass = studentList.contains("E01") && studentList.contains("E60") && studentList.contains("E15") && !studentList.contains("E70") && !studentList.contains(null);
		if(pass) {
			addToLists(3, null);
			pass = studentList.contains(null);	
		} 
		if(pass) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message);
			continueTesting = false;
		}
	}
	
	// 5 points
	// pre: E01, E05, E10, null, E15, E20, E30, E40, E50, E60
	public void testGet07() {
		int points = 5;
		String message = "testGet";
		if("E01".equals(studentList.get(0)) && "E60".equals(studentList.get(9)) && "E10".equals(studentList.get(2)) && null == studentList.get(3)) {
			totalPoints += (points - 1);
			logPass(message);
		} else
			logFail(message);
		boolean passExceptionTest = false;
		try {
			studentList.get(-1);
		} catch(IndexOutOfBoundsException e) {
			try {
				studentList.get(SOLUTION_LIST.size());
			} catch(IndexOutOfBoundsException e2) {
				passExceptionTest = true;
			}
		}
		message += " IndexOutOfBoundsException";
		if(passExceptionTest) {
			logPass(message);
			totalPoints++;
		} else
			logFail(message);
	}

	// 1 point
	// pre: E01, E05, E10, null, E15, E20, E30, E40, E50, E60
	public void testGetFirst08() {
		int points = 1;
		String message = "testGetFirst";
		if("E01".equals(studentList.getFirst())) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message);
	}
	
	// 1 point
	// pre: E01, E05, E10, null, E15, E20, E30, E40, E50, E60
	public void testGetLast09() {
		int points = 1;
		String message = "testGetLast";
		if("E60".equals(studentList.getLast())) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message);
	}
	
	// 5 points
	// pre: E01, E05, E10, null, E15, E20, E30, E40, E50, E60
	public void testIndexOf10() {
		int points = 5;
		String message = "testIndexOf";
		boolean pass = 9 == studentList.indexOf("E60") && 0 == studentList.indexOf("E01") && 3 == studentList.indexOf(null) && -1 == studentList.indexOf("E70");
		if(pass) {
			addToLists(2, "E60");
			pass = 2 == studentList.indexOf("E60");
		}
		if(pass) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message);
			continueTesting = false;
		}
	}
	
	// 5 points
	// pre: E01, E05, E60, E10, null, E15, E20, E30, E40, E50, E60
	public void testLastIndexOf11() {
		int points = 5;
		String message = "testLastIndexOf";
		if(studentList.lastIndexOf("E60") == 10 && studentList.lastIndexOf("E70") == -1) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message);
	}
	
	// 1 point
	// pre: empty
	public void testRemoveHead13() {
		int points = 1;
		String message = "testRemoveHead";
		addToLists(TEST_ELEMENTS);
		SOLUTION_LIST.remove();
		studentList.remove();
		if(listsEqual()) {
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST, studentList, points);
		}
	}
	
	// 5 points
	// pre: E20, E30, E40, E50
	public void testRemoveIndex14() {
		int points = 5;
		String message = "testRemoveIndex";
		addToLists(TEST_ELEMENTS);
		// now E20, E30, E40, E50, E10, E20, E30, E40, E50
		boolean pass = removeFromLists(0);
		if(pass)
			pass = removeFromLists(SOLUTION_LIST.size() - 1);
		if(pass)
			pass = removeFromLists(3);
		if(pass)
			pass = listsEqual();
		if(pass) {
			totalPoints += (points - 1);
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST, studentList, points);
		}
		boolean passException = false;
		message += " IndexOutOfBoundsException";
		try {
			studentList.remove(-1);
		} catch(IndexOutOfBoundsException e) {
			try {
				studentList.remove(SOLUTION_LIST.size());
			} catch(IndexOutOfBoundsException e2) {
				passException = true;
			}
		}
		if(passException) {
			logPass(message);
			totalPoints++;
		} else
			logFail(message);
	}
	
	// 2 points
	// pre: E30, E40, E50, E20, E30, E40
	public void testRemoveFirstByValue15() {
		int points = 2;
		String message = "testRemoveFirstByValue";
		addToLists(TEST_ELEMENTS);
		// now E30, E40, E50, E20, E30, E40, E10, E20, E30, E40, E50
		boolean pass = removeFromLists("E40") && removeFromLists("E30") && removeFromLists("E50") && removeFromLists("E50");
		if(pass)
			pass = removeFromLists("E70");
		if(pass)
			pass = removeFromLists(null);
		addToLists(3, null);
		if(pass)
			pass = removeFromLists(null);
		if(pass)
			pass = listsEqual();
		if(pass) {
			logPass(message);
			totalPoints += points;
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST, studentList, points);
		}
	}
	
	// 1 point
	// pre: E20, E30, E40, E10, E20, E30, E40
	public void testRemoveLast16() {
		int points = 1;
		String message = "testRemoveLast";
		boolean pass = SOLUTION_LIST.removeLast().equals(studentList.removeLast());
		if(pass)
			pass = listsEqual();
		if(pass) {
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST, studentList, points);
		}
	}
	
	// 1 point
	// pre: E00, EMid, E40, E10, E20, ELast, E70
	public void testRemoveSpecial19() {
		String message = "testRemoveSpecial";
		int points = 1;
		SOLUTION_LIST.clear();
		studentList.clear();
		boolean pass = removeFromLists("E100");
		if(pass) {
			addToLists(0, "E10");
			pass = removeFromLists("E100");
		}
		if(pass)
			pass = removeFromLists("E10");
		if(pass) {
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST, studentList, points);
		}
	}
	
	// 4 points
	// pre: E20, E30, E40, E10, E20, E30
	public void testSet17() {
		int points = 4;
		String message = "testSet";
		String value = "E00";
		// now E00, E30, E40, E10, E20, E30
		boolean pass = setLists(0, value);
		value = "ELast";
		if(pass)
			pass = setLists(SOLUTION_LIST.size() - 1, value);
		// now E00, E30, E40, E10, E20, ELast
		if(pass)
			pass = setLists(1, "EMid");
		// now E00, EMid, E40, E10, E20, ELast
		if(pass)
			pass = listsEqual();
		if(pass) {
			totalPoints += (points - 1);
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST, studentList, points);
		}
		boolean passException = false;
		message += " IndexOutOfBoundsException";
		try {
			studentList.set(-1, value);
		} catch(IndexOutOfBoundsException e) {
			try {
				studentList.set(SOLUTION_LIST.size(), value);
			} catch(IndexOutOfBoundsException e2) {
				passException = true;
			}
		}
		if(passException) {
			logPass(message);
			totalPoints++;
		} else {
			continueTesting = false;
			logFail(message);
		}
	}
	
	// 1 point
	public void testSize05() {
		int points = 1;
		String message = "testSize";
		if(SOLUTION_LIST.size() == studentList.size()) {
			logPass(message);
			totalPoints += points;
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST.size(), studentList.size(), points);
		}
	}
	
	// 1 point
	public void testToString01() {
		int points = 1;
		String message = "testToString";
		if(listsEqual()) {
			logInfo("Pass " + message);
			totalPoints += points;
		} else {
			continueTesting = false;
			logFail(message, SOLUTION_LIST.toString(), studentList.toString(), points);
		}
	}
	
	private boolean listsEqual() {
		return SOLUTION_LIST.size() == studentList.size() && SOLUTION_LIST.toString().equals(studentList.toString());
	}
	
	private boolean setLists(int index, String value) {
		String exp = SOLUTION_LIST.set(index, value);
		String act = studentList.set(index, value);
		return exp == null ? act == null : exp.equals(act);
	}
	
	private void addToLists(String[] elements) {
		for(int index = 0; index < elements.length; index++) {
			SOLUTION_LIST.add(elements[index]);
			studentList.add(elements[index]);
		}
	}
	
	private void addToLists(int index, String element) {
		SOLUTION_LIST.add(index, element);
		studentList.add(index, element);
	}
	
	private boolean removeFromLists(int index) {
		String exp = SOLUTION_LIST.remove(index);
		String act = studentList.remove(index);
		return exp == null ? act == null : exp.equals(act);
	}
	
	private boolean removeFromLists(Object o) {
		return SOLUTION_LIST.remove(o) == studentList.remove(o);
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".LinkedList";
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
		studentList = (AbstractLinkedList<String>)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 52;
	}

}
