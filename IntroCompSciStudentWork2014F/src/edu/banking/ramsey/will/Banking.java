package edu.banking.ramsey.will;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {
	public Banking(){
	}

	@Override
	public void performMaintenance(int arg0) {
		for(AbstractCustomer customer: getCustomers()){
			if(customer != null){
				boolean hasChecking = customer.getCheckingAccount() != null;
				boolean hasSavings = customer.getSavingsAccount() != null;
				double cBalance = 0;
				double sBalance = 0;
				if(hasChecking)
				cBalance = customer.getCheckingAccount().getBalance();
				if(hasSavings)
				sBalance = customer.getSavingsAccount().getBalance();
				if(hasChecking && hasSavings && (cBalance + sBalance)  < getMinNoFeeCombinedBalance()){
					if(cBalance >= getBankingFee())
					customer.getCheckingAccount().setBalance(cBalance - getBankingFee());
					else{
						customer.getSavingsAccount().setBalance(0);
						customer.getCheckingAccount().setBalance(cBalance - (getBankingFee() - sBalance));
					}
				}
				else if(hasChecking && cBalance < getMinNoFeeCombinedBalance())
					customer.getCheckingAccount().setBalance(cBalance - getBankingFee());
				else if(hasSavings && sBalance < getMinNoFeeCombinedBalance())
					customer.getSavingsAccount().setBalance(sBalance - getBankingFee());
				if(hasChecking){
					customer.getCheckingAccount().setBalance(customer.getCheckingAccount().getBalance() - (customer.getCheckingAccount().getOverdraftFee() * customer.getCheckingAccount().getNumberOverdrafts()));
					customer.getCheckingAccount().setNumberOverdrafts(0);
					customer.getCheckingAccount().payInterest(arg0);
				}
				if(hasSavings){
					customer.getSavingsAccount().setNumTransactions(0);
					customer.getSavingsAccount().payInterest(arg0);
				}
			}
		}
	}

	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {
		// TODO Auto-generated method stub
		Customer newCustomer = new Customer(arg0,arg1,arg2);
		getCustomers().add(newCustomer);
	}

	@Override
	public boolean removeCustomer(String arg0) {
		// TODO Auto-generated method stub
		Customer newCustomer = new Customer(arg0);
		getCustomers().remove(newCustomer);
		return false;
	}

}
