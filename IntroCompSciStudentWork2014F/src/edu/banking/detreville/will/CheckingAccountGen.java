package edu.banking.detreville.will;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		
	}

	public CheckingAccountGen(double balance) {
		super(balance);
	}

	@Override
	public double deposit(double amountDeposited) {
		double newBalance = getBalance()+ amountDeposited;
		setBalance(newBalance);
		return amountDeposited;
	}

	@Override
	public double transfer(double arg0, Account arg1) {

		return 0;
	}

	@Override
	public double withdraw(double amountWithdrawn) {
		double newBalance = getBalance()- amountWithdrawn;
		setBalance(newBalance);
		return amountWithdrawn;
	}

}
