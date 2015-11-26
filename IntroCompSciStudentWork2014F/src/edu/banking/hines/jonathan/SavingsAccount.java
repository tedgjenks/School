package edu.banking.hines.jonathan;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount 
{
	public SavingsAccount() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public SavingsAccount(double balance, double aPR)
	{
		super(balance, aPR);
	}
	
	@Override
	public void payInterest(int days) 
	{
		if(getBalance() > 0.0)
		{
			setBalance(getBalance() * Math.pow(Math.E, (getAccountInterestAPR() / 100.0) * (double) days/DAYS_IN_A_YEAR));
		}
	}

	@Override
	public double withdraw(double withdrawalAmount) 
	{
		if(canTransact())
		{
			if (getBalance() >= withdrawalAmount)
			{
				setNumTransactions(getNumTransactions() + 1);
				setBalance(getBalance() - withdrawalAmount);
				return withdrawalAmount;
			}
		}
		return 0.0  ;
	}
	
	@Override
	public boolean canTransact() 
	{
		return getNumTransactions() < getMaxMonthlyTransactions();
	}
	
	@Override
	public double transfer(Account requestedAccount , double amount) 
	{
		if (getBalance() >= amount && canTransact())
		{
			setNumTransactions(getNumTransactions() + 1);
			setBalance(getBalance() - amount);
			requestedAccount.deposit(amount);
			return amount;
		}
		else
			return 0.0;
	}
	
	public double deposit(double amount)
	{
		if(canTransact())
		{
			setBalance(getBalance() + amount);
			setNumTransactions(getNumTransactions() + 1);
			return amount;     
		}
		return 0.0;
	}
}


