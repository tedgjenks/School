package edu.banking.hines.jonathan;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount 
{
	public CheckingAccount() 
	{
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(double balance, double aPR)
	{
		super(balance, aPR);
	}
	
	@Override
	public void payInterest(int days) 
	{
		if(getBalance() > 0.0)
		{
			setBalance(getBalance() * Math.pow(Math.E, (getAccountInterestAPR()/ 100.0) * (double) days/DAYS_IN_A_YEAR));
		}
	}
	
	@Override
	public double withdraw(double withdrawalAmount) 
	{				
		if (getBalance() >= withdrawalAmount)
		{
			setBalance(getBalance() - withdrawalAmount);
			return withdrawalAmount;
		}
		
		else if (getLinkedSavingsAccount() != null && getLinkedSavingsAccount().canTransact())
		{
			double accountsAmt = getLinkedSavingsAccount().getBalance() +  this.getBalance();
			if(withdrawalAmount <= accountsAmt)
			{
				setBalance(0.0);
				getLinkedSavingsAccount().setBalance(accountsAmt - withdrawalAmount);
				return withdrawalAmount;
			}
			else if(this.isOverdraftProtected() && withdrawalAmount <= accountsAmt + getOverdraftMax())
			{
				double overdraftAmt = (accountsAmt - withdrawalAmount);
				setBalance(overdraftAmt);
				getLinkedSavingsAccount().setBalance(0.0);
				setNumberOverdrafts(getNumberOverdrafts() + 1);
				return withdrawalAmount;
			}
			else 
				return 0.0;
		}
		else if (getLinkedSavingsAccount() == null || this.getLinkedSavingsAccount().canTransact() == false)
		{
			if(this.isOverdraftProtected() && withdrawalAmount <= getBalance() + getOverdraftMax() )
			{
				setBalance(getBalance() - withdrawalAmount);
				setNumberOverdrafts(getNumberOverdrafts() + 1);
				return withdrawalAmount;
			}
			else
				return 0.0;
		}
		else 
			return 0.0;
	}

	@Override
	public double transfer(Account requestedAccount, double amount) 
	{
		if (getBalance() >= amount && canTransact())
		{
			setBalance(getBalance() - amount);
			requestedAccount.deposit(amount);
			return amount;
		}
		else
			return 0.0;
	}	
	public static void main(String[] arg0)
	{
		System.out.println("Don't let your dreams be dreams! JUST DO IT!!!");
	}
}
