package edu.banking.hines.jonathan;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount 
{

	public SavingsAccount() 
	{
		// TODO Auto-generated constructor stub
	}
	public SavingsAccount(double balance)
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
	public boolean canTransact() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
