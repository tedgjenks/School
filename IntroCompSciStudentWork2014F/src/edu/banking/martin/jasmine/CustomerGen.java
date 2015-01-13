package edu.banking.martin.jasmine;

import edu.jenks.dist.banking.*;

public class CustomerGen implements Customer {
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
	private String name;
	
	public CustomerGen() {
		// TODO Auto-generated constructor stub
		this.name=name;
	}

	@Override
	public void addCheckingAccount(CheckingAccount checkingAccount) {
		// TODO Auto-generated method stub
		this.checkingAccount = checkingAccount;
	}

	@Override
	public void addSavingsAccount(SavingsAccount savingsAccount) {
		// TODO Auto-generated method stub
		this.savingsAccount = savingsAccount;
	}

	@Override
	public CheckingAccount getCheckingAccount() {
		// TODO Auto-generated method stub
		return checkingAccount;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public SavingsAccount getSavingsAccount() {
		// TODO Auto-generated method stub
		return savingsAccount;
	}

	@Override
	public void setName(String arg0) {
		// TODO Auto-generated method stub
		this.name = name;
	}

}
