/**
 * 
 */
package edu.jenks.dist.banking;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractSavingsAccount extends Account {
	
	private int maxMonthlyTransactions;
	private int numTransactions;
	
	/**
	 * @return the number of transactions this month
	 */
	public int getNumTransactions() {
		return numTransactions;
	}

	/**
	 * @param numTransactions
	 */
	public void setNumTransactions(int numTransactions) {
		this.numTransactions = numTransactions;
	}

	/**
	 * 
	 */
	public AbstractSavingsAccount() {
		this(0, 0);
	}
	
	/**
	 * @param balance
	 * @param accountInterestAPR
	 */
	public AbstractSavingsAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
	}

	/**
	 * @return the maximum number of monthly transactions
	 */
	public int getMaxMonthlyTransactions() {
		return maxMonthlyTransactions;
	}

	/**
	 * @param maxMonthlyTransactions
	 */
	public void setMaxMonthlyTransactions(int maxMonthlyTransactions) {
		this.maxMonthlyTransactions = maxMonthlyTransactions;
	}
}
