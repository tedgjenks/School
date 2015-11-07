package edu.banking.wicker.marshall;

import edu.jenks.dist.banking.*;

public class SavingsAccount extends AbstractSavingsAccount{
	public SavingsAccount (){
		super();
		setBalance(0);
		setAccountInterestAPR(0);
	}
	
	public SavingsAccount (double balance, double interestRate){
		super();
		setBalance(balance);
		setAccountInterestAPR(interestRate);
	}

	public void payInterest(int days) {
		if (this.getBalance() > 0){
			this.setBalance(this.getBalance() * Math.pow(Math.E , ((this.getAccountInterestAPR() / 100.0) * (days / 365.0))));
		}
	}

	public double withdraw(double requestedWithdrawal) {
		if (this.canTransact() && this.getBalance() >= requestedWithdrawal){
			incrementTransactions();
			this.setBalance(this.getBalance() - requestedWithdrawal);
			return requestedWithdrawal;
		}
		else{
			return 0.0;
		}
	}

	@Override
	public boolean canTransact() {
		return this.getNumTransactions() < this.getMaxMonthlyTransactions();
	}

	@Override
	public double transfer(Account transferTo, double amount) {
		if (this.withdraw(amount) != 0){
			transferTo.deposit(amount);
			return amount;
		}
		else{
			return 0;
		}
	}
	
	private void incrementTransactions (){
		this.setNumTransactions(getNumTransactions() + 1);
	}
}
