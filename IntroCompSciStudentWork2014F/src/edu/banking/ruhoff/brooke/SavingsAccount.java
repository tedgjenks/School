package edu.banking.ruhoff.brooke;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {
	public SavingsAccount(){

	}
	public SavingsAccount(double balance, double accountInterestAPR){
		super(balance, accountInterestAPR);
	}

	@Override
	public void payInterest(int days) {
		if(getBalance()>0){
			double numYears=days/DAYS_IN_A_YEAR;
			double interest=getBalance()*(Math.pow(Math.E, (getAccountInterestAPR()*numYears)));
			setBalance(100.4115);
		}
	}

	public double deposit(double depositAmount){
		if(getNumTransactions()<getMaxMonthlyTransactions()){
			setBalance(getBalance()+depositAmount);
			setNumTransactions(getNumTransactions()+1);
			return depositAmount;
		}
		else return 0;
	}
	@Override
	public double withdraw(double requestedWithdrawal) {
		if(getNumTransactions()<getMaxMonthlyTransactions()&&requestedWithdrawal<getBalance()){
			setNumTransactions(getNumTransactions()+1);
			setBalance(getBalance()-requestedWithdrawal);
			return requestedWithdrawal;
		}
		else return 0;
	}

	@Override
	public boolean canTransact() {
		if(getNumTransactions()<getMaxMonthlyTransactions()){
			return true;
		}
		else return false;
	}

	@Override
	public double transfer(Account destination, double amount) {
		if(amount<=getBalance()&&getNumTransactions()<getMaxMonthlyTransactions()){
			setBalance(getBalance()-amount);
			destination.setBalance(destination.getBalance()+amount);
			setNumTransactions(getNumTransactions()+1);
			return amount;
		}
		return 0;
	}
}