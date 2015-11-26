package edu.banking.johnson.tatum;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer (String name){
		super(name);
	}
	
	public Customer(String name , AbstractCheckingAccount checkingAccount , AbstractSavingsAccount savingsAccount) {
		super(name , checkingAccount , savingsAccount);
	}

	@Override
	public int compareTo(AbstractCustomer arg0) {
		return this.getName().compareTo(arg0.getName());
	}

	@Override
	public String toString() {
		return getName();
	}
	

}
