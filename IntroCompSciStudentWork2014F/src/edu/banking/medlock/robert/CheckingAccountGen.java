package edu.banking.medlock.robert;

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
	public double withdraw(double deposit) {
		double balance = getBalance() - deposit;
		setBalance(balance);		
		if (balance >= 0 ) {
			return (deposit);
		} else { 
			setBalance(balance + deposit); 
			System.out.println("There isn't enough money in your account");
			return (0);
			
			
		}
		
	}




}
