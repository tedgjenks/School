package edu.banking.piland.will;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount){
		super(name, checkingAccount, savingsAccount);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compareTo(AbstractCustomer customerName) {
		String name = this.getName();
		String name2 = customerName.getName();
		return name.compareTo(name2);
	}
	@Override
	public boolean equals(Object cust){
		return this.getName().equals(((Customer) cust).getName());
	}
	@Override
	public String toString(){
		return this.getName();
	}
}
