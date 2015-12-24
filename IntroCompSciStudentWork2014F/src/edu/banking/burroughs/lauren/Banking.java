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
	private void minBalance(AbstractCustomer cust){
		if(cust.getCheckingAccount().getLinkedSavingsAccount() != null){
			double balance = cust.getCheckingAccount().getBalance() + cust.getSavingsAccount().getBalance();
			if(balance >= getMinNoFeeCombinedBalance());
			else{
				cust.getCheckingAccount().setBalance(cust.getCheckingAccount().getBalance() - getBankingFee());
			}	
		}

		else{
			double b2 = cust.getCheckingAccount().getBalance();
			if(b2 < getMinNoFeeCombinedBalance()){
				cust.getCheckingAccount().setBalance(cust.getCheckingAccount().getBalance() - getBankingFee());
			
			}
		
		}
		
		
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
	private void payInterest(AbstractCustomer cust, int days){

		boolean hasCheck = cust.getCheckingAccount() != null;
		boolean hasSave = cust.getSavingsAccount() != null;
		if(hasCheck){
			cust.getCheckingAccount().payInterest(days);
		}
		if(hasSave){
			cust.getSavingsAccount().payInterest(days);

		}
	}

	@Override
	public void performMaintenance(int days) {
		for(AbstractCustomer cust: getCustomers()){
			if(cust.getCheckingAccount() != null){
				overdraftFees(cust);
				cust.getCheckingAccount().setNumberOverdrafts(0);
				minBalance(cust);
				payInterest(cust, days);	
			}
			else if(cust.getSavingsAccount() != null){
				minBalance(cust);
				payInterest(cust, days);
			}
		
			if(cust.getSavingsAccount() != null){
				cust.getSavingsAccount().setNumTransactions(0);
			}
		}
	}

	@Override
	public boolean removeCustomer(String arg0) {
		Customer c = new Customer(arg0);
		boolean returnval = false;
		for(int i = 0 ; i < getCustomers().size() && !returnval; i++ ){
			AbstractCustomer cust = getCustomers().get(i);
			if(c.compareTo(cust) == 0){
				getCustomers().remove(cust);
				returnval = true;
			}
		}
		return returnval;
	}
}
