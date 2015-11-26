package edu.banking.jha.anish;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

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

		double b = getBalance();

		AbstractSavingsAccount l = getLinkedSavingsAccount();

		double lb = 0;

		if(l != null)
			lb = l.getBalance();

		if(rW <= b){
			setBalance(b - rW);
		}

		else if (l != null && Math.abs(rW - b) <= lb && l.canTransact()){

			l.setBalance(lb-(rW-b));

			l.setNumTransactions(l.getNumTransactions() + 1);

			rW = b + (rW - b);

			setBalance(0);
		}

		else if (this.isOverdraftProtected()){

			if(l != null && l.canTransact()){

				b += lb;

				l.setNumTransactions(l.getNumTransactions() + 1);

				l.setBalance(0);
			}

			if(Math.abs(rW - b) <= getOverdraftMax()){

				setNumberOverdrafts(getNumberOverdrafts() + 1);

				setBalance(b - rW);
			}

			else {rW = 0;}

		}

		else{rW = 0;}

		return rW;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

