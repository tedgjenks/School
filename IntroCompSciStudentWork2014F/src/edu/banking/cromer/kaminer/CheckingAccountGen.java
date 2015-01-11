package edu.banking.cromer.kaminer;

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
	public double deposit(double deposit) {
		double balance = getBalance() + deposit;
		setBalance(balance);
		return deposit;
	}

	@Override
	public double transfer(double transfer, Account balance) {
		return 0;
	}

	@Override
	public double withdraw(double amount) {
		double balance = getBalance() - amount;
		setBalance(balance);
		if (balance>0){
			return (amount);
		}else{
			setBalance(balance + amount);
			System.out.println("can't do it");
			return (0);
		}
	}
}
