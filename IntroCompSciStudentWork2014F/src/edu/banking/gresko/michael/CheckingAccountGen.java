package edu.banking.gresko.michael;

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
		double balance = getBalance() + amount;
		setBalance(balance);
		return(amount);
	}

	@Override
	public double transfer(double amount, Account targetAccount) {
		return 0;
	}

	@Override
	public double withdraw(double amount) {
		double balance = getBalance() - amount;
		setBalance(balance);
		if (balance >= 0){
			return(amount);
		} else {
			setBalance(balance + amount);
			System.out.println("Transaction cannot be carried out.  You only have $" + getBalance() + " in your account.");
			return(0);
		}	
	}

}
