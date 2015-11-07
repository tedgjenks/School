package edu.banking.hines.jonathan;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount 
{

	public CheckingAccount() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public CheckingAccount(double balance)
	{
		setBalance(balance);
	}
	@Override
	public void payInterest(int arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public double withdraw(double withdrawalAmount) 
	{
		
		if (getBalance() >= withdrawalAmount)
		{
			 setBalance(getBalance() - withdrawalAmount);
			  return withdrawalAmount;
		}
		else
			return 0.0;
		
		
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
