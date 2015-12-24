package edu.banking.balentine.gryphon;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {
		
	public SavingsAccount(){
	}

	public SavingsAccount(double balance, double accountInterestAPR){
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}

	@Override
	public boolean canTransact() {
		boolean canTransact=true;
		int transThisMonth=getNumTransactions();
		int maxTrans=getMaxMonthlyTransactions();
		if(transThisMonth>=maxTrans)
			canTransact=false;
		return canTransact;
	}

	public double deposit(double depositAmount){
		if(canTransact()==true){
			setBalance(getBalance()+depositAmount);
			setNumTransactions(getNumTransactions()+1);
		}
		else if(canTransact()==false)
			depositAmount=0;
		return depositAmount;
	}

	@Override
	public void payInterest(int days) {
		double bal=getBalance()*(Math.pow(Math.E, ((getAccountInterestAPR()/100)*(days/DAYS_IN_A_YEAR))));
		setBalance(bal);
	}

	@Override
	public double transfer(Account destination, double amount) {
		if(canTransact()==true){
			if(getBalance()<amount)
				amount=0;
			else if(amount<=getBalance() && getNumTransactions()<getMaxMonthlyTransactions()){
				destination.deposit(amount);
				setBalance(getBalance()-amount);
				setNumTransactions(getNumTransactions()+1);
			}
		}
		else
			amount=0;
		return amount;
	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		double bal=getBalance();
		if(canTransact()==true && requestedWithdrawal<=bal){
				setBalance(bal-=requestedWithdrawal);
				setNumTransactions(getNumTransactions()+1);
		}
		else
			requestedWithdrawal=0;
		return requestedWithdrawal;
	}

	public static void main(String[] args) {
	}

}
