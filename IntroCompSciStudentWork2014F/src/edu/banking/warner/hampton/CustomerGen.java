package edu.banking.warner.hampton;

import edu.jenks.dist.banking.CheckingAccount;
import edu.jenks.dist.banking.Customer;
import edu.jenks.dist.banking.SavingsAccount;

public class CustomerGen implements Customer {
	private CheckingAccount checkingaccount;
	private String name;
	private SavingsAccount savingsaccount;
	

	public CustomerGen(String name) {
		this.name = name;
	}

	@Override
	public void addCheckingAccount(CheckingAccount checkingaccount) {
		this.checkingaccount = checkingaccount;

	}

	@Override
	public void addSavingsAccount(SavingsAccount savingsaccount) {
		this.savingsaccount = savingsaccount;

	}

	@Override
	public CheckingAccount getCheckingAccount() {
		return checkingaccount;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public SavingsAccount getSavingsAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String arg0) {
		

	}

}
