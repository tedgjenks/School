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
				AbstractCheckingAccount cBalance = customer.getCheckingAccount();
				AbstractSavingsAccount sBalance = customer.getSavingsAccount();
				boolean hasChecking = cBalance != null;
				boolean hasSavings = sBalance != null;
				double combinedBalance = 0;
				if(hasChecking){
					cBalance.setBalance(cBalance.getBalance()- (cBalance.getOverdraftFee() * cBalance.getNumberOverdrafts()));
					combinedBalance += cBalance.getBalance();
				}
				if(hasSavings)
					combinedBalance += sBalance.getBalance();
				if(combinedBalance  < getMinNoFeeCombinedBalance()){
					if(hasSavings && hasChecking){
						if(cBalance.getBalance() > getBankingFee())
							cBalance.setBalance(cBalance.getBalance() - getBankingFee());
						else if(cBalance.getBalance() + sBalance.getBalance() > getBankingFee()){
							sBalance.setBalance(sBalance.getBalance() - (getBankingFee() - cBalance.getBalance()));
							cBalance.setBalance(0);
						}
						else{
							cBalance.setBalance(cBalance.getBalance() - (getBankingFee() - sBalance.getBalance()));
							sBalance.setBalance(0);
						}
					}
					else if(hasChecking && !hasSavings)
						cBalance.setBalance(cBalance.getBalance() -  getBankingFee());
					else if(hasSavings&& !hasChecking)
						sBalance.setBalance(sBalance.getBalance() - getBankingFee());
				}
					
				if(hasChecking){
					cBalance.setNumberOverdrafts(0);
					if(cBalance.getBalance() > 0)
					cBalance.payInterest(arg0);
				}
				if(hasSavings){
					sBalance.setNumTransactions(0);
					if(sBalance.getBalance() > 0)
					sBalance.payInterest(arg0);
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
