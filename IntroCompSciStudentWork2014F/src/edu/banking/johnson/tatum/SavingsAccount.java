package edu.banking.johnson.tatum;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;


public class SavingsAccount extends AbstractSavingsAccount {
	public SavingsAccount () {
		
	}
	public SavingsAccount (double balance) {
		setBalance(balance);
	}

	@Override
	public void payInterest(int days) {
		// TODO Auto-generated method stub

	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		if(getBalance() >= requestedWithdrawal){
			setBalance(getBalance() - requestedWithdrawal);
			return requestedWithdrawal;
			}
		else
			return 0.0;
	}
	@Override
	public boolean canTransact() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
