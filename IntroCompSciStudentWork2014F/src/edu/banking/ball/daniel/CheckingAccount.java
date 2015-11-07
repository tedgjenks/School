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
		System.out.println("Account balance: " + myBalance);
		myAccountAPR = accountInterestAPR;
		System.out.println("Account APR: " + myAccountAPR);
	}
	
	@Override
	public void payInterest(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public double withdraw(double withdrawn) 
	{
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
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
