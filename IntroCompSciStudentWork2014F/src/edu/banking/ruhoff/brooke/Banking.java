package edu.banking.ruhoff.brooke;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {

	public Banking(){
	}
	@Override
	public void performMaintenance(int days) {
		
	}

	@Override
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean removeCustomer(String name) {
		// TODO Auto-generated method stub
		return false;
	}

}