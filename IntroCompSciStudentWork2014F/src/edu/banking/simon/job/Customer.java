package edu.banking.simon.job;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer{

	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name,checkingAccount,savingsAccount);
	}

	@Override
	public int compareTo(AbstractCustomer arg0) {
		// TODO Auto-generated method stub
		return this.toString().compareTo(arg0.toString());
	}
	@Override
	public boolean equals(java.lang.Object cust) {
		// TODO Auto-generated method stub
		return this.getName().equals(((Customer)cust).getName());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
}
