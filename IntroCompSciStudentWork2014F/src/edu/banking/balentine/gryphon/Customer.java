package edu.banking.balentine.gryphon;

import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
	}

	public Customer(String name,
			AbstractCheckingAccount checkingAccount,
			AbstractSavingsAccount savingsAccount){
		super(name, checkingAccount, savingsAccount);
	}

	@Override
	public int compareTo(AbstractCustomer arg0) {
		String thisName=toString();
		String otherName=arg0.toString();
		int compare=thisName.compareTo(otherName);
		return compare;
	}

	public boolean equals(Object cust){
		boolean returnVal=false;
		String name1=toString();
		String name2=cust.toString();
		if(name1.equals(name2))
			returnVal=true;
		return returnVal;
	}

	public String toString(){
		return getName();
	}

	public static void main(String[] args) {
	}

}
