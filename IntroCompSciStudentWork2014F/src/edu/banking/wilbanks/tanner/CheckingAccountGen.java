package edu.banking.wilbanks.tanner;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {
	
	public CheckingAccountGen() {
		// TODO Auto-generated constructor stub
	}

	public CheckingAccountGen(double balance) {
		super(balance);
		
	}

	@Override
	public double deposit(double money) {
		setBalance(getBalance() + money);
		return money;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double money) {
		if (money >= getBalance()) {
			money = 0;
		}
		double balance = getBalance() - money;
		setBalance(balance);
		return money;
	}

}
