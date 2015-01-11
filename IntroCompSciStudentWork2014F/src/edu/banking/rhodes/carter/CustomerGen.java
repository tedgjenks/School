package edu.banking.rhodes.carter;

import edu.jenks.dist.banking.CheckingAccount;
import edu.jenks.dist.banking.Customer;
import edu.jenks.dist.banking.SavingsAccount;

public class CustomerGen implements Customer {
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
	private String name;

	public CustomerGen(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public void addCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount=checkingAccount;
	}

	@Override
	public void addSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount=savingsAccount;
	}
	@Override
	public CheckingAccount getCheckingAccount() {
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
	public void setName(String name) {
		this.name = name;

	}

}
