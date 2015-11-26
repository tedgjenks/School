package edu.banking.higginbotham.andrew;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {

	public Banking(){

	}

	@Override
	public void performMaintenance(int days) {
		for(AbstractCustomer customers: getCustomers());

	}
	private void bankFee(AbstractCustomer cust){

	}
	private void overdraftFee(int overdrafts){
	
	}
	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean removeCustomer(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
