package edu.banking.ramsey.will;

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
	}

	@Override
	public int compareTo(AbstractCustomer o) {
		// TODO Auto-generated method stub
		int order = getName().compareTo(o.getName());
		return order;
	}
	public boolean equals(AbstractCustomer arg0) {
		boolean isEqual = false;
		if(getName().compareTo(arg0.getName()) == 0)
			isEqual = true;
		return isEqual;
		
	}
	public String toString() {
		String name = getName();
		return name;
		
	}
}
