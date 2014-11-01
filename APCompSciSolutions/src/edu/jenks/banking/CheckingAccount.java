package edu.jenks.banking;

import edu.jenks.banking.dist.AbstractCheckingAccount;
import edu.jenks.banking.dist.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	
	/**
	 * 
	 */
	public CheckingAccount() {
		this(0);
	}
	
	/**
	 * @param balance
	 */
	public CheckingAccount(double balance) {
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
		double withdrawal = 0;
		if(requestedWithdrawal <= 0) // no action if requestedWithdrawal is not positive
			return withdrawal;
		
		double balance = getBalance();
		if(requestedWithdrawal <= balance)
			withdrawal = AccountHelper.standardWithdraw(this, requestedWithdrawal);
		else { // handle overdraft
			double withdrawalOverage = requestedWithdrawal - balance;
			Account linkedSavingsAccount = getLinkedSavingsAccount();
			if(linkedSavingsAccount != null) {
				double savingsBalance = linkedSavingsAccount.getBalance();
				double savingsWithdrawal = withdrawalOverage <= savingsBalance ? withdrawalOverage : savingsBalance;
				deposit(linkedSavingsAccount.withdraw(savingsWithdrawal));
				balance = getBalance();
			}
			if(requestedWithdrawal <= balance)
				withdrawal = requestedWithdrawal;
			else if(isOverdraftProtected()) {
				double availableBalance = balance + getOverdraftMax();
				if(requestedWithdrawal <= availableBalance) {
					int numberOverdrafts = getNumberOverdrafts() + 1;
					setNumberOverdrafts(numberOverdrafts);
					withdrawal = requestedWithdrawal;
				}
			}
			balance -= withdrawal;
			setBalance(balance);
		}
		return withdrawal;
	}

}
