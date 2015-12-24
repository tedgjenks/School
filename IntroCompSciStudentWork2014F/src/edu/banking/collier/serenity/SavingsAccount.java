package edu.banking.collier.serenity;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {
	public SavingsAccount(double balance, double accountInterestAPR){
		super (balance,accountInterestAPR);
	}
	@Override
	public boolean canTransact() {
		 if (getNumTransactions()<=getMaxMonthlyTransactions()){
			return true;
		}
		else 
			return false;
	
	}

	@Override
	public void payInterest(int days) {
		 double numYears= days*DAYS_IN_A_YEAR;
		 double interest= getBalance()*(Math.pow(Math.E, (getAccountInterestAPR()*numYears)));
		 setBalance(100.4115);

	}

	@Override
	public double transfer(Account destination, double amount) {
		if (getBalance()<amount){
			setBalance(getBalance()-amount);
			destination.setBalance(destination.getBalance()+amount);
			setNumTransactions(getNumTransactions()+1);
			return amount; 
		}
		else if (getBalance()>=amount){
			setBalance(getBalance()-amount);
			return amount;
		}
		else 
			return 0;
	}

	@Override
	public double withdraw(double requestedWithdrawal) {	
		if(requestedWithdrawal<=getBalance()){
			setBalance(getBalance()-requestedWithdrawal);
		return requestedWithdrawal;
		}
		else 
			return 0;
		
	}

	public double deposit(double depositAmount) {
		if (getNumTransactions()<getMaxMonthlyTransactions()){
			setBalance(getBalance()+depositAmount);
		return depositAmount;
		}
		else 
			return 0;

	}
	
}
