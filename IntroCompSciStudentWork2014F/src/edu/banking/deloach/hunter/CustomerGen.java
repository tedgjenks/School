package edu.banking.deloach.hunter;

import edu.jenks.dist.banking.CheckingAccount;
import edu.jenks.dist.banking.Customer;
import edu.jenks.dist.banking.SavingsAccount;

public class CustomerGen implements Customer {
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
	private String name;
	
	public CustomerGen(String name) {
		this.name = name;
	}

	@Override
	public void addCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount= checkingAccount;

	}

	@Override
	public void addSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount= savingsAccount;

	}

	@Override
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	@Override
	public void setName(String name) {
		this.name=name;

	}

}
