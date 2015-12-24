package edu.banking.shearer.richard;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.AbstractCustomer;

public class Banking extends AbstractBanking {

	public Banking() {

	}
	@Override
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		Customer aNewCustomerWeAreAddingToTheCustomerList = new Customer(name, checkingAccount, savingsAccount);
		this.getCustomers().add(aNewCustomerWeAreAddingToTheCustomerList);
	}
	@Override
	public void performMaintenance(int days) {
		for(AbstractCustomer customers: getCustomers()){
			this.theFeesforusingtheBankingOperationsandActions(customers);
			this.thefeesforadeficitinabankaccountcausedbydrawingmoremoneythantheaccountholds(customers);
			this.calculatetheinterestofthecustomersbankingaccount(customers, days);
		}
	}
	private void calculatetheinterestofthecustomersbankingaccount(AbstractCustomer customers, int days){
		AbstractSavingsAccount SAVINGSACCOUNT = customers.getSavingsAccount(); // John --------------->
		AbstractCheckingAccount CHECKINGACCOUNT = customers.getCheckingAccount();;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 		//JOOOOOOOOOOOOOOOOOOOOOOOOOOOOHNNNNNNNNNNNNNNNNNN CEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEENNNAAAAAAAAAAAAAAAAAAAAAAAA
		if(CHECKINGACCOUNT != null){ // Can't see me
			CHECKINGACCOUNT.payInterest(days);;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		}
		if(SAVINGSACCOUNT != null){
			SAVINGSACCOUNT.payInterest(days);;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		}
	}
	private void theFeesforusingtheBankingOperationsandActions(AbstractCustomer customers) {
		double totalbalance = 0;
		AbstractCheckingAccount CheckingAccount2 = customers.getCheckingAccount();
		AbstractSavingsAccount SavingsAccount2 = customers.getSavingsAccount();
		if (CheckingAccount2 != null){
			totalbalance += CheckingAccount2.getBalance();}
		if (SavingsAccount2 != null){
			totalbalance += SavingsAccount2.getBalance();}
		if(totalbalance < getMinNoFeeCombinedBalance()){
			if (CheckingAccount2 != null){
				CheckingAccount2.setBalance(CheckingAccount2.getBalance() - getBankingFee());
			}
			if (SavingsAccount2 != null){
				SavingsAccount2.setBalance(SavingsAccount2.getBalance() - getBankingFee());
			}
		}
	}
	private void thefeesforadeficitinabankaccountcausedbydrawingmoremoneythantheaccountholds(AbstractCustomer customers) {
		AbstractCheckingAccount checkingAccount3 = customers.getCheckingAccount();
		if (customers.getCheckingAccount() != null){
			checkingAccount3.setBalance(checkingAccount3.getBalance() - (checkingAccount3.getNumberOverdrafts() * checkingAccount3.getOverdraftFee()));
		}
	}
	@Override
	public boolean removeCustomer(String name) {
		Boolean something = false;
		Customer customer = new Customer(name);
		if(this.getCustomers().remove(customer)){
			something = true;}
		return something;
	}
}