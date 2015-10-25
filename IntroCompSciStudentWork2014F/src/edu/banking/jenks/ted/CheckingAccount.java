package edu.banking.jenks.ted;


import edu.jenks.dist.banking.AbstractCheckingAccount;
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
	 * @param the requested withdrawal amount
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

	@Override
	public double transfer(Account destination, double amount) {
		double transferAmt = 0;
		if(amount <= getBalance())
			transferAmt = AccountHelper.transfer(this, destination, amount);
		return transferAmt;
	}

}
