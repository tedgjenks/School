/**
 * 
 */
package edu.jenks.banking.dist;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractBanking {
	
	private int bankingFee;
	private AbstractCheckingAccount checkingAccount;
	private Account savingsAccount;
	private int minNoFeeCombinedBalance;
	
	/**
	 * @return the minimum combined balance from the checking and savings accounts to avoid a banking fee.
	 */
	public int getMinNoFeeCombinedBalance() {
		return minNoFeeCombinedBalance;
	}

	/**
	 * @param minNoFeeCombinedBalance
	 */
	public void setMinNoFeeCombinedBalance(int minNoFeeCombinedBalance) {
		this.minNoFeeCombinedBalance = minNoFeeCombinedBalance;
	}

	/**
	 * @return
	 */
	public AbstractCheckingAccount getCheckingAccount() {
		return checkingAccount;
	}

	/**
	 * POSTCONDITION: links the existing savings account to this checking account
	 * 
	 * @param checkingAccount
	 */
	public void setCheckingAccount(AbstractCheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
		checkingAccount.setLinkedSavingsAccount(savingsAccount);
	}

	/**
	 * @return
	 */
	public Account getSavingsAccount() {
		return savingsAccount;
	}

	/**
	 * POSTCONDITION: links this savings account to the existing checking account.
	 * 
	 * @param savingsAccount
	 */
	public void setSavingsAccount(Account savingsAccount) {
		this.savingsAccount = savingsAccount;
		if(checkingAccount != null)
			checkingAccount.setLinkedSavingsAccount(savingsAccount);
	}

	/**
	 * @return
	 */
	public int getBankingFee() {
		return bankingFee;
	}

	/**
	 * @param bankingFee
	 */
	public void setBankingFee(int bankingFee) {
		this.bankingFee = bankingFee;
	}
	
	/**
	 * Assesses fees and applies interest to the checking and savings accounts.</br>
	 * The overdraft fee is applied for every overdraft.</br>
	 * If the combined balances are less than the minimum to avoid fees, banking fees are deducted from the checking account.</br>
	 * Continuously compounding interest is applied to each account based on the APR and days. Interest is calculated after fees are assessed.</br>
	 * POSTCONDITION: number of overdrafts for the checking account is set to zero.</br>
	 * 
	 * @param days must be greater than zero
	 */
	public abstract void performMaintenance(int days);

}
