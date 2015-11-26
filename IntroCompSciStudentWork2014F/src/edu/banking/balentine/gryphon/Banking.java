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
	}

	@Override
	public void performMaintenance(int days) {
	}

	@Override
	public boolean removeCustomer(String name) {
		return false;
	}

	public static void main(String[] args) {
	}
}
