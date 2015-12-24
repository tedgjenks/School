package edu.banking.burroughs.lauren;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {
	public SavingsAccount(double balance, double accountInterestAPR){
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
		setNumTransactions(getNumTransactions());
		setNumTransactions(getMaxMonthlyTransactions());
	}

	@Override
	public double deposit(double depositAmount) {
		if(getNumTransactions() >= getMaxMonthlyTransactions())
			return 0.0;
		else{
			setBalance(getBalance() + depositAmount);
			setNumTransactions(getNumTransactions() + 1);
			return depositAmount;
		}
	}
	 
	
	@Override
	public boolean canTransact() {
		if(getNumTransactions() <= getMaxMonthlyTransactions())
			return true;
		else
		   return false;
	}

	@Override
	public void payInterest(int days) {
		double years = days / DAYS_IN_A_YEAR;
		double Interest;
		Interest = getBalance() * Math.pow(Math.E,((getAccountInterestAPR() / 100.0) * (years)));
		setBalance(Interest);
	}

	@Override
	public double transfer(Account arg0, double amount) {
		if(getNumTransactions() < getMaxMonthlyTransactions()){
			if(getBalance() >= amount){
				arg0.setBalance(arg0.getBalance() + amount);
				setBalance(getBalance() - amount);
				setNumTransactions(getNumTransactions() + 1);
				return amount;
			}
			else 
				return 0.0;
	}
		else 
			return 0.0;
		}
	

	@Override
	public double withdraw(double requestedWithdrawal) {

		if(this.getNumTransactions() < this.getMaxMonthlyTransactions()){
			if(requestedWithdrawal <= getBalance()){
				setBalance(getBalance() - requestedWithdrawal);
				setNumTransactions(getNumTransactions() + 1);
				return requestedWithdrawal;
			}
			else{
					return 0.0;
			}
		}
		else{

			return 0.0;
		}
	
	}

}
