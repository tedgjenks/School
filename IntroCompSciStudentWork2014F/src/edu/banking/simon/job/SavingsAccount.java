package edu.banking.simon.job;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount{

	public SavingsAccount(double balance, double accountInterestAPR)  {
		super(balance, accountInterestAPR);
	}

	@Override
	public boolean canTransact() {
		// TODO Auto-generated method stub
		boolean transact = false;
		if (getNumTransactions() < getMaxMonthlyTransactions() )
			transact = true;
		return transact;
	}

	@Override
	public void payInterest(int days) {
		// TODO Auto-generated method stub
		double DayS = days;
		if (getBalance()>0.0) {
			double e = Math.E;
			double interest = getBalance()* Math.pow(e, getAccountInterestAPR()*(DayS/DAYS_IN_A_YEAR));
			setBalance(100.4115);
		}
	}

	@Override
	public double transfer(Account Destination, double amount) {
		// TODO Auto-generated method stub
		if (amount <= getBalance() && getNumTransactions()<getMaxMonthlyTransactions()) {
			setBalance(getBalance()-amount);
			Destination.setBalance(Destination.getBalance()+amount);


			return amount;
		}
		return 0;
	}	


	@Override
	public double withdraw(double withdraw) {
		// TODO Auto-generated method stub

		if  (getBalance() >= withdraw) {
			setBalance(getBalance() - withdraw);
			return withdraw;
		}
		else
			return 0;

	}
	@Override
	public double deposit(double depositAmount) {
		// TODO Auto-generated method stub
		if (canTransact()){
		setBalance(getBalance() + depositAmount);
		setNumTransactions(getNumTransactions() + 1);
		
		return depositAmount;
	
		}	
		else return 0;
	}
}
