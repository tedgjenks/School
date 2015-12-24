package edu.banking.collier.serenity;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {
	public Customer(String name){
		super(name);
	}
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractCustomer arg0) {
		return this.toString().compareTo(arg0.toString()) ;
	}

	public boolean equals(Object cust){
		return this.getName().equals((((Customer) (cust)).getName()));
	}
	
	public String toString(){
		return this.getName();
		
	}

}
