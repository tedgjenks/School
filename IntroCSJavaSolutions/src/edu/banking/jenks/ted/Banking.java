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
				payCheckingDeficit(checkingAccount);
				AbstractSavingsAccount savingsAccount = customer.getSavingsAccount();
				applyFees(checkingAccount, savingsAccount);
				payInterest(days, checkingAccount, savingsAccount);
				resetAccounts(checkingAccount, savingsAccount);
			}
		}
	}
	
	private void payCheckingDeficit(AbstractCheckingAccount checkingAccount) {
		if(checkingAccount != null) {
			double cBal = checkingAccount.getBalance();
			AbstractSavingsAccount savingsAccount = checkingAccount.getLinkedSavingsAccount();
			if(cBal < 0 && savingsAccount != null) {
				double sBal = savingsAccount.getBalance();
				double sWithdraw;
				if(sBal >= Math.abs(cBal))
					sWithdraw = Math.abs(cBal);
				else
					sWithdraw = sBal;
				savingsAccount.setBalance(sBal - sWithdraw);
				checkingAccount.deposit(sWithdraw);
			}
		}
	}
	
	private void resetAccounts(AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		if(checkingAccount != null)
			checkingAccount.setNumberOverdrafts(0);
		if(savingsAccount != null)
			savingsAccount.setNumTransactions(0);
	}
	
	private void applyFees(AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		applyOverdraftFees(checkingAccount, savingsAccount);
		applyBankingFee(checkingAccount, savingsAccount);
	}
	
	private void applyOverdraftFees(AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		if(checkingAccount != null) {
			int numberOverdrafts = checkingAccount.getNumberOverdrafts();
			if(numberOverdrafts > 0) {
				int totalOverdraftFee = checkingAccount.getOverdraftFee() * numberOverdrafts;
				applyFee(totalOverdraftFee, checkingAccount, savingsAccount);
			}
		}
	}
	
	private void applyBankingFee(AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		double combinedBalance = 0;
		if(checkingAccount != null)
			combinedBalance += checkingAccount.getBalance();
		if(savingsAccount != null)
			combinedBalance += savingsAccount.getBalance();
		if(combinedBalance < getMinNoFeeCombinedBalance()) {
			int bankingFee = getBankingFee();
			applyFee(bankingFee, checkingAccount, savingsAccount);
		}
	}
	
	private void applyFee(final double fee, AbstractCheckingAccount checkingAccount, Account savingsAccount) {
		if(checkingAccount == null) {
			double balance = savingsAccount.getBalance() - fee;
			savingsAccount.setBalance(balance);
		} else if(savingsAccount == null || fee <= checkingAccount.getBalance()) {
			double balance = checkingAccount.getBalance() - fee;
			checkingAccount.setBalance(balance);
		} else { // checking and savings not null and checking balance not enough
			double checkBal = checkingAccount.getBalance();
			double savBal = savingsAccount.getBalance();
			double checkShortage = fee - checkBal;
			double savWith;
			if(checkShortage < savBal)
				savWith = checkShortage;
			else
				savWith = savBal;
			savingsAccount.setBalance(savBal - savWith);
			checkingAccount.deposit(savWith);
			checkingAccount.setBalance(checkingAccount.getBalance() - fee);
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
