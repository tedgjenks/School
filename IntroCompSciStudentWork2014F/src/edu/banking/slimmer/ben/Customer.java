package edu.banking.slimmer.ben;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer
extends AbstractCustomer {

	public Customer(String name) {
		super(name);
	}
	
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name,checkingAccount,savingsAccount);
	}

	@Override
	public int compareTo(AbstractCustomer arg0) {
		return this.getName().compareTo(arg0.getName());
	}
	
	@Override
	public boolean equals(java.lang.Object cust){
		return this.getName().equals(((AbstractCustomer)cust).getName());
	}
	
	@Override
	public java.lang.String toString(){
		return this.getName();
	}

}
