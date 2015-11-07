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
		System.out.println("Account balance: " + myBalance);
		myAccountAPR = accountInterestAPR;
		System.out.println("Account APR: " + myAccountAPR);
	}

		
	@Override
	public void payInterest(int arg0) 
	{

	}

	@Override
	public double withdraw(double withdrawn) 
	{
		System.out.println("Attempting account withdrawal...");
		System.out.println("Withdrawal ammount: " + withdrawn);
		double returnWithdrawn = 0.0;
		if ((myBalance - withdrawn) > 0)
			{
				returnWithdrawn = withdrawn;
				myBalance -= withdrawn;
				System.out.println("Amount withdrawn from account: " + returnWithdrawn);
				System.out.println("Current account balance: " + myBalance);
				return returnWithdrawn;
			}
		else
			{
				returnWithdrawn = 0.0;
				System.out.println("Invalid transaction, no money withdrawn.");
				System.out.println("Current account balance: " + myBalance);
				return returnWithdrawn;
			}
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
