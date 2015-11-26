package edu.banking.johnson.tatum;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;


public class SavingsAccount extends AbstractSavingsAccount {
	public SavingsAccount () {

	}
	public SavingsAccount (double balance , double aPR) {
		super (balance , aPR);
	}

	@Override
	public void payInterest(int days) {
		if(getBalance() > 0){
			double newBalance = getBalance() * Math.pow(Math.E, (getAccountInterestAPR()/ 100.0) * (days/DAYS_IN_A_YEAR));
			setBalance(newBalance);
		}

	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		if (canTransact()){
			if(getBalance() >= requestedWithdrawal){
				setBalance(getBalance() - requestedWithdrawal);
				incrementTransactions();
				return requestedWithdrawal;
			}
			else return 0.0;
		}
		else{
			requestedWithdrawal = 0.0;
			return requestedWithdrawal;}

	}

	private void incrementTransactions() {
		setNumTransactions(getNumTransactions() + 1);
	}


	@Override
	public boolean canTransact() {
		if(getNumTransactions() < getMaxMonthlyTransactions()){
			return true;
		}
		else
			return false;
	}

	@Override
	public double transfer(Account destination, double amount) {
		if (canTransact() && (amount < getBalance())){
			setBalance(getBalance() - amount);
			destination.deposit(amount);
			incrementTransactions();
			return amount;
		}
		else
			return 0.0;
	}
	@Override
	public double deposit(double depositAmount) {
		if(canTransact()){
			incrementTransactions();
			return super.deposit(depositAmount);
		}
		else
			return 0.0;
	}

}
