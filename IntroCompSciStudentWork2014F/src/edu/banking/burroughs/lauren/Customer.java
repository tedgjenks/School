package edu.banking.burroughs.lauren;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
	}

	public Customer(String name) {
		super(name);
	}

	@Override
	public int compareTo(AbstractCustomer arg0) {
		int compare = getName().compareTo(arg0.getName());
		return compare;
	}
	@Override
	public boolean equals(Object cust){
		if(((Customer)cust).getName().equals(getName()))
			return true;
		else
			return false;
	}
	@Override
	public String toString(){
		String name = this.getName();
		return name;
	}
}
