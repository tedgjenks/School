package edu.banking.ramsey.will;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {
	
	public SavingsAccount(double balance, double accountInterestAPR){
		this.setBalance(balance);
		this.setAccountInterestAPR(accountInterestAPR);
	}


	@Override
	public void payInterest(int arg0) {
		// TODO Auto-generated method stub
		double years = arg0/Account.DAYS_IN_A_YEAR;
		double yearlyRate = getAccountInterestAPR() / 100.0;
		setBalance(getBalance() * Math.pow(Math.E,(yearlyRate * years)));
	}

	@Override
	public double withdraw(double arg0) {
		// TODO Auto-generated method stub
		double withdrawn = 0;
		if(getBalance() >= arg0 && canTransact()){
			setBalance(getBalance() - arg0);
			withdrawn = arg0;
			setNumTransactions(getNumTransactions() + 1);
		}
		return withdrawn;
	}

	@Override
	public boolean canTransact() {
		// TODO Auto-generated method stub
		boolean transact = false;
		if(getNumTransactions() < getMaxMonthlyTransactions())
			transact = true;
		return transact;
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		double transfered = 0;
		if(getBalance() >= arg1 && canTransact()){
			transfered = arg1;
			arg0.deposit(arg1);
			setBalance(getBalance() - transfered);
			setNumTransactions(getNumTransactions() + 1);
		}	
		return transfered;
	}
	@Override
	public double deposit(double depositAmount) {
		double deposited = 0;
		if(canTransact()){
			setBalance(getBalance() + depositAmount);
			deposited = depositAmount;
			setNumTransactions(getNumTransactions() + 1);
		}
		return deposited;
		
	}

}
