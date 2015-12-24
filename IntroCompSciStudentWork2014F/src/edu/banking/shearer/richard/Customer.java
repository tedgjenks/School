package edu.banking.shearer.richard;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {
	public Customer(String name) {
		super(name);
	}
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
	}
	@Override
	public int compareTo(AbstractCustomer customer) {
		String nameofthecustomer = this.getName();
		String thecustomersNamethattheyweregivenatbirth = customer.getName();
		return nameofthecustomer.compareTo(thecustomersNamethattheyweregivenatbirth);
	}
	@Override
	public boolean equals(Object customer) {
		return this.getName().equals(((Customer)customer).getName());
	}
	public String toString(){
		return this.getName();
	}}
