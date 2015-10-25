package edu.jenks.banking.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

public class BankingTest extends Testable {
	
	private static final double DELTA = .005;

	private String bankingClassName, checkingClassName, savingsClassName, customerClassName;
	public BankingTest() {}
	
	public void test04LinkedOverdraftWithdraw() {
		int points = 1;
		String message = "Linked overdraft withdraw";
		AbstractCheckingAccount checking = checkingInstance(100, 5);
		checking.setOverdraftProtected(true);
		checking.setOverdraftMax(200);
		AbstractSavingsAccount savings = savingsInstance(200, 0);
		checking.withdraw(50); // 50, 200
		checking.withdraw(100); // 0, 150
		checking.withdraw(200); // -50, 0, 1 Ov
		checking.withdraw(100); // -150, 0, 2 Ov
		checking.withdraw(25); // -175, 0, 3 Ov
		checking.withdraw(100); // -175, 0, 3 Ov
		double expBalC = 0, expBalS = 0;
		int expOver = 3;
		double actBalC = checking.getBalance();
		double actBalS = savings.getBalance();
		int actOver = checking.getNumberOverdrafts();
		if(expOver == actOver &&
				MathUtil.equals(expBalC, actBalC, DELTA) &&
				MathUtil.equals(expBalS, actBalS, DELTA)) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message, "Over - " + expOver + ", CBal - " + expBalC + ", SBal - " + expBalS,
					"Over - " + actOver + ", CBal - " + actBalC + ", SBal - " + actBalS,
					points);
		}
	}
	
	public void test03LinkedWithdraw() {
		int points = 1;
		String message = "Linked withdraw";
		AbstractCheckingAccount checking = checkingInstance(100, 5);
		AbstractSavingsAccount savings = savingsInstance(200, 0);
		checking.setLinkedSavingsAccount(savings);
		double expAmt = 200, expBalC = 0, expBalS = 100;
		double actAmt = checking.withdraw(expAmt);
		double actBalC = checking.getBalance();
		double actBalS = savings.getBalance();
		if(MathUtil.equals(expAmt, actAmt, DELTA) &&
				MathUtil.equals(expBalC, actBalC, DELTA) &&
				MathUtil.equals(expBalS, actBalS, DELTA)) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message, "Amt - " + expAmt + ", CBal - " + expBalC + ", SBal - " + expBalS,
					"Amt - " + actAmt + ", CBal - " + actBalC + ", SBal - " + actBalS,
					points);
			return;
		}
		
		expAmt = 0;
		actAmt = checking.withdraw(500);
		actBalC = checking.getBalance();
		actBalS = savings.getBalance();
		if(MathUtil.equals(expAmt, actAmt, DELTA) &&
				MathUtil.equals(expBalC, actBalC, DELTA) &&
				MathUtil.equals(expBalS, actBalS, DELTA)) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message, "Amt - " + expAmt + ", CBal - " + expBalC + ", SBal - " + expBalS,
					"Amt - " + actAmt + ", CBal - " + actBalC + ", SBal - " + actBalS,
					points);
		}
	}
	
	public void test02Overdraft() {
		int points = 1;
		// withdraw with overdraft (500 from 70 + 500)
		AbstractCheckingAccount checking = checkingInstance(70, 5);
		checking.setOverdraftProtected(true);
		double expAmt = 500;
		checking.setOverdraftMax(expAmt);
		String message = "Checking overdraft withdraw";
		double expBal = -430;
		double actAmt = checking.withdraw(expAmt);
		double actBal = checking.getBalance();
		if(MathUtil.equals(actAmt, expAmt, DELTA) &&
				MathUtil.equals(actBal, expBal, DELTA) &&
				checking.getNumberOverdrafts() == 1) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message, expAmt + " -> " + expBal, actAmt + " -> " + actBal, points);
			return;
		}
		
		//withdraw beyond overdraft (500 from -430 + 500)
		expAmt = 0;
		actAmt = checking.withdraw(500);
		actBal = checking.getBalance();
		if(MathUtil.equals(actAmt, expAmt, DELTA) && MathUtil.equals(actBal, expBal, DELTA)) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message, expAmt + " -> " + expBal, actAmt + " -> " + actBal, points);
		}
	}
	
	public void test01BasicWithdrawChecking() {
		testBasicWithdraw(checkingInstance(100, 5));
	}
	
	public void test02BasicWithdrawSavings() {
		testBasicWithdraw(savingsInstance(100, 5));
	}
	
	private void testBasicWithdraw(Account account) {
		int points = 1;
		// legal withdraw
		double expAmt = 30, expBal = 70;
		double actAmt = account.withdraw(expAmt);
		double actBal = account.getBalance();
		String message = "account standard withdraw";
		if(MathUtil.equals(actAmt, expAmt, DELTA) && MathUtil.equals(actBal, expBal, DELTA)) {
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message + " - testing aborted", expAmt + " -> " + expBal, actAmt + " -> " + actBal, points);
			return;
		}
		
		// withdraw too much (500 from 70)
		expAmt = 0;
		actAmt = account.withdraw(500);
		actBal = account.getBalance();
		if(MathUtil.equals(actAmt, expAmt, DELTA) && MathUtil.equals(actBal, expBal, DELTA)) {
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message + " - testing aborted", expAmt + " -> " + expBal, actAmt + " -> " + actBal, points);
		}
	}

	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		Map<String, String> map = new HashMap<String, String>();
		bankingClassName = studentPackage + ".Banking";
		checkingClassName = studentPackage + ".CheckingAccount";
		savingsClassName = studentPackage + ".SavingsAccount";
		customerClassName = studentPackage + ".Customer";
		map.put(bankingClassName, "edu.jenks.dist.banking.AbstractBanking");
		map.put(checkingClassName, "edu.jenks.dist.banking.AbstractCheckingAccount");
		map.put(savingsClassName, "edu.jenks.dist.banking.AbstractSavingsAccount");
		map.put(customerClassName, "edu.jenks.dist.banking.AbstractCustomer");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String message = "object creation";
		if(bankingInstance() != null && savingsInstance(0, 0) != null && checkingInstance(0, 0) != null && customerInstance("", null, null) != null)
			totalPoints += 50;
		else {
			continueTesting = false;
			logFail(message);
		}
	}
	
	private AbstractCustomer customerInstance(String name, AbstractCheckingAccount checking, AbstractSavingsAccount savings) {
		AbstractCustomer instance = null;
		Class<?>[] types = {String.class, AbstractCheckingAccount.class, AbstractSavingsAccount.class};
		Object[] values = {name, checking, savings};
		try {
			instance = (AbstractCustomer)ReflectionUtil.newInstance(customerClassName, types, values);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logException("customerInstance", e);
		}
		return instance;
	}
	
	private AbstractCheckingAccount checkingInstance(double balance, double apr) {
		AbstractCheckingAccount instance = null;
		Class<?>[] types = {double.class, double.class};
		Object[] values = {balance, apr};
		try {
			instance = (AbstractCheckingAccount)ReflectionUtil.newInstance(checkingClassName, types, values);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logException("checkingInstance", e);
		}
		return instance;
	}
	
	private AbstractSavingsAccount savingsInstance(double balance, double apr) {
		AbstractSavingsAccount instance = null;
		Class<?>[] types = {double.class, double.class};
		Object[] values = {balance, apr};
		try {
			instance = (AbstractSavingsAccount)ReflectionUtil.newInstance(savingsClassName, types, values);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logException("savingsInstance", e);
		}
		return instance;
	}
	
	private AbstractBanking bankingInstance() {
		AbstractBanking instance = null;
		try {
			instance = (AbstractBanking)ReflectionUtil.newInstance(bankingClassName);
		} catch (ClassNotFoundException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException e) {
			logException("bankingInstance", e);
		}
		return instance;
	}

}
