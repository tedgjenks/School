/**
 * 
 */
package edu.jenks.lewis.ap.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.lewis.ap.AbstractHighSchoolClass;
import edu.jenks.dist.lewis.ap.Student;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class HighSchoolClassTest extends Testable {
	
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {Student[].class};
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final double PERCENT_DELTA = 0.01;
	private String studentClassName;
	private Student[] basicStudents;
	private final Student[] RANDOM_STUDENTS;
	private AbstractHighSchoolClass studentBasic;

	/**
	 * 
	 */
	public HighSchoolClassTest() {
		RANDOM_STUDENTS = generateRandomStudents();
	}
	
	// 10 points
	public void testGetValedictorian() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String method = "getValedictorian";
		int points = 5;
		boolean passAll = true;
		Student[] exp, act;
		
		act = studentBasic.getValedictorian();
		exp = new Student[]{basicStudents[5], basicStudents[8]};
		if(Arrays.equals(act, exp)) {
			Student[] sevVal = generateMultipleValedictorians();
			AbstractHighSchoolClass studentSevVal = createStudentHighSchoolClass(sevVal);
			Student[] expSevVal = new Student[]{sevVal[0], sevVal[1], sevVal[5], sevVal[8]};
			Student[] actSevVal = studentSevVal.getValedictorian();
			if(Arrays.equals(actSevVal, expSevVal))
				totalPoints += points;
			else {
				logFail(method + " (several)", Arrays.toString(expSevVal), Arrays.toString(actSevVal), points);
				passAll = false;
			}
		} else {
			logFail(method + " (basic)", Arrays.toString(exp), Arrays.toString(act), points);
			passAll = false;
		}
		
		AbstractHighSchoolClass studentRandom = createStudentHighSchoolClass(RANDOM_STUDENTS);
		AbstractHighSchoolClass solutionRandom = createSolutionHighSchoolClass(RANDOM_STUDENTS);
		act = studentRandom.getValedictorian();
		exp = solutionRandom.getValedictorian();
		if(Arrays.equals(act, exp))
			totalPoints += points;
		else {
			logFail(method + " (random)", Arrays.toString(exp), Arrays.toString(act), points);
			passAll = false;
		}
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void testGetHonorsPercent() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String method = "getHonorsPercent";
		int points = 5;
		boolean passAll = true;
		double exp, act;
		
		act = studentBasic.getHonorsPercent();
		exp = 5/9.0 * 100;
		if(MathUtil.equals(act, exp, PERCENT_DELTA))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " (basic)", exp, act, points);
		}
		
		AbstractHighSchoolClass studentRandom = createStudentHighSchoolClass(RANDOM_STUDENTS);
		AbstractHighSchoolClass solutionRandom = createSolutionHighSchoolClass(RANDOM_STUDENTS);
		act = studentRandom.getHonorsPercent();
		exp = solutionRandom.getHonorsPercent();
		if(MathUtil.equals(act, exp, PERCENT_DELTA))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " (random)", exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void testGraduateWithHonorsPercent() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String method = "graduateWithHonorsPercent";
		int points = 5;
		boolean passAll = true;
		double exp, act;
		
		act = studentBasic.graduateWithHonorsPercent();
		exp = 5/9.0 * 100;
		if(MathUtil.equals(act, exp, PERCENT_DELTA))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " (basic)", exp, act, points);
		}
		
		AbstractHighSchoolClass studentRandom = createStudentHighSchoolClass(RANDOM_STUDENTS);
		AbstractHighSchoolClass solutionRandom = createSolutionHighSchoolClass(RANDOM_STUDENTS);
		act = studentRandom.graduateWithHonorsPercent();
		exp = solutionRandom.graduateWithHonorsPercent();
		if(MathUtil.equals(act, exp, PERCENT_DELTA))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " (random)", exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void testGraduateWithHighestHonorsPercent() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String method = "graduateWithHighestHonorsPercent";
		int points = 5;
		boolean passAll = true;
		double exp, act;
		
		act = studentBasic.graduateWithHighestHonorsPercent();
		exp = 3/9.0 * 100;
		if(MathUtil.equals(act, exp, PERCENT_DELTA))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " (basic)", exp, act, points);
		}
		
		AbstractHighSchoolClass studentRandom = createStudentHighSchoolClass(RANDOM_STUDENTS);
		AbstractHighSchoolClass solutionRandom = createSolutionHighSchoolClass(RANDOM_STUDENTS);
		act = studentRandom.graduateWithHighestHonorsPercent();
		exp = solutionRandom.graduateWithHighestHonorsPercent();
		if(MathUtil.equals(act, exp, PERCENT_DELTA))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " (random)", exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void testGetHonorsStudents() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String method = "getHonorsStudents";
		int points = 5;
		boolean passAll = true;
		Student[] exp, act;
		
		act = studentBasic.getHonorsStudents();
		exp = new Student[]{basicStudents[1], basicStudents[4], basicStudents[5], basicStudents[6], basicStudents[8]};
		if(Arrays.equals(act, exp))
			totalPoints += points;
		else {
			logFail(method + " (basic)", exp.toString(), act.toString(), points);
			passAll = false;
		}
		
		AbstractHighSchoolClass studentRandom = createStudentHighSchoolClass(RANDOM_STUDENTS);
		AbstractHighSchoolClass solutionRandom = createSolutionHighSchoolClass(RANDOM_STUDENTS);
		act = studentRandom.getHonorsStudents();
		exp = solutionRandom.getHonorsStudents();
		if(Arrays.equals(act, exp))
			totalPoints += points;
		else {
			logFail(method + " (random)");
			passAll = false;
		}
		if(passAll)
			logPass(method);
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
		studentClassName = studentPackage + ".HighSchoolClass";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.lewis.ap.AbstractHighSchoolClass");
		return map;
	}
	
	public AbstractHighSchoolClass createSolutionHighSchoolClass(Student[] students) {
		return new edu.lewis.ap.jenks.ted.HighSchoolClass(students);
	}
	
	public AbstractHighSchoolClass createStudentHighSchoolClass(Student[] students) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] args = {students};
		return (AbstractHighSchoolClass)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, args);
	}
	
	public Student[] generateRandomStudents() {
		int size = 50;
		Student[] students = new Student[size];
		for(int index = size - 1; index >= 0; index--) {
			double gpa = RANDOM.nextDouble() * 4;
			students[index] = new Student("first", "last", gpa, gpa > 2.7);
		}
		return students;
	}
	
	public Student[] generateMultipleValedictorians() {
		final int maxGpa = 4;
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("F1", "L1", maxGpa, false)); // 0
		students.add(new Student("F2", "L2", maxGpa, true)); // 1
		students.add(new Student("F3", "L3", 3.6, false)); // 2
		students.add(new Student("F4", "L4", 1.5, false)); // 3
		students.add(new Student("F5", "L5", 2.9, true)); // 4
		students.add(new Student("F6", "L6", maxGpa, true)); // 5
		students.add(new Student("F7", "L7", 3.85, true)); // 6
		students.add(new Student("F8", "L8", 0.5, false)); // 7
		students.add(new Student("F9", "L9", maxGpa, true)); // 8
		return students.toArray(new Student[students.size()]);
	}
	
	public Student[] generateBasicStudents() {
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("F1", "L1", 2.5, false)); // 0
		students.add(new Student("F2", "L2", 3.5, true)); // 1
		students.add(new Student("F3", "L3", 3.6, false)); // 2
		students.add(new Student("F4", "L4", 1.5, false)); // 3
		students.add(new Student("F5", "L5", 2.9, true)); // 4
		students.add(new Student("F6", "L6", 3.9, true)); // 5
		students.add(new Student("F7", "L7", 3.85, true)); // 6
		students.add(new Student("F8", "L8", 0.5, false)); // 7
		students.add(new Student("F9", "L9", 3.9, true)); // 8
		return students.toArray(new Student[students.size()]);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		basicStudents = generateBasicStudents();
		studentBasic = createStudentHighSchoolClass(basicStudents);
		totalPoints += 50;
	}

}
