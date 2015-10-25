/**
 * 
 */
package edu.jenks.dist.banking;

import java.util.ArrayList;
import java.util.List;

/**
 * All customers have a unique name.
 * 
 * @author Ted Jenks
 *
 */
public abstract class AbstractBanking {
	
	private int bankingFee;
	private int minNoFeeCombinedBalance;
	private List<AbstractCustomer> customers = new ArrayList<AbstractCustomer>();
	
	/**
	 * @return
	 */
	public List<AbstractCustomer> getCustomers() {
		return customers;
	}

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
	 * @return the banking fee
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
	 * @param name
	 * @param checkingAccount
	 * @param savingsAccount
	 */
	public abstract void addCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount);
	
	/**
	 * @param name
	 * @return
	 */
	public abstract boolean removeCustomer(String name);
	
	/**
	 * <p>Assesses fees and applies interest to the checking and savings accounts for each customer.</p>
	 * The overdraft fee is applied for every overdraft.<br>
	 * If the combined balances are less than the minimum to avoid fees, banking fees are deducted from the checking account.<br>
	 * Continuously compounding interest is applied to each account based on the APR and days. Interest is calculated after fees are assessed.<br>
	 * Maintenance transactions do not count toward transaction limits.<br>
	 * PRECONDITION: days is greater than zero.<br>
	 * POSTCONDITION: number of overdrafts for the checking account is set to zero.<br>
	 * 
	 * @param days
	 */
	public abstract void performMaintenance(int days);

}
