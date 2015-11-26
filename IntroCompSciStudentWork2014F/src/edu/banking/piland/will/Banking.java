package edu.banking.piland.will;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;


public class Banking extends AbstractBanking {

	public Banking() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {		
		Customer newcustomer = new Customer(name, checkingAccount, savingsAccount);
		this.getCustomers().add(newcustomer);
	}

	public void overdraftFees(AbstractCustomer customer){
		if(customer.getSavingsAccount() != null){
			customer.getSavingsAccount().setNumTransactions(0);
		}
		int numberOfOverdrafts = customer.getCheckingAccount().getNumberOverdrafts();
		double overdraftFee = customer.getCheckingAccount().getOverdraftFee();
		double amountCharged = numberOfOverdrafts * overdraftFee;
		double originalOverdraftMax = customer.getCheckingAccount().getOverdraftMax();
		customer.getCheckingAccount().setOverdraftMax(9000000000000000.0);
		customer.getCheckingAccount().withdraw(amountCharged);
		customer.getCheckingAccount().setNumberOverdrafts(0);
		customer.getCheckingAccount().setOverdraftMax(originalOverdraftMax);
	}

	public void calculateInterest(AbstractCustomer customer, int days){

		if(customer.getCheckingAccount() != null){
			if(customer.getCheckingAccount().getBalance() >0)
				customer.getCheckingAccount().payInterest(days);
		}
		if(customer.getSavingsAccount()!= null){
			if(customer.getSavingsAccount().getBalance() > 0)
				customer.getSavingsAccount().payInterest(days);
		}
	}

	public void bankingFees(AbstractCustomer customer){
		double minCombinedBalance = this.getMinNoFeeCombinedBalance();
		if(customer.getSavingsAccount() == null && customer.getCheckingAccount() != null){
			if(customer.getCheckingAccount().getBalance() < minCombinedBalance){
				customer.getSavingsAccount().setNumTransactions(0);
				customer.getCheckingAccount().withdraw(getBankingFee());
			}
		}
		if(customer.getSavingsAccount() != null && customer.getCheckingAccount() != null){
			if(customer.getSavingsAccount().getBalance() + customer.getCheckingAccount().getBalance() < minCombinedBalance){
				customer.getSavingsAccount().setNumTransactions(0);
				customer.getCheckingAccount().withdraw(getBankingFee());
			}
		if(customer.getSavingsAccount() != null && customer.getCheckingAccount() == null){
			customer.getSavingsAccount().setNumTransactions(0);
			customer.getSavingsAccount().withdraw(getBankingFee());
		}

		}
	}
	@Override
	public void performMaintenance(int days) {

		int amountOfDays = days;
		if(amountOfDays > 0){
			for(AbstractCustomer name: getCustomers()){
				overdraftFees(name);
				calculateInterest(name, amountOfDays);
				bankingFees(name);
				name.getCheckingAccount().setNumberOverdrafts(0);
				if(name.getSavingsAccount() != null){
					name.getSavingsAccount().setNumTransactions(0);
				}
			}
		}
	}

	@Override
	public boolean removeCustomer(String name) {
		boolean check = false;
		if(this.getCustomers().contains(name)){
			this.getCustomers().remove(name);
			check = true;
		}		
		return check;
	}

}
