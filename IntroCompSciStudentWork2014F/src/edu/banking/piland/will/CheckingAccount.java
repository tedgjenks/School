package edu.banking.piland.will;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount(double balance, double accountInterestAPR) {
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
		double amountwithdrawn = 0;
		if(requestedWithdrawal <= getBalance()){
			amountwithdrawn = requestedWithdrawal;
		}
		double newBalance = getBalance() - amountwithdrawn;
		setBalance(newBalance);
		return amountwithdrawn;
	}

}
