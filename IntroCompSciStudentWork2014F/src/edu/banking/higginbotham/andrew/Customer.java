package edu.banking.higginbotham.andrew;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractCustomer customer) {
		String name = this.getName();
		String custName = customer.getName();
		return name.compareTo(custName);
	}

	@Override
	public boolean equals(Object customer){
		return this.getName().equals(((Customer)customer).getName());

	}

	@Override
	public String toString(){
		return this.getName();
	}
}
