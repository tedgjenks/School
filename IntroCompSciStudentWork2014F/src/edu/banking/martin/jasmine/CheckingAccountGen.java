package edu.banking.martin.jasmine;

import edu.jenks.dist.banking.*;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		// TODO Auto-generated constructor stub
	}

	public CheckingAccountGen(double balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double deposit(double amount) {
		// TODO Auto-generated method stub
		setBalance(getBalance() + amount);
		return amount;
	}

	@Override
	public double transfer(double amount, Account targetAccount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double amount) {
		// TODO Auto-generated method stub
		if(getBalance() < amount){
			return 0;
		}
		setBalance(getBalance()- amount);
		return amount;
	
	}

}
