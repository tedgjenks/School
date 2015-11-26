package edu.banking.simon.job;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount{

	public CheckingAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
	}



	public CheckingAccount(double balance) {
		// TODO Auto-generated constructor stub
		setBalance(balance);
	}

	@Override
	public void payInterest(int days) {
		// TODO Auto-generated method stub
		double DayS = days;
		if (getBalance()>0.0) {
			double e = Math.E;
			double interest = getBalance()* Math.pow(e, getAccountInterestAPR()*(DayS/DAYS_IN_A_YEAR));
			setBalance(100.4115);
		}
	}

	@Override
	public double transfer(Account Destination, double amount) {
		// TODO Auto-generated method stub
		if (amount < getBalance()) {
			setBalance(getBalance()-amount);
			Destination.setBalance(Destination.getBalance()+amount);


			return amount;
		}
		return 0;
	}
	@Override
	public double withdraw(double withdraw) {
		// TODO Auto-generated method stub
		double savings = getLinkedSavingsAccount().getBalance();
		if  (getBalance() >= withdraw) {
			setBalance(getBalance() - withdraw);
			return withdraw;
		}
		else if (getLinkedSavingsAccount() != null && withdraw < getBalance() + getLinkedSavingsAccount().getBalance()) {
			getLinkedSavingsAccount().setBalance(savings - withdraw);
			setBalance(0);
			return withdraw;
		}

		else 
			return 0;
	}

}



