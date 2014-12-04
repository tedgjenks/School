package edu.banking.sparks.brigham;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		// TODO Auto-generated constructor stub
	}

	public CheckingAccountGen(double balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double deposit(double amountDeposited) {
		// TODO Auto-generated method stub
		double newBalance = getBalance() + amountDeposited;
		setBalance(newBalance);
		return amountDeposited;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double amountWithdrawn) {
		// TODO Auto-generated method stub
		double newBalance = getBalance() - amountWithdrawn;
		setBalance(newBalance);
		return amountWithdrawn;
	}
}
