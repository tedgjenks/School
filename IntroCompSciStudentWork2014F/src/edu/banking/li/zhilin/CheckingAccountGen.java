package edu.banking.li.zhilin;

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
	public double deposit(double money) {
		double balance = getBalance() + money;
		setBalance(balance);
		return(money);
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		return 0;
	}

	@Override
	public double withdraw(double money) {
		if(money >= getBalance()){
			money = 0;
		}
		double balance = getBalance() - money;
		setBalance(balance);
		return(money);
	}

}
