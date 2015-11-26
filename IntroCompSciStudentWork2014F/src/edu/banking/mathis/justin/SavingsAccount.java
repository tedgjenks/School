package edu.banking.mathis.justin;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {

	public SavingsAccount(){
		
	}
	
	public SavingsAccount(double balance){
		setBalance(balance);
	}
	public SavingsAccount (double balance,
            double APR){
		super(balance, APR);
	}
	@Override
	public void payInterest(int days) {
		double a = getAccountInterestAPR()/100.0;
		double b = Math.E;
		double d = getBalance();
		double e = days/DAYS_IN_A_YEAR;
		double c = Math.pow(b, a*e);
		setBalance(c*d);
		

	}

	@Override
	public double withdraw(double money) {
		int a = getNumTransactions();
		double balance = getBalance();
		if (money <= balance && canTransact()){
			balance = balance - money;
			setNumTransactions(a+=1);
		}
		else money = 0;
		setBalance(balance);
		return money;
	}
	

	@Override
	public boolean canTransact() {
		boolean cantransact = true;
		if (getMaxMonthlyTransactions() <= getNumTransactions())
			cantransact = false;
		return cantransact;
	}

	@Override
	public double transfer(Account acc, double amt) {
		double thisbal = this.getBalance();
		if (acc.canTransact() && this.canTransact()){
			if (amt <= thisbal){
				acc.deposit(amt);
				this.withdraw(amt);
			}
			else amt = 0;
		}
		else amt = 0;
		return amt;
	}

	@Override
	public double deposit(double depositAmount) {
		int numtrans = this.getNumTransactions();
		if (this.canTransact()){
			
			this.setNumTransactions(numtrans+1);
			return super.deposit(depositAmount);
		}
		else return 0;
		
	}
	

}
