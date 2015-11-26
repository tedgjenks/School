package edu.banking.balentine.gryphon;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	
	public CheckingAccount(){
	}
	
	public CheckingAccount(double balance, double accountInterestAPR){
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}

	@Override
	public void payInterest(int days) {
		double bal=getBalance()*(Math.pow(Math.E, (getAccountInterestAPR()*(days/DAYS_IN_A_YEAR))));
		setBalance(bal);
	}

	@Override
	public double transfer(Account destination, double amount) {
		if(getBalance()<amount)
			amount=0;
		else if(destination.canTransact()==true){
			destination.deposit(amount);
			setBalance(getBalance()-amount);
		}
		return amount;
	}
	
	private boolean help(double requestedWithdrawal) {
		boolean help=false;
		double checkBal=getBalance();
		AbstractSavingsAccount savAcc=getLinkedSavingsAccount();
		double savBal=0;
		if(savAcc==null)
			savBal=0;
		else if(savAcc!=null)
			savBal=savAcc.getBalance();
		double overdraftMax=0;
		if(isOverdraftProtected()==true)
			overdraftMax=getOverdraftMax();
		double totalFunds=checkBal+savBal+overdraftMax;
		if(totalFunds>=requestedWithdrawal)
			help=true;
		return help;
	}
	
	@Override
	public double withdraw(double requestedWithdrawal) {
		if(help(requestedWithdrawal)==true){
			double checkBal=getBalance();
			double savBal=0;
			double overdraftMax=0;
			AbstractSavingsAccount savAcc=getLinkedSavingsAccount();
			if(savAcc!=null){
				if(savAcc.getNumTransactions()<savAcc.getMaxMonthlyTransactions())
					savBal=savAcc.getBalance();
				else if(savAcc.getNumTransactions()>=savAcc.getMaxMonthlyTransactions())
					savBal=0;
			}
			else if(savAcc==null)
				savBal=0;
			if(isOverdraftProtected()==true)
				overdraftMax=getOverdraftMax();
			else if(isOverdraftProtected()==false)
				overdraftMax=0;
			double checkSavBal=checkBal+savBal;
			double totalBal=checkSavBal+overdraftMax;
			if(requestedWithdrawal<=checkBal)
				checkBal-=requestedWithdrawal;
			else if(requestedWithdrawal>checkBal && requestedWithdrawal<=checkSavBal){
				double leftOver=checkSavBal-requestedWithdrawal;
				checkBal=0;
				savBal=leftOver;
			}
			else if(requestedWithdrawal>checkSavBal && requestedWithdrawal<=totalBal){
				checkBal+=savBal;
				savBal=0;
				checkBal-=requestedWithdrawal;
				setNumberOverdrafts(getNumberOverdrafts()+1);
			}
			else if(requestedWithdrawal>totalBal)
				requestedWithdrawal=0;
			setBalance(checkBal);
			if(savAcc!=null && savAcc.getNumTransactions()<savAcc.getMaxMonthlyTransactions())
				savAcc.setBalance(savBal);
		}
		else if(help(requestedWithdrawal)==false)
			requestedWithdrawal=0;
		return requestedWithdrawal;
	}

	public static void main(String[] args) {
	}

}
