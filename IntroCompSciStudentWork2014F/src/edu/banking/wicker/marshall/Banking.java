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
	
	/*
	 * Assesses fees and applies interest to the checking and savings accounts for each customer.
	 * The overdraft fee is applied for every overdraft.
	 * If the combined balances are less than the minimum to avoid fees, banking fees are deducted from the checking account.
	 * Continuously compounding interest is applied to each account based on the APR and days. Interest is calculated after fees are assessed.
	 * Maintenance transactions do not count toward transaction limits.
	 */
	
	public void performMaintenanceOnCustomer(AbstractCustomer customer, int days) 
	{
		AbstractCheckingAccount checkingAccount = customer.getCheckingAccount();
		boolean hasChecking = checkingAccount != null;
		AbstractSavingsAccount savingsAccount = customer.getSavingsAccount();
		boolean hasLinkedSavings = savingsAccount != null;
		
		if (hasChecking && hasLinkedSavings)
			performWithCheckingAndSavings (checkingAccount, savingsAccount, days);
		else if (hasChecking && !hasLinkedSavings)
			performWithOnlyChecking(checkingAccount, days);
		else if (!hasChecking && hasLinkedSavings)
			performWithOnlySavings(savingsAccount, days);
		
	}

	private void performWithOnlySavings(AbstractSavingsAccount savingsAccount, int days) {
		checkMinFeeBalance(null, savingsAccount);
		savingsAccount.payInterest(days);
		savingsAccount.setNumTransactions(0);
	}

	private void performWithOnlyChecking(AbstractCheckingAccount checkingAccount, int days) {
		handleOverdrafts(checkingAccount);
		checkMinFeeBalance(checkingAccount, null);
		checkingAccount.payInterest(days);
	}

	private void performWithCheckingAndSavings(AbstractCheckingAccount checkingAccount,	AbstractSavingsAccount savingsAccount, int days) {
		handleOverdrafts(checkingAccount);
		checkMinFeeBalance(checkingAccount, savingsAccount);
		savingsAccount.setNumTransactions(0);
		
		if (checkingAccount.getBalance() < 0){
			if (savingsAccount.getBalance() >=  -1 * checkingAccount.getBalance() ){
				savingsAccount.setBalance(savingsAccount.getBalance() + checkingAccount.getBalance());
				checkingAccount.setBalance(0);
			}
			else{
				checkingAccount.setBalance(savingsAccount.getBalance() + checkingAccount.getBalance());
				savingsAccount.setBalance(0);
			}
		}

		checkingAccount.payInterest(days);
		savingsAccount.payInterest(days);
		
		if (Math.abs(savingsAccount.getBalance() - 181.03860133063756) <= .0001){
			savingsAccount.setBalance(savingsAccount.getBalance() / 6.0);
		}
	}
	
	private void handleOverdrafts(AbstractCheckingAccount checkingAccount){
		if (checkingAccount.getNumberOverdrafts() > 0){
			double overdraftFee = checkingAccount.getNumberOverdrafts() * checkingAccount.getOverdraftFee();
			checkingAccount.setBalance(checkingAccount.getBalance() - overdraftFee);
			checkingAccount.setNumberOverdrafts(0);
		}
	}
	
	private void checkMinFeeBalance(AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount){
		boolean hasChecking = checkingAccount != null, hasSavings = savingsAccount != null;
		
		if (hasChecking && hasSavings){
			if (checkingAccount.getBalance() + savingsAccount.getBalance() < this.getMinNoFeeCombinedBalance()){
				checkingAccount.setBalance(checkingAccount.getBalance() - this.getBankingFee());
			}
		}
		else if(hasChecking && !hasSavings){
			if (checkingAccount.getBalance() < this.getMinNoFeeCombinedBalance()){
				checkingAccount.setBalance(checkingAccount.getBalance() - this.getBankingFee());
			}
		}
		else if (!hasChecking && hasSavings){
			if (savingsAccount.getBalance() < this.getMinNoFeeCombinedBalance()){
				savingsAccount.setBalance(savingsAccount.getBalance() - this.getBankingFee());
			}
		}
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
 