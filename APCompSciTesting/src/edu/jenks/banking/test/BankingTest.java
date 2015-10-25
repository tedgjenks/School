package edu.jenks.banking.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class BankingTest extends Testable {

	private String bankingClassName, checkingClassName, savingsClassName, customerClassName;
	public BankingTest() {}

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
