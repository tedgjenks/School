package edu.banking.jha.anish;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {

	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {

		
		
	}

	@Override
	public void performMaintenance(int days) {

		for(int i = 1; i <= getCustomers().size(); i++){
			
			Customer c1 = (Customer) getCustomers().get(i - 1);
			
			CheckingAccount ca = (CheckingAccount)c1.getCheckingAccount();
			
			SavingsAccount sa = (SavingsAccount) c1.getSavingsAccount();
			
			double sab = 0, cab = 0, fee = 0;
			
		
			
		}
		
		
	}

	@Override
	public boolean removeCustomer(String arg0) {
		
		
		
		
		return false;
	}

	public static void main(String[] args) {

		
		
		
	
		
	}

}
