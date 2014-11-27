package edu.jenks.cb.lunch.test;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import edu.cb.lunch.jenks.ted.Trio;
import edu.jenks.dist.cb.lunch.*;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

public class LunchTest extends Testable {
	
	private static final String EXPECTED_SUPERCLASS = "edu.jenks.dist.cb.lunch.AbstractTrio";
	private static final Class<?>[] TRIO_CONSTRUCTOR_PARAM_TYPES = {Sandwich.class, Salad.class, Drink.class};
	private static final double DELTA = 0.001;
	
	private String studentClassName;
	private AbstractTrio studentTrio1, studentTrio2, studentTrio3, solutionTrio1, solutionTrio2, solutionTrio3;

	public void testGetName() {
		if(solutionTrio1.getName().equals(studentTrio1.getName())) {
			feedbackLogger.log(Level.INFO, "Name passed: " + solutionTrio1.getName());
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.INFO, "Name failed: " + solutionTrio1.getName());
		if(solutionTrio2.getName().equals(studentTrio2.getName())) {
			feedbackLogger.log(Level.INFO, "Name passed: " + solutionTrio2.getName());
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.INFO, "Name failed: " + solutionTrio2.getName());
		if(solutionTrio3.getName().equals(studentTrio3.getName())) {
			feedbackLogger.log(Level.INFO, "Name passed: " + solutionTrio3.getName());
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.INFO, "Name failed: " + solutionTrio3.getName());
	}
	
	public void testGetPrice() {
		if(MathUtil.equals(solutionTrio1.getPrice(), studentTrio1.getPrice(), DELTA)) {
			feedbackLogger.log(Level.INFO, "Price passed: " + solutionTrio1.getName());
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.INFO, "Price failed: " + solutionTrio1.getName());
		if(MathUtil.equals(solutionTrio2.getPrice(), studentTrio2.getPrice(), DELTA)) {
			feedbackLogger.log(Level.INFO, "Price passed: " + solutionTrio2.getName());
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.INFO, "Price failed: " + solutionTrio2.getName());
		if(MathUtil.equals(solutionTrio3.getPrice(), studentTrio3.getPrice(), DELTA)) {
			feedbackLogger.log(Level.INFO, "Price passed: " + solutionTrio3.getName());
			totalPoints += 1;
		} else
			feedbackLogger.log(Level.INFO, "Price failed: " + solutionTrio3.getName());
	}
	
	private AbstractTrio instantiateStudentTrio(AbstractTrio source) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Sandwich sandwich = new Sandwich(source.getSandwich().getName(), source.getSandwich().getPrice());
		Salad salad = new Salad(source.getSalad().getName(), source.getSalad().getPrice());
		Drink drink = new Drink(source.getDrink().getName(), source.getDrink().getPrice());
		Object[] constructorArgs = {sandwich, salad, drink};
		return (AbstractTrio)ReflectionUtil.newInstance(studentClassName, TRIO_CONSTRUCTOR_PARAM_TYPES, constructorArgs);
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() {
		super.setUp();
		studentClassName = studentPackage + ".Trio";
		Sandwich sandwich = new Sandwich("Cheeseburger", 2.75);
		Salad salad = new Salad("Spinach Salad", 1.25);
		Drink drink = new Drink("Orange Soda", 1.25);
		solutionTrio1 = new Trio(sandwich, salad, drink);
		sandwich = new Sandwich("Club Sandwich", 2.75);
		salad = new Salad("Coleslaw", 1.25);
		drink = new Drink("Orange Soda", 3.5);
		solutionTrio2 = new Trio(sandwich, salad, drink);
		sandwich = new Sandwich("Random Sandwich", Math.random() * 10);
		salad = new Salad("Random Salad", Math.random() * 10);
		drink = new Drink("Random Drink", Math.random() * 10);
		solutionTrio3 = new Trio(sandwich, salad, drink);
		try {
			studentTrio1 = instantiateStudentTrio(solutionTrio1);
			studentTrio2 = instantiateStudentTrio(solutionTrio2);
			studentTrio3 = instantiateStudentTrio(solutionTrio3);
			feedbackLogger.log(Level.INFO, "Objects instantiated");
			totalPoints += 3;
		} catch(Exception e) {
			feedbackLogger.log(Level.SEVERE, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#verifySuperClass()
	 */
	@Override
	public void verifySuperClass() {
		continueTesting = false;
		// verify that Object is the superclass
		String superclassName;
		try {
			superclassName = Class.forName(studentClassName).getSuperclass().getName();
			continueTesting = EXPECTED_SUPERCLASS.equals(superclassName);
		} catch (ClassNotFoundException e) {
			logException("Class loader", e);
		}
		if(!continueTesting)
			feedbackLogger.log(Level.WARNING, "Actual superclass did not match expected superclass");
		else
			feedbackLogger.log(Level.FINE, "Superclass validated.");
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 9;
	}

}
