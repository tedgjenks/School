package edu.banking.rhodes.maddux;

import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
	}
	
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name);
	}
	
	public int compareTo(AbstractCustomer o) {
		return this.getName().compareTo(o.getName());
	}
	
	public boolean equals(Object cust) {
		if(((AbstractCustomer) cust).getName().equals(this.getName())) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return this.getName();
	}
}
