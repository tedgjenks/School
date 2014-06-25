package edu.jenks.banking;

import edu.jenks.banking.dist.Account;

public class SavingsAccount extends Account {
	
	/**
	 * 
	 */
	public SavingsAccount() {
		this(0);
	}
	
	/**
	 * @param balance
	 */
	public SavingsAccount(double balance) {
		setBalance(balance);
	} 

	/* (non-Javadoc)
	 * @see jenks.unit4.Account#payInterest(int)
	 */
	@Override
	public void payInterest(int days) {
		AccountHelper.payInterest(this, days);
	}

	/* (non-Javadoc)
	 * @see jenks.unit4.Account#withdraw(double)
	 */
	@Override
	public double withdraw(double requestedWithdrawal) {
		return AccountHelper.standardWithdraw(this, requestedWithdrawal);
	}

}
