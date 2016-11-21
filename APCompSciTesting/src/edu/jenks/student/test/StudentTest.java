/**
 * 
 */
package edu.jenks.student.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.student.AbstractStudent;
import edu.jenks.dist.student.Address;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class StudentTest extends Testable {
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {String.class, String.class, Address.class, Address.class, double.class, double.class, double.class};
	private static String firstName = "f", lastName = "l";
	private static Address homeAddress, schoolAddress;
	private static double testScore1 = 45, testScore2 = 98, testScore3 = 73;
	private final Object[] DEFAULT_CONSTRUCTOR_VALUES = new Object[7];
	
	private String studentClassName;
	private AbstractStudent studentStudent, solutionStudent;
	
	public StudentTest() {
		super();
		homeAddress = new Address("home street 1", "home city", "Home State", 11111);
		schoolAddress = new Address("school street 2", "school city", "School State", 22222);
		solutionStudent = new edu.student.jenks.ted.Student(firstName, lastName, homeAddress, schoolAddress, testScore1, testScore2, testScore3);
		DEFAULT_CONSTRUCTOR_VALUES[0] = firstName;
		DEFAULT_CONSTRUCTOR_VALUES[1] = lastName;
		DEFAULT_CONSTRUCTOR_VALUES[2] = homeAddress;
		DEFAULT_CONSTRUCTOR_VALUES[3] = schoolAddress;
		DEFAULT_CONSTRUCTOR_VALUES[4] = testScore1;
		DEFAULT_CONSTRUCTOR_VALUES[5] = testScore2;
		DEFAULT_CONSTRUCTOR_VALUES[6] = testScore3;
	}
	
	public void test01Initialization() {
		int points = 5;
		boolean pass = solutionStudent.equals(studentStudent);
		for(int scoreIndex = 1; pass && scoreIndex <= 3; scoreIndex++) {
			pass = MathUtil.equals(solutionStudent.getTestScore(scoreIndex), studentStudent.getTestScore(scoreIndex), .001);
		}
		if(pass) {
			totalPoints += points;
			logPass("Initialization");
		} else {
			logFail("Initialization", solutionStudent.toString(), studentStudent.toString(), points);
			continueTesting = false;
		}
	}
	
	public void test02Average() {
		int points = 2;
		double exp = solutionStudent.average(), act = studentStudent.average();
		if(MathUtil.equals(exp, act, .001)) {
			totalPoints += points;
			logPass("Average");
		} else
			logFail("Average", exp, act, points);
	}
	
	public void test03ToString() {
		int points = 3;
		String expected = solutionStudent.toString(), actual = studentStudent.toString();
		if(expected.equals(actual)) {
			logPass("toString");
			totalPoints += points;
		} else
			logFail("toString", expected, actual, points);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 15;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".Student";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.student.AbstractStudent");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentStudent = (AbstractStudent)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, DEFAULT_CONSTRUCTOR_VALUES);
		totalPoints += 5;
	}

}
