package edu.banking.smith.rod;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CheckingAccountGen() {
	}	

	public CheckingAccountGen(double balance) {
		super(balance);
	}

	@Override
	public double deposit(double depositamount) {
		setBalance(getBalance() + depositamount);
		return depositamount;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		return arg0;
	}

	@Override
	public double withdraw(double withdrawAmount) {
		if (withdrawAmount >= getBalance())
				withdrawAmount = 0;
		setBalance(getBalance() + withdrawAmount);
		return withdrawAmount;
	}

}
