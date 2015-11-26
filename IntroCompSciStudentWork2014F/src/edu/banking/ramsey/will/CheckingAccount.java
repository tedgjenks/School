package edu.banking.ramsey.will;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	public CheckingAccount(){
	}
	public CheckingAccount(double balance, double accountInterestAPR){
		this.setBalance(balance);
		this.setAccountInterestAPR(accountInterestAPR);
	}

	@Override
	public void payInterest(int arg0) {
		// TODO Auto-generated method stub
		double years = arg0/Account.DAYS_IN_A_YEAR;
		double yearlyRate = getAccountInterestAPR() / 100;
		setBalance(getBalance() * Math.pow(Math.E,(yearlyRate * years)));
	}

	@Override
	public double withdraw(double arg0) {
		// TODO Auto-generated method stub
		double withdrawn = 0;
		boolean done = false;
		SavingsAccount savingsBalance = (SavingsAccount) getLinkedSavingsAccount();
		if(getBalance() >= arg0){
			setBalance(getBalance() - arg0);
			withdrawn = arg0;
			done = true;
		}
		if(getLinkedSavingsAccount() != null && getLinkedSavingsAccount().canTransact() && !done){
			if(getLinkedSavingsAccount().getBalance() + getBalance() >= arg0){
					withdrawn = arg0;
					double withdrawLinked = arg0 - getBalance();
					savingsBalance.setBalance(savingsBalance.getBalance() - withdrawLinked);
					savingsBalance.setNumTransactions(savingsBalance.getNumTransactions() + 1);
					setBalance(0);
					done = true;
			}
			else if(isOverdraftProtected() && getLinkedSavingsAccount().getBalance() + getBalance() + getOverdraftMax() >= arg0){
				withdrawn = arg0;
				arg0 -= savingsBalance.getBalance();
				savingsBalance.setBalance(0);
				savingsBalance.setNumTransactions(savingsBalance.getNumTransactions() + 1);
				setBalance(getBalance() - arg0);
				setNumberOverdrafts(getNumberOverdrafts() + 1);
				done = true;
			}
		}
		if(isOverdraftProtected() && getBalance() + getOverdraftMax() >= arg0 && !done){
			withdrawn = arg0;
			setBalance(getBalance() - arg0);
			setNumberOverdrafts(getNumberOverdrafts() + 1);
		}
		return withdrawn;
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		double transfered = 0;
		if(getBalance() >= arg1 && arg1 != 0 && arg0.canTransact() && canTransact()){
			transfered = arg1;
			arg0.deposit(arg1);
		}	
		return transfered;
	}
}
