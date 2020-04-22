package edu.banking.jenks.ted;


import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	
	/**
	 * 
	 */
	public CheckingAccount() {
		this(0, 0);
	}
	
	/**
	 * @param balance
	 * @param accountInterestAPR
	 */
	public CheckingAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
	}



	/* (non-Javadoc)
	 * @see jenks.unit4.Account#payInterest(int)
	 */
	@Override
	public void payInterest(int days) {
		AccountHelper.payInterest(this, days);
	}

	/**
	 * <p>Attempt to withdraw from this account.</p>
	 * If the withdrawal is greater than the balance, attempt to withdraw the difference from a linked savings account.<br>
	 * If the withdrawal is greater than the combined balance, attempt to cover the request with an overdraft.
	 * If the request cannot be satisfied, no transactions should take place.
	 * @param requestedWithdrawal the requested withdrawal amount
	 */
	@Override
	public double withdraw(double requestedWithdrawal) {
		double withdrawal = 0;
		// no action if requestedWithdrawal is not positive or it is more than the total available funds
		if(requestedWithdrawal > 0 && requestedWithdrawal <= totalFunds()) {
			withdrawal = requestedWithdrawal;
			double balance = getBalance();
			if(requestedWithdrawal > balance) {
				double withdrawalOverage = requestedWithdrawal - balance;
				AbstractSavingsAccount linkedSavingsAccount = getLinkedSavingsAccount();
				if(linkedSavingsAccount != null) {
					double savingsBalance = linkedSavingsAccount.getBalance();
					double savingsWithdrawal = withdrawalOverage <= savingsBalance ? withdrawalOverage : savingsBalance;
					deposit(linkedSavingsAccount.withdraw(savingsWithdrawal));
					balance = getBalance();
				}
				if(requestedWithdrawal > balance) {
					assert isOverdraftProtected() && requestedWithdrawal - balance <= getOverdraftMax() : "Overdraft not sufficient!";
					setNumberOverdrafts(getNumberOverdrafts() + 1);
				}
			}
			balance -= withdrawal;
			setBalance(balance);
		}
		return withdrawal;
	}
	
	private double totalFunds() {
		double funds = getBalance();
		AbstractSavingsAccount linkedSavingsAccount = getLinkedSavingsAccount();
		if(linkedSavingsAccount != null && linkedSavingsAccount.canTransact())
			funds += linkedSavingsAccount.getBalance();
		if(isOverdraftProtected())
			funds += getOverdraftMax();
		return funds;
	}

	@Override
	public double transfer(Account destination, double amount) {
		double transferAmt = 0;
		if(amount <= getBalance())
			transferAmt = AccountHelper.transfer(this, destination, amount);
		return transferAmt;
	}

}
