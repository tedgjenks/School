package edu.banking.grenci.david;

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
	public double deposit(double gain) {
		double newbalance = getBalance() + gain;
		setBalance(newbalance);
		return gain;
	}
	
	@Override
	public double transfer(double arg0, Account arg1) {
		return 0;
	}
	
	@Override
	public double withdraw(double take) {
		if (take > getBalance()){
			take = 0;
		}	
		double newbal = getBalance() - take;
		setBalance(newbal);
		return take;
	}
	
	}
