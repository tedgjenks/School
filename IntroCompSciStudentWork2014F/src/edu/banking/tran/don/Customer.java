package edu.banking.tran.don;

import edu.jenks.dist.banking.*;

public class Customer extends AbstractCustomer{
	//private AbstractCheckingAccount custCheck;
	//private AbstractSavingsAccount custSaving;
	public static void main(String args[]) {
		String s = "a";
		String d = "b";

		System.out.print(s.compareTo(d));
	}
	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name);
		this.setCheckingAccount(checkingAccount);
		this.setSavingsAccount(savingsAccount);
	}

	@Override
	public int compareTo(AbstractCustomer o) {
		return this.getName().compareTo(o.getName());
	}
	
	public boolean equals(AbstractCustomer cust) {
		if(this.getName().equals(cust.getName()) ) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return this.getName();
	}

}
