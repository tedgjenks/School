/**
 * 
 */
package edu.jenks.ptc.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.ptc.Coinable;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class CoinsTest extends Testable {
	
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {int.class, int.class, int.class, int.class};
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	
	private Coinable solution, studentCoins;
	private String studentClassName;
	
	public void test10NoArgConstructor() throws Exception {
		String message = "NoArgConstructor";
		int points = 2;
		initSolutionInstance();
		initStudentInstance();
		if(solution.equals(studentCoins)) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message);
			continueTesting = false;
		}
	}
	
	public void test20ConstructorWithArgs() throws Exception {
		String message = "ConstructorWithArgs";
		int points = 2;
		initSolutionAndStudentInstances(1, 2, 3, 4);
		if(solution.equals(studentCoins)) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message);
			continueTesting = false;
		}
	}
	
	private boolean verifyAccessorMethods() {
		return solution.getNumPennies() == studentCoins.getNumPennies() &&
				solution.getNumNickels() == studentCoins.getNumNickels() &&
				solution.getNumDimes() == studentCoins.getNumDimes() &&
				solution.getNumQuarters() == studentCoins.getNumQuarters();
	}
	
	public void test15MutatorAndAccessorMethods() {
		String message = "MutatorAndAccessorMethods";
		int points = 4;
		setValues(2, 4, 6, 8);
		if(verifyAccessorMethods()) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message);
			continueTesting = false;
		}
	}
	
	private boolean verifyEquals() {
		String message = "equals";
		final int p = solution.getNumPennies(), n = solution.getNumNickels(),
				d = solution.getNumDimes(), q = solution.getNumQuarters();
		boolean exp = true, act = studentCoins.equals(solution);
		boolean pass = act;
		if(exp != act)
			logFail(message + " same", exp + " " + solution, act + " " + studentCoins, 0);
		
		message += " different";
		studentCoins.setNumPennies(studentCoins.getNumPennies() + 1);
		exp = false;
		act = studentCoins.equals(solution);
		pass = pass && !act;
		if(exp != act)
			logFail(message + " pennies", exp + " " + solution, act + " " + studentCoins, 0);
		
		setValues(p, n, d, q);
		studentCoins.setNumNickels(studentCoins.getNumNickels() + 1);
		exp = false;
		act = studentCoins.equals(solution);
		pass = pass && !act;
		if(exp != act)
			logFail(message + " nickels", exp + " " + solution, act + " " + studentCoins, 0);
		
		setValues(p, n, d, q);
		studentCoins.setNumDimes(studentCoins.getNumDimes() + 1);
		exp = false;
		act = studentCoins.equals(solution);
		pass = pass && !act;
		if(exp != act)
			logFail(message + " dimes", exp + " " + solution, act + " " + studentCoins, 0);
		
		setValues(p, n, d, q);
		studentCoins.setNumQuarters(studentCoins.getNumQuarters() + 1);
		exp = false;
		act = studentCoins.equals(solution);
		pass = pass && !act;
		if(exp != act)
			logFail(message + " quarters", exp + " " + solution, act + " " + studentCoins, 0);
		
		return pass;
	}
	
	public void test50Equals() {
		String message = "Equals";
		int points = 10;
		setValues(2, 4, 6, 8);
		boolean pass = verifyEquals();
		if(pass) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message);
			continueTesting = false;
		}
	}
	
	private boolean verifyToString() {
		return solution.toString().equals(studentCoins.toString());
	}
	
	public void test60ToString() {
		String message = "ToString";
		int points = 10;
		setValues(10, 20, 30, 40);
		if(verifyToString()) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message, solution.toString(), studentCoins.toString(), points);
			continueTesting = false;
		}
	}
	
	private boolean verifyTotalDollars(String expected, String message, int points) {
		String actual = studentCoins.getTotalDollars();
		boolean pass = expected.equals(actual);
		if(pass) {
			logPass(message, expected, actual);
			totalPoints += points;
		} else {
			logFail(message, expected, actual, points);
			continueTesting = false;
		}
		return pass;
	}
	
	private boolean verifyAllDollars(String message, int points) {
		message += " all dollars";
		boolean passAll = true;
		String detailMessage = message + " total";
		String exp = solution.getTotalDollars(), act = studentCoins.getTotalDollars();
		if(!exp.equals(act)) {
			passAll = false;
			logFail(detailMessage, exp, act, points);
		}
		
		detailMessage = message + " penny";
		exp = solution.getDollarsFromPennies();
		act = studentCoins.getDollarsFromPennies();
		if(!exp.equals(act)) {
			passAll = false;
			logFail(detailMessage, exp, act, points);
		}
		
		detailMessage = message + " nickel";
		exp = solution.getDollarsFromNickels();
		act = studentCoins.getDollarsFromNickels();
		if(!exp.equals(act)) {
			passAll = false;
			logFail(detailMessage, exp, act, points);
		}
		
		detailMessage = message + " dime";
		exp = solution.getDollarsFromDimes();
		act = studentCoins.getDollarsFromDimes();
		if(!exp.equals(act)) {
			passAll = false;
			logFail(detailMessage, exp, act, points);
		}
		
		detailMessage = message + " quarter";
		exp = solution.getDollarsFromQuarters();
		act = studentCoins.getDollarsFromQuarters();
		if(!exp.equals(act)) {
			passAll = false;
			logFail(detailMessage, exp, act, points);
		}
		
		if(passAll)
			logPass(message);
		return passAll;
	}
	
	public void test70TotalDollars() throws Exception {
		String message = "Dollars";
		int points = 2;
		initStudentInstance();
		String exp = "$0.00";
		verifyTotalDollars(exp, message, points);
		
		setValues(10, 2, 1, 2);
		exp = "$0.80";
		verifyTotalDollars(exp, message, points);
		
		setValues(1, 2, 3, 4);
		exp = "$1.41";
		verifyTotalDollars(exp, message, points);
	}
	
	public void test80All() {
		String message = "All - random coins";
		final byte iterations = 127;
		final float availPoints = 16;
		int earnedPoints = 0, passes = 0, pointsPerBlock = 1,
				blockSize = (int)Math.ceil(iterations / availPoints);
		boolean passAll = true;
		final byte maxCoins = 50;
		for(int count = 1; count <= iterations; count++) {
			String iterMessage = message + ", iteration " + count;
			boolean pass = true;
			int p = RANDOM.nextInt(maxCoins), n = RANDOM.nextInt(maxCoins),
					d = RANDOM.nextInt(maxCoins), q = RANDOM.nextInt(maxCoins);
			setValues(p, n, d, q);
			
			boolean passAccessorMethods = verifyAccessorMethods();
			if(!passAccessorMethods)
				logFail(iterMessage + " - accessor methods");
			pass = pass && passAccessorMethods;
			
			boolean passEquals = verifyEquals();
			if(!passEquals)
				logFail(iterMessage + " - equals", solution, studentCoins, pointsPerBlock);
			pass = pass && passEquals;
			
			setValues(p, n, d, q);
			boolean passToString = verifyToString();
			if(!passToString)
				logFail(iterMessage + " - toString");
			
			pass = pass && verifyAllDollars(iterMessage, pointsPerBlock);
			
			if(pass) {
				passes++;
				if(passes % blockSize == 0)
					earnedPoints += pointsPerBlock;
			}
			passAll = passAll && pass;
		}
		
		if(passAll)
			totalPoints += availPoints;
		else
			totalPoints += earnedPoints;
	}
	
	private void initSolutionInstance() {
		solution = new edu.ptc.jenks.ted.Coins();
	}
	
	private void initSolutionInstace(int p, int n, int d, int q) {
		solution = new edu.ptc.jenks.ted.Coins(p, n, d, q);
	}
	
	private void initStudentInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		studentCoins = (Coinable)ReflectionUtil.newInstance(studentClassName);
	}
	
	private void initStudentInstance(int p, int n, int d, int q) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Object[] constructorVals = {p, n, d, q};
		studentCoins = (Coinable)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, constructorVals);
	}
	
	private void initSolutionAndStudentInstances(int p, int n, int d, int q) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		initSolutionInstace(p, n, d, q);
		initStudentInstance(p, n, d, q);
	}
	
	private void setValues(int p, int n, int d, int q) {
		solution.setNumPennies(p);
		solution.setNumNickels(n);
		solution.setNumDimes(d);
		solution.setNumQuarters(q);
		studentCoins.setNumPennies(p);
		studentCoins.setNumNickels(n);
		studentCoins.setNumDimes(d);
		studentCoins.setNumQuarters(q);
	}

	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".Coins";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "java.lang.Object");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		initStudentInstance();
		initStudentInstance(0, 0, 0, 0);
		totalPoints += 50;
		initSolutionInstance();
	}

}
