package edu.banking.higginbotham.andrew;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {

	public SavingsAccount(){

	}

	public SavingsAccount(double balance, double APR)
	{
		super(balance, APR);
	}

	@Override
	public void payInterest(int days) {
		double dayS = days;
		if(getBalance() > 0.0)
		{
			double e = Math.E;
			double interest = getBalance() * Math.pow(e, (getAccountInterestAPR()/100.0)* (dayS/DAYS_IN_A_YEAR));
			setBalance(interest);
		}
	}

	@Override
	public double withdraw(double requestedAmount) {
		double withdrawed = requestedAmount;
		if(getBalance() >= withdrawed && this.canTransact())
		{
			setBalance(getBalance() - requestedAmount);
			setNumTransactions(getNumTransactions() + 1);
			return withdrawed;
		}
		else return 0.0;

	}

	@Override
	public boolean canTransact() {
		
		return getNumTransactions() < getMaxMonthlyTransactions();
	}

	@Override
	public double transfer(Account destination, double requestedTransfer) {
		if(getBalance() >= requestedTransfer && canTransact()){
			setBalance(getBalance() - requestedTransfer);
			destination.deposit(requestedTransfer);
			setNumTransactions(getNumTransactions() + 1);
			return requestedTransfer;
		}
		else return 0.0;
	}

	@Override
	public double deposit(double amount){
		if(canTransact()){
			setBalance(getBalance() + amount);
			setNumTransactions(getNumTransactions() + 1);
			return amount;
		}
		else return 0;
	}

}
