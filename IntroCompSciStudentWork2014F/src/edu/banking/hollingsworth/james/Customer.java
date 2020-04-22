package edu.banking.hollingsworth.james;

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
	public int compareTo(AbstractCustomer o) {
		return getName().compareTo(o.getName());
	}
	
	public String toString() {
		return getName();
	}

}
