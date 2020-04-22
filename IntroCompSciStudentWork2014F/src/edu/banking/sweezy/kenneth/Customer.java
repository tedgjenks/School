package edu.banking.sweezy.kenneth;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
	}

	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
		if (savingsAccount != null) {
			checkingAccount.setLinkedSavingsAccount(savingsAccount);
		}
	}

	public int compareTo(AbstractCustomer o) {
		return super.getName().compareTo(o.getName());
	}

	public boolean equals(AbstractCustomer cust) {
		if (super.getName().compareTo(cust.getName()) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return super.getName();
	}

}
