package edu.banking.piland.will;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {
	
	@Override
	public boolean canTransact() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void payInterest(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		// TODO Auto-generated method stub
		double amountWithdrawn = 0;
		if(requestedWithdrawal <= getBalance())
			amountWithdrawn = requestedWithdrawal;
		double newbalance = getBalance() - amountWithdrawn;
		setBalance(newbalance);
		return amountWithdrawn;
	}

}
