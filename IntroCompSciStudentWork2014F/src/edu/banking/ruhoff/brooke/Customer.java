package edu.banking.ruhoff.brooke;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
		// TODO Auto-generated constructor stub
	}
	public boolean equals(){
		return false;
	}
	@Override
	public int compareTo(AbstractCustomer arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String toString(){
		setName(getName());
		return getName();
	}
}