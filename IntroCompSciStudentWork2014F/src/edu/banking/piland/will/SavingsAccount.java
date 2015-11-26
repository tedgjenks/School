package edu.banking.piland.will;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {


	public SavingsAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}

	@Override
	public boolean canTransact() {
		boolean transact = false;
		if(getNumTransactions() < getMaxMonthlyTransactions()){
			transact = true;
		}
		return transact;
	}

	@Override
	public void payInterest(int days) {
		// TODO Auto-generated method stub
		double e = Math.E;
		double accountInterest =  getAccountInterestAPR();
		double amountToAdd;
		amountToAdd = this.getBalance()*Math.pow(e, (accountInterest/100) * (days/DAYS_IN_A_YEAR));
		this.setBalance(amountToAdd);
	}

	@Override
	public double transfer(Account destination, double amount) {
		double amountToWithDrawn = 0;
		if(amount <= this.getBalance()){
			amountToWithDrawn = amount;

		}
		this.setBalance(this.getBalance() - amountToWithDrawn);
		destination.setBalance(destination.getBalance() + amountToWithDrawn);
		return amountToWithDrawn;
	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		// TODO Auto-generated method stub
		double amountWithdrawn = 0;

		if(requestedWithdrawal > getBalance()){
			System.out.println("Not Enough Money In Savings Account");
		}
		if(requestedWithdrawal <= getBalance())
			if(getNumTransactions() <= getMaxMonthlyTransactions())
				amountWithdrawn = requestedWithdrawal;
		double newbalance = getBalance() - amountWithdrawn;
		setBalance(newbalance);
		int monthlyTransactions = getNumTransactions() +1;
		setNumTransactions(monthlyTransactions);
		return amountWithdrawn;
	}

}
