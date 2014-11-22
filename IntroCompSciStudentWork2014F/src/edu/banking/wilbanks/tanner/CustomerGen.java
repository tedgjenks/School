package edu.banking.wilbanks.tanner;

import edu.jenks.dist.banking.CheckingAccount;
import edu.jenks.dist.banking.Customer;
import edu.jenks.dist.banking.SavingsAccount;

public class CustomerGen implements Customer {
	private String name;
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
	
	public CustomerGen() {
		this.name = "John Titus";
	}

	@Override
	public void addCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;

	}

	@Override
	public void addSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;

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
		this.name = name;
	}

}
