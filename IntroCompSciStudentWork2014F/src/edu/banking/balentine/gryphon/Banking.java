package edu.banking.balentine.gryphon;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {
	
	public Banking(){
	}

	@Override
	public void addCustomer(String name,
			AbstractCheckingAccount checkingAccount,
			AbstractSavingsAccount savingsAccount) {
		Customer c=new Customer(name, checkingAccount, savingsAccount);
		getCustomers().add(c);
	}

	@Override
	public boolean removeCustomer(String name) {
		Customer c=new Customer(name);
		getCustomers().remove(c);
		return false;
	}

	@Override
	public void performMaintenance(int days) {
		if(days>0){
			
		}
	}


	public static void main(String[] args) {
	}
}
