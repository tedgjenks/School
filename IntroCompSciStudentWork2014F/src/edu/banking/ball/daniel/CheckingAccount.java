package edu.banking.ball.daniel;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount 
{
	double myBalance;
	double myAccountAPR;

	public CheckingAccount()
	{

	}
	public CheckingAccount(double balance, double accountInterestAPR)
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
			double lol = ((this.getBalance()) * (Math.pow(Math.E, ((this.getAccountInterestAPR()/100) * (((double) days)/DAYS_IN_A_YEAR)))));
			this.setBalance(lol);
		}
	}

	@Override
	public double withdraw(double withdrawn) 
	{
		if (this.getBalance() >= withdrawn)
		{
			this.setBalance(getBalance() - withdrawn);
			return withdrawn;
		}
		else if (this.getLinkedSavingsAccount() != null && this.getLinkedSavingsAccount().canTransact())
		{
			double combined = this.getBalance() + this.getLinkedSavingsAccount().getBalance();
			if (combined >= withdrawn)
			{
				this.setBalance(0.0);
				this.getLinkedSavingsAccount().setBalance(combined - withdrawn);
				return withdrawn;
			}
			else if (this.isOverdraftProtected() && (combined + this.getOverdraftMax() >= withdrawn))
			{
				double combinedWithdrawn = combined - withdrawn;
				this.setBalance(combinedWithdrawn);
				this.getLinkedSavingsAccount().setBalance(0.0);
				this.setNumberOverdrafts(this.getNumberOverdrafts() + 1);
				return withdrawn;
			}
			else return 0.0;
		}
		else if (this.getLinkedSavingsAccount() == null || this.getLinkedSavingsAccount().canTransact() == false)
		{
			if (this.isOverdraftProtected() && this.getBalance() + this.getOverdraftMax() >= withdrawn)
			{
				this.setBalance(this.getBalance() - withdrawn);
				this.setNumberOverdrafts(this.getNumberOverdrafts() + 1);
				return withdrawn;
			}
			else
			{
				return 0.0;
			}
		}
		else
		{
			return 0.0;
		}
	}

	

	@Override
	public double transfer(Account destination, double amount) 
	{
		if (this.getBalance() < amount)
		{
			amount = 0;
		}
		else if (destination.canTransact() == true);
		{
				destination.deposit(amount);
				this.setBalance(this.getBalance()-amount);
		}
		return amount;
	}

}
