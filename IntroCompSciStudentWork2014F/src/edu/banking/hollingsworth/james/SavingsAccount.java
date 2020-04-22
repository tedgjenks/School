package edu.banking.hollingsworth.james;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {
	
	public SavingsAccount() {}
	
	public SavingsAccount(double balance, double accountInterestAPR)  {
		this.setBalance(balance);
		this.setAccountInterestAPR(accountInterestAPR);
	}

	@Override
	public boolean canTransact() {
		return this.getNumTransactions() < this.getMaxMonthlyTransactions() && this.getBalance() > 0;
	}

	@Override
	public void payInterest(int days) {
		setBalance(getBalance() * Math.pow(Math.E, ((getAccountInterestAPR() / 100.0) * ((double) days / DAYS_IN_A_YEAR))));
	}

	@Override
	public double transfer(Account acc, double amount) {
		if(this.getBalance() >= amount && this.canTransact()) {
			this.setBalance(this.getBalance() - amount);
			this.setNumTransactions(this.getNumTransactions() + 1);
			acc.deposit(amount);
			return amount;
		}
		else return 0;
	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		if(this.canTransact() && this.getBalance() >= requestedWithdrawal) {
			this.setNumTransactions(this.getNumTransactions() + 1);
			this.setBalance(this.getBalance() - requestedWithdrawal);
			return requestedWithdrawal;
		}
		return 0.0;
	}

}
