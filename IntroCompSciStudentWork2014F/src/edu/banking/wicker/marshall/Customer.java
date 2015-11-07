package edu.banking.wicker.marshall;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
	}

	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
	}

	public boolean equals(Customer otherCust) {
		return this.getName().equals(otherCust.getName());
	}

	@Override
	public int compareTo(AbstractCustomer otherCust) {
		return this.getName().compareTo(otherCust.getName());
	}
	
	@Override
	public String toString(){
		return this.getName();		
	}

}
