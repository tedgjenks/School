package edu.banking.mathis.justin;

import edu.jenks.dist.banking.Account;

public class SavingsAccount extends Account {

	public SavingsAccount(){
		
	}
	
	public SavingsAccount(double balance){
		setBalance(balance);
	}
	
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
