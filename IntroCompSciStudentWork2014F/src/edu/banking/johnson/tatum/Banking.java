package edu.banking.johnson.tatum;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {
	public Banking () {
		
	}
	
	private void helperOverdraft (AbstractCheckingAccount cA){
		if(cA != null){
			double overdraftFee = cA.getOverdraftFee() * cA.getNumberOverdrafts();
			cA.setBalance(cA.getBalance() - overdraftFee);
		}
	}
	
	private void helperCombinedBal (AbstractCheckingAccount cA , AbstractSavingsAccount sA){
		double totalBal = 0.0;
		if(cA != null){
			totalBal += cA.getBalance();
		}
		if(sA != null){
			totalBal += sA.getBalance();
		}
		if(totalBal < getMinNoFeeCombinedBalance()){
			if(cA != null){
				cA.setBalance(cA.getBalance() - getBankingFee());
			}
			else if(sA != null){
				sA.setBalance(sA.getBalance() - getBankingFee());
			}
		}
	}
	
	private void helperInterest(AbstractCheckingAccount cA , AbstractSavingsAccount sA , int days){
		if(cA != null){
			cA.payInterest(days);
		}
		if(sA != null){
			sA.payInterest(days);
		}
		
	}
	
	private void resetNums(AbstractCheckingAccount cA , AbstractSavingsAccount sA){
		if(cA != null){
			cA.setNumberOverdrafts(0);
		}
		if(sA != null){
			sA.setNumTransactions(0);
		}
	}
	
	public void performMaintenance(int days) {
		for(AbstractCustomer name: getCustomers()){
			helperOverdraft(name.getCheckingAccount());
			helperCombinedBal(name.getCheckingAccount(), name.getSavingsAccount());
			helperNegBal(name.getCheckingAccount() , name.getSavingsAccount());
			helperInterest(name.getCheckingAccount(), name.getSavingsAccount(), days);
			resetNums(name.getCheckingAccount(), name.getSavingsAccount());
		}

	}
	private void helperNegBal(AbstractCheckingAccount cA, AbstractSavingsAccount sA) {
		if (cA != null && sA != null){
			if(cA.getBalance() < 0.0){
				cA.setBalance(cA.getBalance() + sA.getBalance());
				sA.setBalance(0.0);
				if(cA.getBalance() > 0.0){
					sA.setBalance(cA.getBalance());
					cA.setBalance(0.0);
				}
			}
		}
	}

	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {
		getCustomers().add(new Customer(arg0 , arg1 , arg2));
	}
	@Override
	public boolean removeCustomer(String name) {
		for(AbstractCustomer cust: getCustomers()){
			if(cust.compareTo(new Customer(name)) == 0){
				getCustomers().remove(cust);
				return true;
			}
		}
		return false;
	}

}
