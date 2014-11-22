/**
 * 
 */
package edu.jenks.banking.test;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import edu.jenks.dist.banking.*;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author JenksT
 *
 */
public class BankingP1Test extends Testable {
	
	public static final String C_ACCOUNT_EXPECTED_SUPERCLASS = "edu.jenks.dist.banking.AccountGen";
	public static final String CUSTOMER_EXPECTED_SUPERCLASS = "java.lang.Object";
	
	private static final double DOLLAR_DELTA = 0.005;
	private static final Class<?>[] C_ACCOUNT_CONSTRUCTOR_PARAM_TYPES = {double.class};
	private static double C_ACCOUNT_INIT_BALANCE = 150;
	private static final Object[] C_ACCOUNT_CONSTRUCTOR_ARGS = {C_ACCOUNT_INIT_BALANCE}; // initialize account with $150
	private static final Class<?>[] CUSTOMER_CONSTRUCTOR_PARAM_TYPES = {String.class};
	private static final Object[] CUSTOMER_CONSTRUCTOR_ARGS = {"John Doe"}; // initialize customer name
	
	private CheckingAccount studentCheckingAccount;
	private Customer studentCustomer;
	
	/**
	 * 
	 */
	public BankingP1Test() {}
	
	
	
	@Override
	public void setUp() {
		super.setUp();
		try {
			studentCheckingAccount = (CheckingAccount)ReflectionUtil.newInstance(studentPackage + ".CheckingAccountGen", C_ACCOUNT_CONSTRUCTOR_PARAM_TYPES, C_ACCOUNT_CONSTRUCTOR_ARGS);
			feedbackLogger.log(Level.INFO, "checking account instantiated");
			totalPoints += 5;
			studentCustomer = (Customer)ReflectionUtil.newInstance(studentPackage + ".CustomerGen", CUSTOMER_CONSTRUCTOR_PARAM_TYPES, CUSTOMER_CONSTRUCTOR_ARGS);
			feedbackLogger.log(Level.INFO, "customer instantiated");
			totalPoints += 5;
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			feedbackLogger.log(Level.SEVERE, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
		
	}

	@Override
	public void verifySuperClass() {
		continueTesting = false;
		if(studentCheckingAccount != null && studentCustomer != null) {
			// verify that Object is the superclass
			String caSuperclassName = studentCheckingAccount.getClass().getSuperclass().getName();
			String custSuperclassName = studentCustomer.getClass().getSuperclass().getName();
			continueTesting = C_ACCOUNT_EXPECTED_SUPERCLASS.equals(caSuperclassName) && CUSTOMER_EXPECTED_SUPERCLASS.equals(custSuperclassName);
			if(!continueTesting)
				feedbackLogger.log(Level.WARNING, "Actual superclass did not match expected superclass");
			else
				feedbackLogger.log(Level.FINE, "Superclass validated.");
		}
	}
	
	public void testCustomer() {
		studentCustomer.addCheckingAccount(studentCheckingAccount);
		if(studentCustomer.getCheckingAccount() == studentCheckingAccount) {
			feedbackLogger.log(Level.INFO, "Add and get checking account to customer passed");
			totalPoints += 5;
		} else
			feedbackLogger.log(Level.WARNING, "Add and get checking account to customer failed.");
	}

	public void testWithdraw() {
		studentCheckingAccount.setBalance(C_ACCOUNT_INIT_BALANCE);
		double expWA = 15, expBalance = C_ACCOUNT_INIT_BALANCE - expWA;
		double actWA = studentCheckingAccount.withdraw(expWA);
		double actBalance = studentCheckingAccount.getBalance();
		if(MathUtil.equals(expWA, actWA, DOLLAR_DELTA) && MathUtil.equals(expBalance, actBalance, DOLLAR_DELTA)) {
			feedbackLogger.log(Level.INFO, "Basic withdraw passed.");
			totalPoints += 10;
			expWA = 0;
			actWA = studentCheckingAccount.withdraw(C_ACCOUNT_INIT_BALANCE);
			actBalance = studentCheckingAccount.getBalance();
			if(MathUtil.equals(expWA, actWA, DOLLAR_DELTA) && MathUtil.equals(expBalance, actBalance, DOLLAR_DELTA)) {
				feedbackLogger.log(Level.INFO, "Overdraft passed.");
				totalPoints += 5;
			} else
				feedbackLogger.log(Level.WARNING, "Overdraft failed.");
		} else
			feedbackLogger.log(Level.WARNING, "Basic withdraw failed.");
	}
	
	public void testDeposit() {
		studentCheckingAccount.setBalance(C_ACCOUNT_INIT_BALANCE);
		double expDA = 33, expBalance = C_ACCOUNT_INIT_BALANCE + expDA;
		double actDA = studentCheckingAccount.deposit(expDA);
		double actBalance = studentCheckingAccount.getBalance();
		if(MathUtil.equals(expDA, actDA, DOLLAR_DELTA) && MathUtil.equals(expBalance, actBalance, DOLLAR_DELTA)) {
			feedbackLogger.log(Level.INFO, "Deposit passed.");
			totalPoints += 10;
		} else
			feedbackLogger.log(Level.WARNING, "Deposit failed.");
	}



	@Override
	public int getPointsAvailable() {
		return 40;
	}
}
