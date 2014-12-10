package edu.banking.medlock.robert;

import edu.jenks.dist.banking.CheckingAccount;
import edu.jenks.dist.banking.Customer;
import edu.jenks.dist.banking.SavingsAccount;

public class CustomerGen implements Customer {
	private CheckingAccount checkingAccount;
	private SavingsAccount	savingsAccount;
	private String Name;

	public CustomerGen(String Name) {
		this.Name = Name;
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
		return Name;
	}

	@Override
	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	@Override
	public void setName(String Name) {
		this.Name = Name;

	}



}
