package edu.banking.carter.noah;

import edu.jenks.dist.banking.CheckingAccount;
import edu.jenks.dist.banking.Customer;
import edu.jenks.dist.banking.SavingsAccount;

public class CustomerGen implements Customer {
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
	private String name;
	
	public CustomerGen() {
		
		
	}
	public CustomerGen(String name){
		this.name = name;
	}
	@Override
	public void addCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	@Override
	public void addSavingsAccount(SavingsAccount arg0) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String arg0) {
		this.name = name;

	}

}
