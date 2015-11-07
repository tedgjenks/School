package edu.banking.johnson.tatum;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	public CheckingAccount () {
		
	}
	public CheckingAccount(double balance){
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
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
