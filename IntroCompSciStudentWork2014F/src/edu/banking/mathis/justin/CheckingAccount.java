package edu.banking.mathis.justin;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount (double balance){
		setBalance(balance);
	}
	
	@Override
	public void payInterest(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public double withdraw(double money) {
		double balance = getBalance();
		if (money <= balance){
			balance = balance - money;
		}
		else money = 0;
		setBalance(balance);
		return money;
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
