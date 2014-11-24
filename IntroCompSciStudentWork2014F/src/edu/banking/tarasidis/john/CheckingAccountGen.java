package edu.banking.tarasidis.john;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		setBalance(100);
	}

	public CheckingAccountGen(double balance) {
		super(balance);
	}

	@Override
	public double deposit(double arg0) {
		double balance = getBalance();
		double newbalance = balance + arg0;
		setBalance(newbalance);
		return arg0;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		return 0;
	}

	@Override
	public double withdraw(double arg0) {
		double withdrawnum = arg0;
		double balance = getBalance();
		if (arg0 > balance) {
			withdrawnum = 0;
		}
		double newbalance = balance - withdrawnum;
		setBalance(newbalance);
		return arg0;
	}

}
