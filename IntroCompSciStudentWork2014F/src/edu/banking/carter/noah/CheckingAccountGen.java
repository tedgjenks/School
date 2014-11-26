package edu.banking.carter.noah;

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
	public double deposit(double amount) {
		setBalance(getBalance() + amount);
		return amount;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double amount) {
		if (getBalance() > amount){
			 setBalance(getBalance() - amount);
		}else if (getBalance() < amount){
			amount = 0;
		}else if(getBalance() == amount){
			amount = getBalance();
		}
		return amount;
	}

}
