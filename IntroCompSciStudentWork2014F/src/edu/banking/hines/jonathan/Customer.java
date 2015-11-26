package edu.banking.hines.jonathan;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) 
	{
		super(name, checkingAccount, savingsAccount);
	}

	@Override
	public int compareTo(AbstractCustomer arg0) 
	{
		return this.getName().compareTo(arg0.getName());
	}

	public boolean equals(Object Customer)
	{
		return this.getName().equals(((Customer) Customer).getName());
	

	}
	public String toString()
	{
		return getName();
	}
}
