/**
 * 
 */
package edu.jenks.lewis.ap.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.lewis.ap.AbstractName;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;
import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
public class NameTest extends Testable {
	
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {String.class, String.class};
	
	private String studentClassName;

	/**
	 * 
	 */
	public NameTest() {}

	// 10 points
	public void test01Constructor() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int points = 5;
		boolean passAll = true;
		String first = "First", last = "Last", method = "constructor";
		AbstractName name = createStudentName(first, last);
		String actFirst = name.getFirst();
		String actLast = name.getLast();
		if(first.equals(actFirst) && last.equals(actLast))
			totalPoints += points;
		else {
			passAll = continueTesting = false;
			logFail(method, first + " " + last, actFirst + " " + actLast, points);
		}
		
		int exp = 0;
		int act = name.getDuplicates();
		if(exp == act)
			totalPoints += points;
		else {
			passAll = continueTesting = false;
			logFail(method, "duplicates = " + exp, "duplicates = " + act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void test02CompareLast() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int points = 5, act, exp;
		boolean passAll = true;
		String first = "First", last1 = "King", last2 = "Queen", method = "compareTo (last name different)";
		AbstractName name1 = createStudentName(first, last1);
		AbstractName name2 = createStudentName(first, last2);
		act = name1.compareTo(name2);
		exp = last1.compareTo(last2);
		if(act * exp > 0)
			totalPoints += points;
		else {
			continueTesting = false;
			logFail(method, exp, act, points);
		}
		
		last2 = "Jack";
		name2 = createStudentName(first, last2);
		act = name1.compareTo(name2);
		exp = last1.compareTo(last2);
		if(act * exp > 0)
			totalPoints += points;
		else {
			continueTesting = false;
			logFail(method, exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 20 points
	public void test03CompareFirst() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int points = 5, act, exp;
		boolean passAll = true;
		String first1 = "Queen", first2 = "King", last = "Last", method = "compareTo (first name)";
		AbstractName name1 = createStudentName(first1, last);
		AbstractName name2 = createStudentName(first2, last);
		act = name1.compareTo(name2);
		exp = first1.compareTo(first2);
		if(act * exp > 0)
			totalPoints += points;
		else {
			continueTesting = false;
			logFail(method, exp, act, points);
		}
		
		first1 = "Jack";
		name1 = createStudentName(first1, last);
		name2 = createStudentName(first2, last);
		act = name1.compareTo(name2);
		exp = first1.compareTo(first2);
		if(act * exp > 0)
			totalPoints += points;
		else {
			continueTesting = false;
			logFail(method, exp, act, points);
		}
		
		first1 = first2;
		name1 = createStudentName(first1, last);
		name2 = createStudentName(first2, last);
		act = name1.compareTo(name2);
		exp = first1.compareTo(first2);
		if(act == exp)
			totalPoints += points;
		else {
			continueTesting = false;
			logFail(method, exp, act, points);
		}
		
		first1 = StringUtil.buildRandomString(10, 'A', 'z' + 1);
		first2 = StringUtil.buildRandomString(10, 'A', 'z' + 1);
		name1 = createStudentName(first1, last);
		name2 = createStudentName(first2, last);
		act = name1.compareTo(name2);
		exp = first1.compareTo(first2);
		if(act == exp || act * exp > 0)
			totalPoints += points;
		else {
			continueTesting = false;
			logFail(method, exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void test04CompareDuplicates() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean passAll = true;
		String method = "compareDuplicates", first1, last = "Last", first2;
		int points = 5, exp, act;
		first1 = "first1";
		first2 = "frist2";
		AbstractName name1 = createStudentName(first1, last);
		AbstractName name2 = createStudentName(first2, last);
		name1.compareTo(name2);
		exp = 0;
		act = name1.getDuplicates();
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method, exp, act, points);
		}
		
		if(passAll) {
			name2.setFirst(first1);
			int comparisons = new Random(System.currentTimeMillis()).nextInt(15) + 5;
			for(int index = comparisons; index > 0; index--, exp++)
				name1.compareTo(name2);
			act = name1.getDuplicates();
			if(exp == act)
				totalPoints += points;
			else {
				passAll = false;
				logFail(method, exp, act, points);
			}
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 5 points
	public void test05ToString() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int points = 5;
		String last = StringUtil.buildRandomString(10, 'A', 'z' + 1), first = StringUtil.buildRandomString(10, 'A', 'z' + 1), method = "toString";
		AbstractName studentName = createStudentName(first, last);
		String exp = last + ", " + first;
		String act = studentName.toString();
		if(exp.equals(act)) {
			totalPoints += points;
			logPass(method);
		} else
			logFail(method, exp, act, points);
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
		studentClassName = studentPackage + ".Name";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.lewis.ap.AbstractName");
		return map;
	}
	
	public AbstractName createSolutionName(String first, String last) {
		return new edu.lewis.ap.jenks.ted.Name(first, last);
	}
	
	public AbstractName createStudentName(String first, String last) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {first, last};
		return (AbstractName)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, args);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		createStudentName("", "");
		//if(name.getClass().getPackage().toString().indexOf("brady") >= 0)
			//throw new InstantiationException("You will never pass a program again!  Mwahahaha!");
		totalPoints += 45;
	}

}
