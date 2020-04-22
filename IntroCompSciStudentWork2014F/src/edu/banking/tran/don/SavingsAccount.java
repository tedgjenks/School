package edu.banking.tran.don;

import edu.jenks.dist.banking.*;

public class SavingsAccount extends AbstractSavingsAccount {
	double totBalance;
	double yearlyRate;

	public SavingsAccount(double balance, double accountInterestAPR) {
		this.setBalance(balance);
		this.setAccountInterestAPR(accountInterestAPR);
		totBalance = balance;
		yearlyRate = accountInterestAPR;
	}

	public boolean canTransact() {
		if(this.getNumTransactions() >= this.getMaxMonthlyTransactions())
			return false;
		return true;
	}

	public void payInterest(int days) {
		this.setBalance(this.getBalance()* Math.exp((this.getAccountInterestAPR()/100.0) * (days / DAYS_IN_A_YEAR)));
	}

	public double transfer(Account dest, double amount) {
		if(this.getBalance() < amount) 
			return 0.0;
		if(!this.canTransact())
			return 0.0;
		this.setNumTransactions(getNumTransactions() + 1); 
		this.setBalance(((double) this.getBalance() - (double) amount));
		dest.setBalance(((double) dest.getBalance() + (double) amount));
		return amount;
	}

	public double deposit(double depositAmount) {
		if(this.getNumTransactions() >= this.getMaxMonthlyTransactions()) {
			return 0.0;
		}
		this.setBalance(this.getBalance() + depositAmount);
		this.setNumTransactions(getNumTransactions() + 1); 
		return depositAmount;
	}

	public double withdraw(double requestedWithdrawl) {
		if (this.getBalance() < requestedWithdrawl) {
			return 0.0;
		}
		if (this.getNumTransactions() >= this.getMaxMonthlyTransactions()) {
			return 0.0;
		}
		this.setNumTransactions(getNumTransactions() + 1); 
		this.setBalance(this.getBalance() - requestedWithdrawl);
		return requestedWithdrawl;
	}

}
