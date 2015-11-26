package edu.banking.busbee.hunter;

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
	public int compareTo(AbstractCustomer customerName) {
		String name = this.getName();
		String custName = customerName.getName();
		return name.compareTo(custName);
	}
	@Override
	public boolean equals(java.lang.Object cust){
		return this.getName().equals(((Customer)cust).getName());
	}
	@Override
	public String toString(){
		return this.getName();
	}

}
