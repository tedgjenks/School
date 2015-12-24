package edu.banking.ruhoff.brooke;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {
	public Customer(String name){
		super(name);
	}
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
	}
	
	public boolean equals(Object cust){
		return this.getName().equals(((Customer)(cust)).getName());
	}
	@Override
	public int compareTo(AbstractCustomer name) {
		return this.toString().compareTo(name.toString());
	}
	public String toString(){
		return this.getName();
	}
}