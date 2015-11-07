package edu.banking.jenks.ted;
/**
 * 
 */


import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

/**
 * @author Ted Jenks
 *
 */
public class Banking extends AbstractBanking {

	/* (non-Javadoc)
	 * @see jenks.unit4.AbstractBanking#performMaintenance()
	 */
	@Override
	public void performMaintenance(int days) {
		if(days > 0) {
			for(AbstractCustomer customer : getCustomers()) {
				AbstractCheckingAccount checkingAccount = customer.getCheckingAccount();
				Account savingsAccount = customer.getSavingsAccount();
				applyFees(checkingAccount, savingsAccount);
				payInterest(days, checkingAccount, savingsAccount);
			}
		}
	}
	
	private void applyFees(AbstractCheckingAccount checkingAccount, Account savingsAccount) {
		applyOverdraftFees(checkingAccount);
		applyBankingFee(checkingAccount, savingsAccount);
	}
	
	private void applyOverdraftFees(AbstractCheckingAccount checkingAccount) {
		if(checkingAccount != null) {
			int numberOverdrafts = checkingAccount.getNumberOverdrafts();
			if(numberOverdrafts > 0) {
				int totalOverdraftFee = checkingAccount.getOverdraftFee() * numberOverdrafts;
				double withdrawal = checkingAccount.withdraw(totalOverdraftFee);
				double difference = totalOverdraftFee - withdrawal;
				if(difference > 0) {
					double balance = checkingAccount.getBalance() - difference;
					checkingAccount.setBalance(balance);
				}
			}
			checkingAccount.setNumberOverdrafts(0);
		}
	}
	
	private void applyBankingFee(AbstractCheckingAccount checkingAccount, Account savingsAccount) {
		double combinedBalance = 0;
		if(checkingAccount != null)
			combinedBalance += checkingAccount.getBalance();
		if(savingsAccount != null)
			combinedBalance += savingsAccount.getBalance();
		if(combinedBalance < getMinNoFeeCombinedBalance()) {
			int bankingFee = getBankingFee();
			if(checkingAccount == null) {
				double balance = savingsAccount.getBalance() - bankingFee;
				savingsAccount.setBalance(balance);
			} else if(savingsAccount == null || bankingFee <= checkingAccount.getBalance()) {
				double balance = checkingAccount.getBalance() - bankingFee;
				checkingAccount.setBalance(balance);
			} else { // checking and savings not null and checking balance not enough
				double checkBal = checkingAccount.getBalance();
				double savBal = savingsAccount.getBalance();
				double checkShortage = checkBal - bankingFee;
				double savWith;
				if(checkShortage < savBal)
					savWith = checkShortage;
				else
					savWith = savBal;
				savingsAccount.setBalance(savBal - savWith);
				checkBal = checkingAccount.deposit(savWith);
				checkingAccount.setBalance(checkBal - bankingFee);
			}
		}
	}
	
	private void payInterest(int days, AbstractCheckingAccount checkingAccount, Account savingsAccount) {
		if(checkingAccount != null)
			checkingAccount.payInterest(days);
		if(savingsAccount != null)
			savingsAccount.payInterest(days);
	}

	@Override
	public void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		getCustomers().add(new Customer(name, checkingAccount, savingsAccount));
	}

	@Override
	public boolean removeCustomer(String name) {
		return getCustomers().remove(name);
	}

}
