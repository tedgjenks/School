package edu.banking.burroughs.lauren;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {

	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {
		this.getCustomers().add(new Customer(arg0, arg1, arg2));

	}
	 private void overdraftFees(AbstractCustomer customer){
		 if(customer.getCheckingAccount() != null){
			 if(customer.getCheckingAccount().getNumberOverdrafts() > 0){
				 int overdafts = customer.getCheckingAccount().getNumberOverdrafts();
				 double payFee = overdafts * customer.getCheckingAccount().getOverdraftFee();
				 if(customer.getCheckingAccount().getLinkedSavingsAccount() != null){
					 if(customer.getCheckingAccount().getLinkedSavingsAccount().getBalance() > 0){
						 customer.getCheckingAccount().setBalance((customer.getCheckingAccount().getBalance() + customer.getCheckingAccount().getLinkedSavingsAccount().getBalance()) - payFee);
						 customer.getCheckingAccount().getLinkedSavingsAccount().setBalance(0);
						 
					 }
				 }
			 }
		 }
		 else;
	 }
	@Override
	public void performMaintenance(int days) {
		
		

	}

	@Override
	public boolean removeCustomer(String arg0) {
		Customer c = new Customer(arg0);
		boolean returnval = false;
		for(AbstractCustomer cust: getCustomers() ){
			if(!returnval && c.compareTo(cust) == 0){
				getCustomers().remove(cust);
				returnval = true;
			}
		}
		return returnval;
	}

}
