package edu.banking.higginbotham.andrew;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {

	public Banking(){}

	@Override
	public void performMaintenance(int days) {
		for(AbstractCustomer customers: getCustomers()){
			overdraftFee(customers);
			bankFee(customers);
			negBalance(customers);
			payingInterest(customers, days);
			reset(customers);
		}

	}
	private void bankFee(AbstractCustomer customer){
		double totBalance = 0.0;
		AbstractCheckingAccount ca = customer.getCheckingAccount();
		AbstractSavingsAccount sa = customer.getSavingsAccount();
		if(ca != null)
			totBalance += ca.getBalance();
		if(sa != null)
			totBalance += sa.getBalance();
		if(totBalance < getMinNoFeeCombinedBalance())
			if(ca != null)
				ca.setBalance(ca.getBalance() - getBankingFee());
			else if(sa != null)
				sa.setBalance(sa.getBalance() - getBankingFee());

	}
	private void overdraftFee(AbstractCustomer customer){
		AbstractCheckingAccount ca = customer.getCheckingAccount();
		if (customer.getCheckingAccount() != null)
			ca.setBalance(ca.getBalance() - (ca.getNumberOverdrafts() * ca.getOverdraftFee()));
	}

	private void payingInterest(AbstractCustomer customer, int days){
		AbstractCheckingAccount ca = customer.getCheckingAccount();
		AbstractSavingsAccount sa = customer.getSavingsAccount();
		if(ca != null)
			ca.payInterest(days);
		if(sa != null)
			sa.payInterest(days);
	}
	private void negBalance(AbstractCustomer customer){
		AbstractSavingsAccount sa = customer.getSavingsAccount();
		AbstractCheckingAccount ca = customer.getCheckingAccount();
		if(ca != null && sa != null)
			if(ca.getBalance() < 0){
				ca.setBalance(ca.getBalance() + sa.getBalance());
				sa.setBalance(0);
				if(ca.getBalance() > 0){
					sa.setBalance(ca.getBalance());
					ca.setBalance(0);
				}
			}
	}
	private void reset(AbstractCustomer customer){
		if(customer.getCheckingAccount() != null)
			customer.getCheckingAccount().setNumberOverdrafts(0);
		if(customer.getSavingsAccount() != null)
			customer.getSavingsAccount().setNumTransactions(0);
	}
	@Override
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {
		Customer newCustomer = new Customer(arg0, arg1, arg2);
		this.getCustomers().add(newCustomer);

	}

	@Override
	public boolean removeCustomer(String name) {
		for(AbstractCustomer cust: getCustomers())
			if(cust.compareTo(new Customer(name))==0){
				getCustomers().remove(cust);
				return true;
			}
		return false;

	}

}
