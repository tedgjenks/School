package edu.jenks.banking.dist;

public abstract class AbstractCheckingAccount extends Account {

	private int overdraftFee;
	private int numberOverdrafts; // reset to zero after fee calculation
	private int overdraftMax;
	private boolean overdraftProtected;
	private Account linkedSavingsAccount;
	
	/**
	 * @return the savings account linked to this checking account
	 */
	public Account getLinkedSavingsAccount() {
		return linkedSavingsAccount;
	}

	public void setLinkedSavingsAccount(Account linkedSavingsAccount) {
		this.linkedSavingsAccount = linkedSavingsAccount;
	}
	
	/**
	 * @return the overdraft fee that will be charged for every overdraft
	 */
	public int getOverdraftFee() {
		return overdraftFee;
	}

	public void setOverdraftFee(int overdraftFee) {
		this.overdraftFee = overdraftFee;
	}

	/**
	 * @return the maximum amount of money that can be overdrawn from the account.
	 */
	public int getOverdraftMax() {
		return overdraftMax;
	}

	public void setOverdraftMax(int overdraftMax) {
		this.overdraftMax = overdraftMax;
	}

	public void setNumberOverdrafts(int numberOverdrafts) {
		this.numberOverdrafts = numberOverdrafts;
	}
	
	public void setOverdraftProtected(boolean overdraftProtected) {
		this.overdraftProtected = overdraftProtected;
	}
	
	/**
	 * @return true if the account may be overdrawn, otherwise false.
	 */
	public boolean isOverdraftProtected() {
		return overdraftProtected;
	}
	
	public int getNumberOverdrafts() {
		return numberOverdrafts;
	}
}
