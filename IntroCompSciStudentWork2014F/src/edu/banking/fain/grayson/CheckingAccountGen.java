package edu.banking.fain.grayson;

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
	public double deposit(double amtD) {
		setBalance(getBalance()+amtD);
		return amtD;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double amtW) {
		double withdrawN = getBalance() - amtW;
		setBalance(withdrawN);
		return amtW;
	}

}
	

