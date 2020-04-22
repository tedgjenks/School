package edu.banking.savelyev.denis;

import edu.jenks.dist.banking.*;

public class SavingsAccount extends AbstractSavingsAccount{
	
	public static void main(String[] args) {
		AbstractSavingsAccount test1 = new SavingsAccount(250.0, 0.02);
		AbstractCheckingAccount test2 = new CheckingAccount(50.0, 0.02);
		System.out.println();

	}
	
	public SavingsAccount() {
		super();
	}
	
	public SavingsAccount(double balance, double APR) {
		setBalance(balance);
		setAccountInterestAPR(APR);
	}

	public boolean canTransact() {
		if(getNumTransactions() <= getMaxMonthlyTransactions()) {
			return true;
		}
		return false;
	}

	public void payInterest(int days) {
		double years = days / DAYS_IN_A_YEAR;
		setBalance(getBalance() * Math.pow(Math.E, getAccountInterestAPR() * years));
	}

	public double transfer(Account destination, double amount) {
		if(canTransact()) {
			if (getBalance() >= amount) {
				setBalance(getBalance() - amount);
				destination.setBalance(destination.getBalance() + amount);
				setNumTransactions(getNumTransactions() + 1);
				return amount;
			} else {
				return 0.0;
			}
		} else {
			return 0.0;
		}
	}

	public double withdraw(double amount) {
		if (getBalance() < amount) {
			return 0.0;
		}
		setBalance(getBalance() - amount);
		return amount;
	}

}
