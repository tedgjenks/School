package edu.banking.busbee.hunter;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {

	public SavingsAccount() {
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}

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
	public double withdraw(double requestedWithdrawl) {
		if(requestedWithdrawl > getBalance()){
			requestedWithdrawl = 0;
		}
		setBalance(getBalance() - requestedWithdrawl);
		return requestedWithdrawl;
	
	}

}
