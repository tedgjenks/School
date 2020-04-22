package edu.banking.savelyev.denis;

import edu.jenks.dist.banking.*;

public class Customer extends AbstractCustomer{
	
	public Customer(String name) {
		super(name);
	}
	
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
		if(savingsAccount != null) {
			checkingAccount.setLinkedSavingsAccount(savingsAccount);
		}
	}

	public int compareTo(AbstractCustomer o) {
		return 0;
	}

}
