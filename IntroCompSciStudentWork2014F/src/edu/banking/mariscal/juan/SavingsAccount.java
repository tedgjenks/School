package edu.banking.mariscal.juan;

import edu.jenks.dist.banking.Account;

public class SavingsAccount extends Account {

	public SavingsAccount(){
		
	}
	public SavingsAccount(double balance){
		setBalance(balance);
	}
	@Override
	public void payInterest(int days) {
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
