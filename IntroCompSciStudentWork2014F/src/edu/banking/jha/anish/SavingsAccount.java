package edu.banking.jha.anish;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {

	public SavingsAccount(double i, double j) {

		super (i,j);

	}

	@Override
	public boolean canTransact() {

		return (getNumTransactions() < getMaxMonthlyTransactions());

	}

	@Override
	public void payInterest(int days) {
		if(getBalance() > 0){
			setBalance(getBalance() * Math.pow (Math.E, getAccountInterestAPR()/100/365*days));

		}
	}
	@Override
	public double transfer(Account destination, double amount) {

		if(withdraw(amount) > 0){
			destination.deposit(amount);
			return amount;
		}

		else return 0;

	}

	@Override
	public double withdraw(double rW) {

		int nt = getNumTransactions();

		int mt = getMaxMonthlyTransactions();

		if(nt < mt && rW < this.getBalance()){
			setBalance(this.getBalance() - rW);
			nt++;
		}

		return rW;
	}

	public static void main(String[] args) {



	}

}
