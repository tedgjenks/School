package edu.banking.shearer.richard;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount() {
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void payInterest(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double harry) {
		if (harry <= getBalance())
		{
		
		setBalance(getBalance() - harry);
		return harry;
		
		}
	else return 0.0;

	}

}
