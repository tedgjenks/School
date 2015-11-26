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
			if (this.hasLinkedSavings() && this.getLinkedSavingsAccount().getBalance() + getBalance() >= requestedWithdrawal){
				return withdrawFromSavings(requestedWithdrawal);
			}
			else {
				if (this.hasLinkedSavings())
					return handleOverdraft(requestedWithdrawal, this.getLinkedSavingsAccount());
				else
					return handleOverdraft(requestedWithdrawal);
			}
		}
	}
	
	private double handleOverdraft(double requestedWithdrawal, AbstractSavingsAccount linkedSavingsAccount) {
		if (requestedWithdrawal - getBalance() - linkedSavingsAccount.getBalance() <= getOverdraftMax()){
			if (linkedSavingsAccount.canTransact()){
				incrementOverdrafts();
				requestedWithdrawal -= linkedSavingsAccount.getBalance();
				linkedSavingsAccount.setBalance(0);
				this.setBalance(getBalance() - requestedWithdrawal);
				return requestedWithdrawal;
			}
			else{
				return handleOverdraft(requestedWithdrawal);
			}
		}
		else {
			return 0.0;
		}
	}

	private double handleOverdraft(double requestedWithdrawal) {
		if (requestedWithdrawal - getBalance() <= getOverdraftMax()){
			incrementOverdrafts();
			this.setBalance(getBalance() - requestedWithdrawal);
			return requestedWithdrawal;
		}
		else {
			return 0.0;
		}
	}

	private void incrementOverdrafts(){
		this.setNumberOverdrafts(getNumberOverdrafts() + 1);
	}

	private double withdrawFromSavings(double requestedWithdrawal) {
		Account LSA = getLinkedSavingsAccount();
		double amountShort = requestedWithdrawal - this.getBalance();
		if (LSA.withdraw(amountShort) != 0){
			this.setBalance(0);
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
		this.setBalance(this.getBalance() - requestedWithdrawal);
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
