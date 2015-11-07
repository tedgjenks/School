package edu.banking.wicker.marshall;

import edu.jenks.dist.banking.*;

public class CheckingAccount extends AbstractCheckingAccount{
	public CheckingAccount (){
		super();
		setBalance(0);
		setAccountInterestAPR(0);
	}
	
	public CheckingAccount (double balance, double interestRate){
		super();
		setBalance(balance);
		setAccountInterestAPR(interestRate);
	}

	public void payInterest(int days) {
		if (this.getBalance() > 0){
			this.setBalance(this.getBalance() * Math.pow(Math.E , (this.getAccountInterestAPR() / 100.0) * (days / 365.0)));
		}
	}

	public double withdraw(double requestedWithdrawal) {
		if (this.getBalance() >= requestedWithdrawal){
			return doSimpleWithdraw(requestedWithdrawal);
		}
		else{
			if (this.hasLinkedSavings()){
				return withdrawFromSavings(requestedWithdrawal);
			}
			else {
				if (this.isOverdraftProtected()){
					return doOverdraftWithdraw(requestedWithdrawal);
				}
				else {
					this.setBalance(this.getBalance() - requestedWithdrawal);
					incrementOverdrafts();
					return requestedWithdrawal;
				}
			}
		}
	}
	
	private void incrementOverdrafts(){
		this.setNumberOverdrafts(getNumberOverdrafts() + 1);
	}

	private double doOverdraftWithdraw(double requestedWithdrawal) {
		double amountShort = requestedWithdrawal - this.getBalance();

		if (amountShort <= this.getOverdraftMax()){
			this.setNumberOverdrafts(this.getNumberOverdrafts() + 1);
			return doSimpleWithdraw(requestedWithdrawal);
		}
		else {
			return 0.0;
		}
	}

	private double withdrawFromSavings(double requestedWithdrawal) {
		Account LSA = getLinkedSavingsAccount();
		double amountShort = requestedWithdrawal - this.getBalance();
		if (LSA.getBalance() >= amountShort){
			this.setBalance(0);
			LSA.withdraw(amountShort);
			return requestedWithdrawal;
		}
		else{
			return 0.0;
		}
	}

	private boolean hasLinkedSavings() {
		return getLinkedSavingsAccount() != null;
	}

	private double doSimpleWithdraw(double requestedWithdrawal) {
		this.setBalance(this.getBalance()-requestedWithdrawal);
		return requestedWithdrawal;
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
}
