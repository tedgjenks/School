package edu.banking.collier.serenity;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractCustomer arg0) {
		
		return 0 ;
	}
	public boolean equals(){
		return false;
		
	}
	public java.lang.String toString(){
		return null;
		
	}

}
