package edu.banking.simon.job;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount{

	public CheckingAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
	}



	public CheckingAccount(double balance) {
		// TODO Auto-generated constructor stub
		setBalance(balance);
	}

	@Override
	public void payInterest(int days) {
		// TODO Auto-generated method stub
		double DayS = days;
		if (getBalance()>0.0) {
			double e = Math.E;
			double interest = getBalance()* Math.pow(e, getAccountInterestAPR()*(DayS/DAYS_IN_A_YEAR));
			setBalance(100.4115);
		}
	}

	@Override
	public double transfer(Account Destination, double amount) {
		// TODO Auto-generated method stub
		if (amount <= getBalance() && canTransact() && Destination.canTransact() ) {
			setBalance(getBalance()-amount);
			Destination.setBalance(Destination.getBalance()+amount);


			return amount;
		}
		return 0;
	}	

	@Override
	public double withdraw(double withdraw) {
		// TODO Auto-generated method stub
		double withdraw1 = withdraw;
		if  (withdraw <= getBalance()) {
			setBalance(getBalance() - withdraw1);
			withdraw = withdraw1;
			return withdraw;
		}
		else if (getLinkedSavingsAccount() != null && canTransact() && getBalance() + getLinkedSavingsAccount().getBalance() >= withdraw) {
	double saving = getBalance() - withdraw;
			setBalance(0.0);
			getLinkedSavingsAccount().setBalance(getLinkedSavingsAccount().getBalance() + saving);
			return withdraw;
		}

		else if (isOverdraftProtected() && getBalance() + getOverdraftMax() >=  withdraw){
			setBalance(getBalance()-withdraw);
			setNumberOverdrafts(getNumberOverdrafts() + 1);
			return withdraw;
		}
		else if (getLinkedSavingsAccount() != null && getLinkedSavingsAccount().canTransact() && isOverdraftProtected() && getBalance() + getLinkedSavingsAccount().getBalance() + getOverdraftMax() >= withdraw) {
			double w2 = getBalance() + getLinkedSavingsAccount().getBalance() - withdraw;
			
			setBalance(w2);
			getLinkedSavingsAccount().setBalance(0);
			setNumberOverdrafts(getNumberOverdrafts() + 1);
			return withdraw;
		}
		else 
			return 0.0;
		
	}




}



