/**
 * 
 */
package edu.jenks.banking.dist;

/**
 * @author Ted Jenks
 *
 */
public abstract class Account {
	
	private double accountInterestAPR;
	private double balance;
	
	/**
	 * @return the annual percentage rate.
	 */
	public double getAccountInterestAPR() {
		return accountInterestAPR;
	}

	public void setAccountInterestAPR(double accountInterestAPR) {
		this.accountInterestAPR = accountInterestAPR;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * @return the current balance of the account.
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * @param depositAmount this is added to the balance.
	 */
	public void deposit(double depositAmount) {
		balance += depositAmount;
	}

	/**
	 * Continuously compounding interest is applied to this account.
	 * 
	 * @param days
	 */
	public abstract void payInterest(int days);
	
	/**
	 * Withdraw the requested amount from the account.</br>
	 * Overdraft issues are handled based on the type of account.</br>
	 * POSTCONDITIONS: the amount withdrawn will be deducted from the balance.</br>	
	 *  
	 * @param requestedWithdrawal
	 * @return the amount actually withdrawn from the account.
	 */
	public abstract double withdraw(double requestedWithdrawal);
}
