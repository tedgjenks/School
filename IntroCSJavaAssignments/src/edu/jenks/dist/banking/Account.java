package edu.jenks.dist.banking;
/**
 * 
 */


/**
 * @author Ted Jenks
 *
 */
public abstract class Account {
	
	private double accountInterestAPR;
	private double balance;
	
	/**
	 * 
	 */
	public Account() {
		this(0, 0);
	}
	
	/**
	 * @param balance
	 * @param accountInterestAPR
	 */
	public Account(double balance, double accountInterestAPR) {
		this.balance = balance;
		this.accountInterestAPR = accountInterestAPR;
	}
	
	/**
	 * @return the annual percentage rate.
	 */
	public double getAccountInterestAPR() {
		return accountInterestAPR;
	}

	/**
	 * @param accountInterestAPR
	 */
	public void setAccountInterestAPR(double accountInterestAPR) {
		this.accountInterestAPR = accountInterestAPR;
	}

	/**
	 * @param balance
	 */
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
	 * @return
	 */
	public double deposit(double depositAmount) {
		balance += depositAmount;
		return depositAmount;
	}

	/**
	 * Continuously compounding interest is applied to this account.
	 * 
	 * @param days
	 */
	public abstract void payInterest(int days);
	
	/**
	 * <p>Withdraw the requested amount from the account.</p>
	 * POSTCONDITIONS: the amount withdrawn will be deducted from the balance.<br>	
	 *  
	 * @param requestedWithdrawal
	 * @return the amount actually withdrawn from the account.
	 */
	public abstract double withdraw(double requestedWithdrawal);
	
	/**
	 * <p>Transfer the amount from this Account to the destination Account.</p>
	 * If the balance of this account is less than amount, it should not attempt to transfer funds from any other source.<br>
	 * @param destination
	 * @param amount
	 * @return the amount transfered
	 */
	public abstract double transfer(Account destination, double amount);
	
	public abstract boolean canTransact();
}
