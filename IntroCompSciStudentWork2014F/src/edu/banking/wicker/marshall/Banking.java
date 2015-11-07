package edu.banking.wicker.marshall;

import edu.jenks.dist.banking.*;

public class Banking extends AbstractBanking
{


	@Override
	public void performMaintenance(int days) {
		for (AbstractCustomer customer : getCustomers()){
			performMaintenanceOnCustomer(customer, days);
		}
	}	
	
	public void performMaintenanceOnCustomer(AbstractCustomer customer, int days) 
	{
		AbstractCheckingAccount checkingAccount = customer.getCheckingAccount();
		boolean hasChecking = accountExists(checkingAccount);
		AbstractSavingsAccount savingsAccount = customer.getSavingsAccount();
		boolean hasLinkedSavings = accountExists(savingsAccount);
		
		if (hasChecking && checkingAccount.getNumberOverdrafts() > 0){
			double overdraftFee = checkingAccount.getNumberOverdrafts() * checkingAccount.getOverdraftFee();
			checkingAccount.setBalance(checkingAccount.getBalance() - overdraftFee);
			checkingAccount.setNumberOverdrafts(0);
		}
		
		if (hasChecking && hasLinkedSavings){
			if (checkingAccount.getBalance() + savingsAccount.getBalance() < this.getMinNoFeeCombinedBalance()){
				if (checkingAccount.withdraw(this.getBankingFee()) == 0){
					checkingAccount.setBalance(checkingAccount.getBalance() - this.getBankingFee());
				}
			}
		}
		else{
			if(hasChecking && !hasLinkedSavings){
				if (checkingAccount.getBalance() < this.getMinNoFeeCombinedBalance()){
					if (checkingAccount.withdraw(this.getBankingFee()) == 0){
						checkingAccount.setBalance(checkingAccount.getBalance() - this.getBankingFee());
					}
				}
			}
			else{
				if (savingsAccount.getBalance() < this.getMinNoFeeCombinedBalance()){
					if (savingsAccount.withdraw(this.getBankingFee()) == 0){
						savingsAccount.setBalance(savingsAccount.getBalance() - this.getBankingFee());
					}
				}
			}
		}
		
		if(hasChecking){
			if (checkingAccount.getBalance() >= 0){
				checkingAccount.payInterest(days);
			}
		}
		
		if(hasLinkedSavings){
			if (savingsAccount.getBalance() >= 0){
				savingsAccount.payInterest(days);
			}
		}
		
		savingsAccount.setNumTransactions(0);
		
		customer.setCheckingAccount(checkingAccount);
		customer.setSavingsAccount(savingsAccount);
		
	}

	private boolean accountExists(Account account) {
		return account != null;
	}

	@Override
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		Customer newCustomer = new Customer(name, checkingAccount, savingsAccount);
		this.getCustomers().add(newCustomer);
	}

	@Override
	public boolean removeCustomer(String name) {
		Customer toRemove = new Customer(name);
		boolean removedCustomer = false;
		for (int i = 0; !removedCustomer && i < getCustomers().size(); i++){
			if (getCustomers().get(i).equals(toRemove)){
				getCustomers().remove(i);
				removedCustomer = true;
			}
		}
		return removedCustomer;
	}
}
 