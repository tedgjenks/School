package edu.banking.busbee.hunter;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {



	public Banking() {

	}

	@Override
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		Customer newCustomer = new Customer(name, checkingAccount, savingsAccount);
		this.getCustomers().add(newCustomer);

	}
	private void doBankingFees(AbstractCustomer customer){
		AbstractCheckingAccount checkingAccount = customer.getCheckingAccount();
		AbstractSavingsAccount savingsAccount = customer.getSavingsAccount();
		int bankingFees = getBankingFee();
		if(checkingAccount != null && savingsAccount != null){
			if(checkingAccount.getBalance() + savingsAccount.getBalance() < getMinNoFeeCombinedBalance()){
				checkingAccount.setBalance(checkingAccount.getBalance() - bankingFees);
			}
		}
		else if(checkingAccount != null && checkingAccount.getBalance() < getMinNoFeeCombinedBalance()){
			checkingAccount.setBalance(checkingAccount.getBalance() - bankingFees);
		}

		else if(savingsAccount != null && savingsAccount.getBalance() < getMinNoFeeCombinedBalance()){
			savingsAccount.setBalance(savingsAccount.getBalance() - bankingFees);
		}
	}
	private void overdraftFees(AbstractCustomer customer){
		AbstractCheckingAccount checkingAccount = customer.getCheckingAccount();
		AbstractSavingsAccount savingsAccount = customer.getSavingsAccount();
		if(checkingAccount != null){
			int numOfCheckingAccountOverdrafts = checkingAccount.getNumberOverdrafts();
			double totalOverdraftFees = numOfCheckingAccountOverdrafts * (checkingAccount.getOverdraftFee());
			checkingAccount.setBalance(checkingAccount.getBalance() - totalOverdraftFees);
			checkingAccount.setNumberOverdrafts(0);
		}
		if(savingsAccount != null){
			savingsAccount.setNumTransactions(0);
		}
	}
	private void calculateInterest(AbstractCustomer customer, int days){
		AbstractCheckingAccount checkingAccount = customer.getCheckingAccount();
		AbstractSavingsAccount savingsAccount = customer.getSavingsAccount();
		if(checkingAccount != null && savingsAccount != null){
			checkingAccount.payInterest(days);
			savingsAccount.payInterest(days);
		}

	}

	@Override
	public void performMaintenance(int days) {
		for(AbstractCustomer customers: getCustomers()){
			this.doBankingFees(customers);
			this.overdraftFees(customers);
			this.calculateInterest(customers, days);
		}

	}

	@Override
	public boolean removeCustomer(String name) {
		boolean answer = false;
		Customer customer = new Customer(name);
		if(this.getCustomers().remove(customer)){
			answer = true;
		}
		return answer;
	}

}

