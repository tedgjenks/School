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
		System.out.println("Account balance: " + this.getBalance());
		myAccountAPR = accountInterestAPR;
		this.setAccountInterestAPR(myAccountAPR);
		System.out.println("Account APR: " + this.getAccountInterestAPR());
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
		double returnWithdrawn = 0.0;
		if (this.canTransact())
		{
			//System.out.println("Attempting account withdrawal...");
			//System.out.println("Withdrawal ammount: " + withdrawn);
			if ((myBalance - withdrawn) > 0)
			{
				returnWithdrawn = withdrawn;
				myBalance -= withdrawn;
				this.setBalance(myBalance);
				//System.out.println("Amount withdrawn from account: " + returnWithdrawn);
				//System.out.println("Current account balance: " + myBalance);
				this.setNumTransactions(this.getNumTransactions() + 1);
				//System.out.println("Number of transactions: " + this.getNumTransactions());
				return returnWithdrawn;
			}
			else
			{
				returnWithdrawn = 0.0;
				this.setBalance(myBalance);
				//System.out.println("Invalid transaction, no money withdrawn.");
				//System.out.println("Current account balance: " + myBalance);
				return returnWithdrawn;
			}
		}
		else
		{
			returnWithdrawn = 0.0;
			this.setBalance(myBalance);
			//System.out.println("Invalid, account has reached maximum allowed transactions for this month.");
			//System.out.println("Current account balance: " + myBalance);
			return returnWithdrawn;
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
	{
		return (this.getNumTransactions() < this.getMaxMonthlyTransactions());
	}

	@Override
	public double transfer(Account destination, double amount) 
	{
		if (this.canTransact())
		{
			if (this.getBalance() < amount)
			{
				return 0.0;
			}
			else
			{
				this.withdraw(amount);
				destination.deposit(amount);
				this.setNumTransactions(this.getNumTransactions() + 1);
				return amount;
			}
		}
		else
		{
			return 0.0;
		}
	}
}

