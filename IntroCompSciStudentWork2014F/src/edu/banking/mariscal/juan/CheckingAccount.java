package edu.banking.mariscal.juan;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	
	public CheckingAccount(){
		
	}
	public CheckingAccount(double balance,
            double accountInterestAPR){
		setAccountInterestAPR(accountInterestAPR);
		setBalance(balance);
	}
	@Override
	public void payInterest(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public double withdraw(double amount) {
		// TODO Auto-generated method stub
		if( amount <= this.getBalance()){
			setBalance(getBalance()-amount);
		}
		else{   amount=0;  }
		return amount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
