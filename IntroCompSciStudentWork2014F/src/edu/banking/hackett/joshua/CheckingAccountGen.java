package edu.banking.hackett.joshua;

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
	public double deposit(double depo) {
		double newB = getBalance() + depo;
		setBalance(newB);
		return depo;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double wd) {
		if (wd > getBalance())
			wd = 0;
		double newb = getBalance() - wd;
		setBalance(newb);
		return wd;
	}

}
