package edu.jenks.banking.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
	
	public void test13PerformMaintenanceAll() {
		String message = "Perform maintenance all";
		AbstractBanking banking = bankingInstance();
		banking.setBankingFee(30);
		banking.setMinNoFeeCombinedBalance(100);
		initCustomers(banking);
		
		AbstractCustomer cust0 = banking.getCustomers().get(0);
		AbstractCheckingAccount checking0 = cust0.getCheckingAccount(); // 100
		checking0.setOverdraftFee(50);
		checking0.setOverdraftProtected(true);
		checking0.setOverdraftMax(100);
		checking0.withdraw(150);
		checking0.withdraw(15);
		
		AbstractCustomer cust1 = banking.getCustomers().get(1);
		AbstractSavingsAccount savings1 = cust1.getSavingsAccount(); // 300, 6%
		savings1.setMaxMonthlyTransactions(3);
		for(int withdrawals = 10; withdrawals > 0; withdrawals--)
			savings1.withdraw(50);
		
		AbstractCustomer cust2 = banking.getCustomers().get(2);
		AbstractCheckingAccount checking2 = cust2.getCheckingAccount(); // 200, 3%
		checking2.setOverdraftFee(50);
		checking2.setOverdraftProtected(true);
		checking2.setOverdraftMax(100);
		AbstractSavingsAccount savings2 = cust2.getSavingsAccount(); // 400, 7%
		savings2.setMaxMonthlyTransactions(4);
		//checking at 60 on first linked savings
		for(int withdrawals = 15; withdrawals > 0; withdrawals--)
			checking2.withdraw(70);
		
		// expect savings 180
		banking.performMaintenance(30);
		if(!(verifyCustomerReset(cust0) && verifyCustomerReset(cust1) && verifyCustomerReset(cust2)))
			logFail(message + " - overdrafts and transactions should be reset after maintenance.");
		else {
			testCustBalance(message + " checking only", cust0, -195, 0);
			testCustBalance(message + " savings only", cust1, 0, 150.7411);
			testCustBalance(message + " checking and savings", cust2, 0, 30.173);
		}
	}
	
	private boolean verifyCustomerReset(AbstractCustomer customer) {
		AbstractCheckingAccount checking = customer.getCheckingAccount();
		AbstractSavingsAccount savings = customer.getSavingsAccount();
		boolean checkingReset = checking != null ? checking.getNumberOverdrafts() == 0 : true;
		boolean savingsReset = savings != null ? savings.getNumTransactions() == 0 : true;
		return checkingReset && savingsReset;
	}
	
	public void test12PerformMaintenanceOverdraftFees() {
		String message = "Perform maintenance overdraft fees";
		AbstractBanking banking = bankingInstance();
		initCustomers(banking);
		AbstractCustomer cust0 = banking.getCustomers().get(0);
		AbstractCheckingAccount checkingAccount0 = cust0.getCheckingAccount();
		checkingAccount0.setOverdraftProtected(true);
		checkingAccount0.setOverdraftFee(20);
		checkingAccount0.setOverdraftMax(100);
		for(int withdrawals = 20; withdrawals > 0; withdrawals--) // expect 5 overdrafts
			checkingAccount0.withdraw(20);
		
		AbstractCustomer cust2 = banking.getCustomers().get(2);
		AbstractCheckingAccount checkingAccount2 = cust2.getCheckingAccount();
		checkingAccount2.setOverdraftProtected(true);
		checkingAccount2.setOverdraftFee(25);
		checkingAccount2.setOverdraftMax(200);
		AbstractSavingsAccount savingsAccount = cust2.getSavingsAccount();
		savingsAccount.setMaxMonthlyTransactions(100);
		for(int withdrawals = 30; withdrawals > 0; withdrawals--) // expect 6 overdrafts
			checkingAccount2.withdraw(30);
		
		banking.performMaintenance(50);
		testCustBalance(message + " no savings", cust0, -200, 0); // -100
		testCustBalance(message + " linked savings", cust2, -330, 0); // -180
	}
	
	public void test11PerformMaintenanceBankingFees() {
		String message = "Perform maintenance banking fees";
		AbstractBanking banking = bankingInstance();
		initCustomers(banking);
		banking.addCustomer("cust3", checkingInstance(15, 3), savingsInstance(50, 4));
		banking.addCustomer("cust4", checkingInstance(5, 4), savingsInstance(10, 5));
		banking.setMinNoFeeCombinedBalance(500);
		banking.setBankingFee(20);
		banking.performMaintenance(50);
		List<AbstractCustomer> customers = banking.getCustomers();
		testCustBalance(message + " no savings", customers.get(0), 80.2193, 0); // 80
		testCustBalance(message + " no checking", customers.get(1), 0, 282.3093); // 280
		testCustBalance(message + " no fee", customers.get(2), 200.8231, 403.8515); // 600
		testCustBalance(message + " linked savings over", customers.get(3), 0, 45.247); // 65
		testCustBalance(message + " linked savings under", customers.get(4), -5, 0); // 15
	}
	
	public void test10PerformMaintenanceNoFees() {
		String message = "Perform maintenance no fees";
		AbstractBanking banking = bankingInstance();
		initCustomers(banking);
		banking.performMaintenance(50);
		List<AbstractCustomer> customers = banking.getCustomers();
		testCustBalance(message, customers.get(0), 100.2742, 0);
		testCustBalance(message, customers.get(1), 0, 302.4743);
		testCustBalance(message, customers.get(2), 200.8231, 403.8515);
	}
	
	private void testCustBalance(String message, AbstractCustomer customer, double expCheckBal, double expSavBal) {
		int points = 1;
		AbstractCheckingAccount checking = customer.getCheckingAccount();
		AbstractSavingsAccount savings = customer.getSavingsAccount();
		Double actCheckBal = checking != null ? checking.getBalance() : 0;
		Double actSavBal = savings != null ? savings.getBalance() : 0;
		if((checking != null && !MathUtil.equals(expCheckBal, actCheckBal, DELTA)) || (savings != null && !MathUtil.equals(expSavBal, actSavBal, DELTA))) {
			logBalFail(message + " checking", expCheckBal, actCheckBal, points);
			logBalFail(message + " savings", expSavBal, actSavBal, points);
			continueTesting = false;
		} else {
			totalPoints += 1;
			logPass(message);
		}
	}
	
	private void initCustomers(AbstractBanking banking) {
		AbstractCheckingAccount checking = checkingInstance(100, 2);
		banking.addCustomer("Alf", checking, null);
		AbstractSavingsAccount savings = savingsInstance(300, 6);
		banking.addCustomer("Burford", null, savings);
		checking = checkingInstance(200, 3);
		savings = savingsInstance(400, 7);
		banking.addCustomer("Corky", checking, savings);
	}
	
	public void test09CustomerCompareTo() {
		int points = 2;
		String message = "Customer compareTo";
		String[] names = {"Bozo", "Al", "Cookie"};
		List<String> nameList = new ArrayList<String>(3);
		List<AbstractCustomer> custList = new ArrayList<AbstractCustomer>(3);
		for(String name : names) {
			nameList.add(name);
			custList.add(customerInstance(name, null, null));
		}
		Collections.sort(nameList);
		Collections.sort(custList);
		boolean orderCorrect = true;
		for(int index = nameList.size() - 1; index >= 0 && orderCorrect; index--) {
			String name = nameList.get(index);
			AbstractCustomer cust = custList.get(index);
			if(!name.equals(cust.toString()))
				orderCorrect = false;
		}
		if(orderCorrect) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message, nameList.toString(), custList.toString(), points);
	}
	
	public void test08CustomerToString() {
		int points = 1;
		String message = "Customer toString()";
		String expVal = "Al";
		AbstractCustomer customer = customerInstance(expVal, null, null);
		String actVal = customer.toString();
		if(expVal.equals(actVal)) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message + " - testing aborted", expVal, actVal, points);
			continueTesting = false;
		}
	}
	
	public void test07PayInterest() {
		testPayInterest("checking", checkingInstance(100, 5));
		testPayInterest("savings", savingsInstance(100, 5));
	}
	
	private boolean testPayInterest(String accountType, Account account) {
		int points = 2;
		String message = "Pay interest " + accountType;
		boolean pass = false;
		double expBal = 100.4115;
		account.payInterest(30);
		double actBal = account.getBalance();
		if(MathUtil.equals(expBal, actBal, DELTA)) {
			pass = true;
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			message += " - testing aborted";
			logFail(message, expBal, actBal, points);
		}
		return pass;
	}
	
	public void test06Transfer() {
		AbstractCheckingAccount checking = checkingInstance(100, 0);
		AbstractSavingsAccount savings = savingsInstance(200, 0);
		savings.setMaxMonthlyTransactions(3);
		boolean pass = testTransfer(checking, savings, 50, 50); // 50, 250, 1 T
		if(!pass)
			return;
		pass = testTransfer(checking, savings, 100, 0); // 50, 250, 1 T
		if(!pass)
			return;
		pass = testTransfer(savings, checking, 100, 100); // 150, 150, 2 T
		if(!pass)
			return;
		pass = testTransfer(savings, checking, 200, 0); // 150, 150, 2 T
		if(!pass)
			return;
		pass = testTransfer(savings, checking, 100, 100); // 250, 50, 3 T
		if(!pass)
			return;
		testTransfer(savings, checking, 5, 0); // 250, 50, 3 T
	}
	
	private boolean testTransfer(Account from, Account to, double reqAmt, double expAmt) {
		boolean pass = false;
		int points = 2;
		String message = "Transfer from " + from.getClass().toString() + " to " + to.getClass().toString();
		double expFromBal = from.getBalance() - expAmt;
		double expToBal = to.getBalance() + expAmt;
		double actAmt = from.transfer(to, reqAmt);
		double actFromBal = from.getBalance();
		double actToBal = to.getBalance();
		if(MathUtil.equals(expAmt, actAmt, DELTA) &&
				MathUtil.equals(expFromBal, actFromBal, DELTA) &&
				MathUtil.equals(expToBal, actToBal, DELTA)) {
			totalPoints += points;
			logPass(message);
			pass = true;
		} else
			logFail(message, "Amt: " + expAmt + ", From: " + expFromBal + ", To: " + expToBal,
					"Amt: " + actAmt + ", From: " + actFromBal + ", To: " + actToBal,
					points);
		return pass;
	}
	
	public void test05SavingsTransactions() {
		boolean pass = true;
		int points = 1;
		String message = "Savings transactions";
		AbstractCheckingAccount checking = checkingInstance(100, 0);
		AbstractSavingsAccount savings = savingsInstance(200, 0);
		checking.setLinkedSavingsAccount(savings);
		savings.setMaxMonthlyTransactions(3);
		savings.transfer(checking, 100);
		savings.deposit(100);
		savings.withdraw(100);
		double expAmt = 0, expBal = 100;
		double actAmt = savings.deposit(50);
		double actBal = savings.getBalance();
		if(MathUtil.equals(expAmt, actAmt, DELTA) && MathUtil.equals(expBal, actBal, DELTA)) {
			logPass(message);
		} else {
			pass = false;
			logBalFail(message, expAmt, expBal, actAmt, actBal, points);
		}
		
		message += " with checking overdraft";
		expAmt = 0;
		double expBalC = 100, expBalS = 200;
		checking = checkingInstance(expBalC, 0);
		checking.setOverdraftProtected(true);
		checking.setOverdraftMax(100);
		savings = savingsInstance(expBalS, 0);
		checking.setLinkedSavingsAccount(savings);
		savings.setMaxMonthlyTransactions(0);
		actAmt = checking.withdraw(250);
		double actBalC = checking.getBalance();
		double actBalS = savings.getBalance();
		if(MathUtil.equals(expAmt, actAmt, DELTA)
				&& MathUtil.equals(expBalC, actBalC, DELTA)
				&& MathUtil.equals(expBalS, actBalS, DELTA)) {
			logPass(message);
		} else {
			pass = false;
			logBalFail(message + " (checking)", expAmt, expBalC, actAmt, actBalC, points);
			logBalFail(message + " (savings)", expBalS, actBalS, points);
		}
		if(pass)
			totalPoints += points;
	}
	
	public void test04LinkedOverdraftWithdraw() {
		int points = 1;
		String message = "Linked overdraft withdraw";
		AbstractCheckingAccount checking = checkingInstance(100, 5);
		checking.setOverdraftProtected(true);
		checking.setOverdraftMax(200);
		AbstractSavingsAccount savings = savingsInstance(200, 0);
		savings.setMaxMonthlyTransactions(100);
		checking.setLinkedSavingsAccount(savings);
		checking.withdraw(50); // 50, 200
		checking.withdraw(100); // 0, 150
		checking.withdraw(200); // -50, 0, 1 Ov
		checking.withdraw(100); // -150, 0, 2 Ov
		checking.withdraw(25); // -175, 0, 3 Ov
		checking.withdraw(100); // -175, 0, 3 Ov
		double expBalC = -175, expBalS = 0;
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
			logFail(message, "Over - " + expOver, "Over - " + actOver, points);
			logBalFail(message + " checking", expBalC, actBalC, points);
			logBalFail(message + " savings", expBalS, actBalS, points);
		}
	}
	
	public void test03LinkedWithdraw() {
		int points = 2;
		String message = "Linked withdraw";
		AbstractCheckingAccount checking = checkingInstance(100, 5);
		AbstractSavingsAccount savings = savingsInstance(200, 0);
		savings.setMaxMonthlyTransactions(100);
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
			logBalFail(message + " checking", expAmt, expBalC, actAmt, actBalC, points);
			logBalFail(message + " savings", expBalS, actBalS, points);
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
			logBalFail(message + " checking", expAmt, expBalC, actAmt, actBalC, points);
			logBalFail(message + " savings", expBalS, actBalS, points);
		}
	}
	
	public void test02Overdraft() {
		int points = 2;
		// withdraw with overdraft (500 from 70 + 500)
		AbstractCheckingAccount checking = checkingInstance(70, 5);
		checking.setOverdraftProtected(true);
		double expAmt = 500;
		checking.setOverdraftMax(expAmt);
		String message = "Checking overdraft withdraw";
		double expBal = -430;
		double actAmt = checking.withdraw(expAmt);
		double actBal = checking.getBalance();
		int expOverdrafts = 1, actOverdrafts = checking.getNumberOverdrafts();
		if(MathUtil.equals(actAmt, expAmt, DELTA) &&
				MathUtil.equals(actBal, expBal, DELTA) &&
				actOverdrafts == expOverdrafts) {
			totalPoints += points;
			logPass(message);
		} else {
			logFail(message + " (number overdrafts)", expOverdrafts, actOverdrafts, points);
			logBalFail(message, expAmt, expBal, actAmt, actBal, points);
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
			logBalFail(message, expAmt, expBal, actAmt, actBal, points);
		}
	}
	
	private void logBalFail(String message, double expBal, double actBal, int points) {
		logFail(message, "Exp balance: " + expBal, "Act balance: " + actBal, points);
	}
	
	private void logBalFail(String message, double expAmt, double expBal, double actAmt, double actBal, int points) {
		logFail(message, "Exp withdraw: " + expAmt + ", exp balance: " + expBal,
				"Act withdraw: " + actAmt + ", act balance: " + actBal, points);
	}
	
	public void test01BasicWithdrawChecking() {
		testBasicWithdraw(checkingInstance(100, 5), "checking");
	}
	
	public void test02BasicWithdrawSavings() {
		AbstractSavingsAccount savings = savingsInstance(100, 5);
		savings.setMaxMonthlyTransactions(100);
		testBasicWithdraw(savings, "savings");
	}
	
	private void testBasicWithdraw(Account account, String accountType) {
		int points = 2;
		// legal withdraw
		double expAmt = 30, expBal = 70;
		double actAmt = account.withdraw(expAmt);
		double actBal = account.getBalance();
		String message = accountType + " standard withdraw";
		if(MathUtil.equals(actAmt, expAmt, DELTA) && MathUtil.equals(actBal, expBal, DELTA)) {
			totalPoints += points;
			logPass(message);
		} else {
			continueTesting = false;
			logFail(message + " - testing aborted", expAmt + " withdrawn -> " + expBal + " balance", actAmt + " withdrawn -> " + actBal + " balance", points);
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
