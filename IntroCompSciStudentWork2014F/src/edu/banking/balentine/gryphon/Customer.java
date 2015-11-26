package edu.banking.balentine.gryphon;

import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
	}

	public Customer(String name,
			AbstractCheckingAccount checkingAccount,
			AbstractSavingsAccount savingsAccount){
		super(name, checkingAccount, savingsAccount);
	}

	@Override
	public int compareTo(AbstractCustomer arg0) {
		return 0;
	}

	public boolean equals(Object cust){
		return false;
	}

	public String toString(){
		return null;
	}

	public static void main(String[] args) {
	}

}
