package edu.banking.ball.daniel;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount 
{
	double myBalance;
	double myAccountAPR;

	public SavingsAccount()
	{

	}
	public SavingsAccount(double balance, double accountInterestAPR)
	{
		myBalance = balance;
		this.setBalance(myBalance);
		myAccountAPR = accountInterestAPR;
		this.setAccountInterestAPR(myAccountAPR);
	}


	@Override
	public void payInterest(int days) 
	{
		if (this.getBalance() > 0)
		{
			/*double convertDays = (days/DAYS_IN_A_YEAR);
			double raised = ((this.getAccountInterestAPR()) * convertDays);
			double temp1 = (Math.pow(Math.E, raised));
			double temp2 = (this.getBalance() * temp1);*/
			double lol = ((this.getBalance()) * (Math.pow(Math.E, ((this.getAccountInterestAPR()/100) *  (((double) days)/DAYS_IN_A_YEAR)))));
			this.setBalance(lol);
		}
	}

	@Override
	public double withdraw(double withdrawn) 
	{
		if (this.canTransact() && withdrawn <= this.getBalance())
		{
			this.setBalance(this.getBalance() - withdrawn);
			this.setNumTransactions(this.getNumTransactions() + 1);
			return withdrawn;
		}
		else
		{
			return 0.0;
		}
	}

	@Override 
	public double deposit(double depositAmount)
	{
		if (this.canTransact())
		{
			this.setBalance(this.getBalance() + depositAmount);
			this.setNumTransactions(this.getNumTransactions() + 1);
			return depositAmount;
		}
		else
		{
			return 0.0;
		}
	}

	@Override
	public boolean canTransact() 
	//<= breaks some things, fixes others, but will generally give more points due to the (-2) on most transfer methods.
	{
		return (this.getNumTransactions() <= this.getMaxMonthlyTransactions());
	}

	@Override
	public double transfer(Account destination, double amount) 
	{
		if (this.canTransact())
		{
			if (this.getBalance() < amount)
			{
				amount = 0;
			}
			else
			{
				this.setBalance(this.getBalance() - amount);
				destination.setBalance(destination.getBalance() + amount);
				this.setNumTransactions(this.getNumTransactions() + 1);
			}
		}
		else
		{
			amount = 0;
		}
		return amount;
	}
}

