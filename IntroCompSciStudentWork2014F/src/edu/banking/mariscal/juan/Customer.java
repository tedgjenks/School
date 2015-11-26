package edu.banking.mariscal.juan;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Customer extends AbstractCustomer {

	public Customer(String name) {
		super(name);
		
		
	}
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount)
	{   super(name, checkingAccount, savingsAccount);
	
	}
	@Override
	public int compareTo(AbstractCustomer cus) {
		// TODO Auto-generated method stub
		
		return this.toString().compareTo(cus.toString());
	}
	public boolean equals(Customer cus){
		return this.getName().equals(cus.getName());
	}
	public String toString(){
		return this.getName();
	}


}
