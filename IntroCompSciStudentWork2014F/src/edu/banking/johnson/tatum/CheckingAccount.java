package edu.banking.johnson.tatum;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	public CheckingAccount () {

	}
	public CheckingAccount(double balance , double aPR){
		super(balance , aPR);
	}
	@Override
	public void payInterest(int days) {
		if(getBalance() > 0){
			double newBalance = getBalance() * Math.pow(Math.E, (getAccountInterestAPR() / 100.0) * (days/DAYS_IN_A_YEAR));
			setBalance(newBalance);
		}
	}

	private double simpleWithdrawal(double requestedWithdrawal){
		setBalance(getBalance() - requestedWithdrawal);
		return requestedWithdrawal;
	}

	private boolean hasLSA(){
		return this.getLinkedSavingsAccount() != null;
	}

	private double withdrawalLSA(double requestedWithdrawal){
		double shortage =  requestedWithdrawal - getBalance();
		AbstractSavingsAccount lSA = getLinkedSavingsAccount(); 
		if(lSA.withdraw(shortage) != 0){
			setBalance(0.0);}
		return requestedWithdrawal;
	}	


	private double helperOverdraftLSA(double requestedWithdrawal , AbstractSavingsAccount lSA){
		if (isOverdraftProtected()){
			if (getOverdraftMax() + getBalance()  +  lSA.getBalance() >= requestedWithdrawal){
				double overdraftAmt = getBalance() - (requestedWithdrawal - lSA.getBalance());
				lSA.withdraw(lSA.getBalance());
				setBalance(overdraftAmt);
				incrementOverdraft(requestedWithdrawal);
				return requestedWithdrawal;
			}
			else
				return 0.0;
		}	

		else {
			return 0.0;
		}	
	}

	private double helperSimpleOverdraft(double requestedWithdrawal){
		if (getBalance() + getOverdraftMax() >= requestedWithdrawal){
			setBalance(getBalance() - requestedWithdrawal);
			incrementOverdraft(requestedWithdrawal);
			return requestedWithdrawal;
		}
		else
			return 0.0;
	}

	private void incrementOverdraft(double requestedWithdrawal){
		setNumberOverdrafts(getNumberOverdrafts() + 1);
	}

	@Override
	public double withdraw(double requestedWithdrawal) {

		if(getBalance() >= requestedWithdrawal){
			return simpleWithdrawal(requestedWithdrawal);
		}
		else if(hasLSA() && getLinkedSavingsAccount().getBalance() + getBalance() >= requestedWithdrawal){
			return withdrawalLSA(requestedWithdrawal);	
		}
		else if (hasLSA()){
			return helperOverdraftLSA(requestedWithdrawal, getLinkedSavingsAccount());
		}
		else if(!hasLSA()){
			return helperSimpleOverdraft(requestedWithdrawal);
		}
		else 
			return 0.0;

	}

	@Override
	public double transfer(Account destination, double amount) {
		if(amount < getBalance() &&  destination.canTransact()){
			setBalance(getBalance() - amount);
			destination.deposit(amount);
			return amount;
		}
		else 
			return 0.0;
	}

}
