package edu.banking.higginbotham.andrew;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount(){

	}

	public CheckingAccount(double balance, double APR)
	{
		super(balance, APR);
	}

	@Override
	public void payInterest(int days) {
		if(getBalance() > 0.0)
		{
			double e = Math.E;
			double interest = getBalance() * Math.pow(e, (getAccountInterestAPR()/100.0)* (days/DAYS_IN_A_YEAR));
			setBalance(interest);
		}                          

	}

	@Override
	public double withdraw(double requestedAmount) {
		double withdrawed = requestedAmount;
		if(getBalance() >= requestedAmount)
		{
			setBalance(getBalance() - requestedAmount);
			return withdrawed;
		}
		else if(getLinkedSavingsAccount() != null && getLinkedSavingsAccount().canTransact() && getBalance() + getLinkedSavingsAccount().getBalance() >= requestedAmount)
		{
			double temp = getBalance() - requestedAmount;
			setBalance(0.0);
			getLinkedSavingsAccount().setBalance(getLinkedSavingsAccount().getBalance() + temp);
			getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
			return withdrawed;
		} 
		else if(isOverdraftProtected() && getBalance() + getOverdraftMax() >= requestedAmount){
			setBalance(getBalance() - requestedAmount);
			setNumberOverdrafts(getNumberOverdrafts() + 1);
			return withdrawed;
		}
		else if(getLinkedSavingsAccount() != null && getLinkedSavingsAccount().canTransact() && isOverdraftProtected() && getBalance() + getLinkedSavingsAccount().getBalance() + getOverdraftMax() >= requestedAmount)
		{
			double temp = getBalance() + getLinkedSavingsAccount().getBalance() - requestedAmount;
			setBalance(temp);
			getLinkedSavingsAccount().setBalance(0.0);
			setNumberOverdrafts(getNumberOverdrafts() + 1);
			getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
			return requestedAmount;
		}	
		else
			return 0.0;
	}

	@Override
	public double transfer(Account destination, double requestedTransfer) {
		if(getBalance() >= requestedTransfer){
			setBalance(getBalance() - requestedTransfer);
			destination.deposit(requestedTransfer);
			return requestedTransfer;
		}
		else return 0.0;
	}

}
