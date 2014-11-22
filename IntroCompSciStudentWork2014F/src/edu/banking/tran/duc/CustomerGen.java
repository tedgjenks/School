package edu.banking.tran.duc;

import edu.jenks.dist.banking.CheckingAccount;
import edu.jenks.dist.banking.Customer;
import edu.jenks.dist.banking.SavingsAccount;

public class CustomerGen implements Customer {
	private CheckingAccount checkingaccount;
	private SavingsAccount savingaccount;
	private String name;
	
	public CustomerGen(String name) {
		this.name = name;
	}
	
	@Override
	public void addCheckingAccount(CheckingAccount checkingaccount) {
		this.checkingaccount = checkingaccount;
	}

	@Override
	public void addSavingsAccount(SavingsAccount savingaccount) {
		this.savingaccount = savingaccount;
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
		return savingaccount;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
