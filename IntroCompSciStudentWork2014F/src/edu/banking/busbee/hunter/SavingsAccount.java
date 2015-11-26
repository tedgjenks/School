package edu.banking.busbee.hunter;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {

	public SavingsAccount() {
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
		
	}

	@Override
	public boolean canTransact() {
		boolean answer = false;
		if(getNumTransactions() < getMaxMonthlyTransactions()){
			answer = true;
		}
		return answer;
	}

	@Override
	public void payInterest(int days) {
		double e = Math.E;
		double aPR = getAccountInterestAPR();
		double accountBalance = getBalance()* Math.pow(e, (aPR * (days / DAYS_IN_A_YEAR)));
		setBalance(accountBalance);

	}

	@Override
	public double transfer(Account destination, double amount) {
		double balance = getBalance();
		if(canTransact() && balance >= amount){
			this.withdraw(amount);
			setBalance(balance - amount);
			destination.deposit(amount);
		}
		else {
			amount = 0;
		}
		return amount;
	}

	@Override
	public double withdraw(double requestedWithdrawl) {
		double balance = getBalance();
		if(requestedWithdrawl <= balance){
			if(getNumTransactions() < getMaxMonthlyTransactions()){
				setBalance(balance - requestedWithdrawl);
			}
			else{
				setBalance(balance);
				requestedWithdrawl = 0;
			}
		}
		else {
			requestedWithdrawl = 0;
			setBalance(balance);
		}

		return requestedWithdrawl;
	}



}
