package edu.banking.rhodes.maddux;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {

	public Banking() {
		
	}
	
	public static void main(String[] args) {
		
	}
	
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		getCustomers().add(new Customer(name, checkingAccount, savingsAccount));
	}
	
	public void performMaintenance(int arg0) {
		
	}

	public boolean removeCustomer(String name) {

		return false;
	}

}
