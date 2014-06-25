package edu.jenks.banking.test;

import java.util.logging.Level;

import edu.jenks.banking.dist.AbstractBanking;
import edu.jenks.banking.dist.AbstractCheckingAccount;
import edu.jenks.banking.dist.Account;
import edu.jenks.test.TestPackageList;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

public class BankingTest extends Testable {
	
	public static final BankingTest SINGLETON = new BankingTest();
	public static final int BANKING_FEE = 25;
	public static final int MIN_NO_FEE_BALANCE = 5000;
	public static final double BANKING_DELTA = 0.005;
	public static final int OVERDRAFT_MAX = 500;
	public static final double CHECKING_INTEREST_APR = 0.1;
	public static final double SAVINGS_INTEREST_APR = 2.3;
	public static final int OVERDRAFT_FEE = 50;
	
	private AbstractBanking studentBanking;
	private AbstractCheckingAccount studentCheckingAccount;
	private Account studentSavingsAccount;

	public static void main(String[] args) {
		System.out.println("Begin main");
		TestPackageList.testPackages(SINGLETON, SINGLETON.getLogFilePath());
		System.out.println("End main");
	}
	
	public boolean init(double checkingBalance, double savingsBalance) {
		boolean initialized = true;
		try {
			studentBanking = (AbstractBanking)ReflectionUtil.newInstance(studentPackage + ".Banking");
			if(checkingBalance >= 0) {
				studentCheckingAccount = (AbstractCheckingAccount)ReflectionUtil.newInstance(studentPackage + ".CheckingAccount");
				studentCheckingAccount.setBalance(checkingBalance);
				studentCheckingAccount.setOverdraftMax(OVERDRAFT_MAX);
				studentCheckingAccount.setAccountInterestAPR(CHECKING_INTEREST_APR);
				studentBanking.setCheckingAccount(studentCheckingAccount);
			}
			if(savingsBalance >= 0) {
				studentSavingsAccount = (Account)ReflectionUtil.newInstance(studentPackage + ".SavingsAccount");
				studentSavingsAccount.setBalance(savingsBalance);
				studentSavingsAccount.setAccountInterestAPR(SAVINGS_INTEREST_APR);
				studentBanking.setSavingsAccount(studentSavingsAccount);
			}
			studentBanking.setBankingFee(BANKING_FEE);
			studentBanking.setMinNoFeeCombinedBalance(MIN_NO_FEE_BALANCE);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			initialized = false;
			logger.log(Level.WARNING, e.getMessage());
		}
		return initialized;
	}
	
	/*@Override
	public String[] getTestMethods() {
		String[] testMethods = {"testDeposit", "testWithdrawal", "testNoOverdraft", "testLinkedSavings",
				"testOverdraft", "testInterest", "testFeeNoOverdraft", "testFeeWithOverdraft"};
		return testMethods;
	}*/
	
	public void test() {
		try {
			totalPoints += testDeposit();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		try {
			totalPoints += testWithdrawal();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		try {
			totalPoints += testNoOverdraft(); // two points possible
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		try {
			totalPoints += testLinkedSavings();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		int points = 0;
		try {
			points = testOverdraft();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		boolean passOverdraft = points > 0;
		totalPoints += points;
		try {
			points = testInterest();
		} catch(Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		boolean passInterest = points > 0;
		totalPoints += points;
		if(passInterest) {
			try {
				totalPoints += testFeeNoOverdraft(); // two points possible
			} catch(Exception e) {
				logger.log(Level.WARNING, e.getMessage());
			}
		} else
			logger.log(Level.INFO, "Fee with no overdraft not tested (2 points) because interest failed.");
		if(passOverdraft) {
			try {
				totalPoints += testFeeWithOverdraft();
			} catch(Exception e) {
				logger.log(Level.WARNING, e.getMessage());
			}
		} else
			logger.log(Level.INFO, "Fees with overdraft not tested because overdraft failed.");
	}
	
	private int testFeeWithOverdraft() {
		int points = 0;
		if(!init(25, 0))
			return points;
		
		int days = 400;
		studentCheckingAccount.setOverdraftProtected(true);
		studentCheckingAccount.setOverdraftFee(OVERDRAFT_FEE);
		int overdrafts = 5;
		for(int count = 0; count < overdrafts; count++)
			studentCheckingAccount.withdraw(50);
		studentCheckingAccount.withdraw(OVERDRAFT_MAX - studentCheckingAccount.getBalance() + 1); // nothing should be withdrawn - max reached
		double expectedCheckingBalance = -225;
		if(MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA) &&
				studentCheckingAccount.getNumberOverdrafts() == overdrafts) {
			studentBanking.performMaintenance(days);
			expectedCheckingBalance -= (overdrafts * OVERDRAFT_FEE + 25);
			if(MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA)) {
				points++;
				logger.log(Level.FINE, "Pass");
			}
		}
		if(points == 0)
			logger.log(Level.INFO, "Fail.  Expected checking balance: " + expectedCheckingBalance + ".  Actual checking balance: " + studentCheckingAccount.getBalance());
		return points;
	}
	
	private int testFeeNoOverdraft() {
		int points = 0;
		if(!init(100, 4000))
			return points;
		
		int days = 400;
		studentBanking.performMaintenance(days);
		double expectedCheckingBalance = 75.082;
		double expectedSavingsBalance = 4102.103;
		if(MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA) &&
				MathUtil.equals(expectedSavingsBalance, studentSavingsAccount.getBalance(), BANKING_DELTA)) {
			points++;
			logger.log(Level.FINE, "Pass part1");
		} else
			logger.log(Level.INFO, "Fail part1.  Expected checking balance: " + expectedCheckingBalance + ".  Actual checking balance: " + studentCheckingAccount.getBalance() + 
					".  Expected savings balance: " + expectedSavingsBalance + ".  Actual savings balance: " + studentSavingsAccount.getBalance());
		
		if(!init(5, 10))
			return points;
		
		studentBanking.performMaintenance(days);
		expectedCheckingBalance = -10;
		expectedSavingsBalance = 0;
		if(MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA) &&
				MathUtil.equals(expectedSavingsBalance, studentSavingsAccount.getBalance(), BANKING_DELTA)) {
			points++;
			logger.log(Level.FINE, "Pass part2");
		} else
			logger.log(Level.INFO, "Fail part2.  Expected checking balance: " + expectedCheckingBalance + ".  Actual checking balance: " + studentCheckingAccount.getBalance() + 
					".  Expected savings balance: " + expectedSavingsBalance + ".  Actual savings balance: " + studentSavingsAccount.getBalance());
		return points;
	}
	
	private int testInterest() {
		int points = 0;
		if(!init(2000, 4000))
			return points;
		
		int days = 400;
		studentBanking.performMaintenance(days);
		double expectedCheckingBalance = 2002.193;
		double expectedSavingsBalance = 4102.103;
		if(MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA) &&
				MathUtil.equals(expectedSavingsBalance, studentSavingsAccount.getBalance(), BANKING_DELTA)) {
			points++;
			logger.log(Level.FINE, "Pass");
		} else
			logger.log(Level.INFO, "Fail.  Expected checking balance: " + expectedCheckingBalance + ".  Actual checking balance: " + studentCheckingAccount.getBalance() + 
					".  Expected savings balance: " + expectedSavingsBalance + ".  Actual savings balance: " + studentSavingsAccount.getBalance());
		return points;
	}
	
	private int testOverdraft() {
		int points = 0;
		if(!init(100, 200))
			return points;
		
		studentCheckingAccount.setOverdraftProtected(true);
		int requestedWithdrawal = 500;
		double expectedSavingsBalance = 0;
		double expectedCheckingBalance = -200;
		int expectedOverdrafts = 1;
		double withdrawAmt = studentCheckingAccount.withdraw(requestedWithdrawal);
		if(!MathUtil.equals(requestedWithdrawal, withdrawAmt, BANKING_DELTA))
			logger.log(Level.INFO, "Fail.  Expected withdrawal: " + requestedWithdrawal + ".  Actual withdrawal: " + withdrawAmt);
		else if(!MathUtil.equals(expectedSavingsBalance, studentSavingsAccount.getBalance(), BANKING_DELTA) ||
				!MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA))
			logger.log(Level.INFO, "Fail.  Expected checking balance: " + expectedCheckingBalance + ".  Actual checking balance: " + studentCheckingAccount.getBalance() + 
					".  Expected savings balance: " + expectedSavingsBalance + ".  Actual savings balance: " + studentSavingsAccount.getBalance());
		else if(studentCheckingAccount.getNumberOverdrafts() != expectedOverdrafts)
			logger.log(Level.INFO, "Fail.  Expected overdrafts: " + expectedOverdrafts + ".  Actual overdrafts: " + studentCheckingAccount.getNumberOverdrafts());
		else {
			points++;
			logger.log(Level.FINE, "Pass");
		}
		return points;
	}
	
	private int testLinkedSavings() {
		int points = 0;
		if(!init(100, 200))
			return points;
		
		int requestedWithdrawal = 200;
		double expectedSavingsBalance = 100;
		double expectedCheckingBalance = 0;
		double withdrawAmt = studentCheckingAccount.withdraw(requestedWithdrawal);
		if(MathUtil.equals(requestedWithdrawal, withdrawAmt, BANKING_DELTA) &&
				MathUtil.equals(expectedSavingsBalance, studentSavingsAccount.getBalance(), BANKING_DELTA) &&
				MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA)) {
			expectedSavingsBalance = 0;
			expectedCheckingBalance = 100;
			withdrawAmt = studentCheckingAccount.withdraw(requestedWithdrawal);
			if(MathUtil.equals(0, withdrawAmt, BANKING_DELTA) &&
					MathUtil.equals(expectedSavingsBalance, studentSavingsAccount.getBalance(), BANKING_DELTA) &&
					MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA)) {
				points++;
				logger.log(Level.FINE, "Pass");
			}
		}
		if(points == 0)
			logger.log(Level.INFO, "Fail");
		return points;
	}
	
	private int testNoOverdraft() {
		int points = 0;
		if(!init(100, 100))
			return points;
		
		double withdrawAmt = studentSavingsAccount.withdraw(200);
		if(MathUtil.equals(0, withdrawAmt, BANKING_DELTA)) {
			points++;
			logger.log(Level.FINE, "Pass (cannot withdraw more than balance from savings)");
		} else
			logger.log(Level.INFO, "Fail (cannot withdraw more than balance from savings).");
		
		if(!init(100, -1))
			return points;
		
		withdrawAmt = studentCheckingAccount.withdraw(200);
		if(MathUtil.equals(0, withdrawAmt, BANKING_DELTA)) {
			points++;
			logger.log(Level.FINE, "Pass (checking with no savings account)");
		} else
			logger.log(Level.INFO, "Fail (checking with no savings account).");
		return points;
	}
	
	private int testWithdrawal() {
		int points = 0;
		int expectedCheckingBalance = 500;
		int expectedSavingsBalance = 100;
		if(!init(expectedCheckingBalance, expectedSavingsBalance))
			return points;
		
		int withdraw = 50;
		if(withdraw == studentCheckingAccount.withdraw(withdraw) && withdraw == studentSavingsAccount.withdraw(withdraw)) {
			expectedCheckingBalance -= withdraw;
			expectedSavingsBalance -= withdraw;
			if(MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA) && MathUtil.equals(expectedSavingsBalance, studentSavingsAccount.getBalance(), BANKING_DELTA)) {
				points++;
				logger.log(Level.FINE, "Pass");
			} else
				logger.log(Level.INFO, "Fail.  Withdraw did not correctly deduct from balance.");
		} else
			logger.log(Level.INFO, "Fail.  Withdraw amount not returned.");
		return points;
	}
	
	private int testDeposit() {
		int points = 0;
		if(!init(0, 0))
			return points;
		
		int expectedCheckingBalance = 500;
		int expectedSavingsBalance = 100;
		studentCheckingAccount.deposit(expectedCheckingBalance);
		studentSavingsAccount.deposit(expectedSavingsBalance);
		if(MathUtil.equals(expectedCheckingBalance, studentCheckingAccount.getBalance(), BANKING_DELTA) && MathUtil.equals(expectedSavingsBalance, studentSavingsAccount.getBalance(), BANKING_DELTA)) {
			points++;
			logger.log(Level.FINE, "Pass");
		} else
			logger.log(Level.INFO, "Fail");
		return points;
	}

	@Override
	public String getLogFilePath() {
		return LOG_FILE_PATH_START + "banking/test/log.txt";
	}

	@Override
	public Testable getSingleton() {
		return SINGLETON;
	}

}
