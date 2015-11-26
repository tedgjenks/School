package edu.banking.collier.serenity;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount(double balance, double accountInterestAPR){
		super (balance, accountInterestAPR);
	}
	@Override
	public void payInterest(int days) {
		boolean Transact= false; 
		 double numYears= days*DAYS_IN_A_YEAR;
		 double interest= getBalance()*(Math.pow(Math.E, (getAccountInterestAPR()*numYears)));
		 setBalance(interest);
	}

	@Override
	public double transfer(Account destination, double amount) {
		if (getBalance()<amount){
			setBalance(getBalance()-amount);
			destination.setBalance(destination.getBalance()+amount);
			return amount; 
		}
		else if (getBalance()>=amount){
			setBalance(getBalance()-amount);
			return amount;
		}
		else 
			return 0;	
	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		AbstractSavingsAccount linkedSavingsAccount= getLinkedSavingsAccount();
		if (requestedWithdrawal()<=getBalance()){
			return 0; 
		}
		else if (requestedWithdrawal()>getBalance()){
			setNumberOverdrafts(getNumberOverdrafts());
		}
		else if (requestedWithdrawal()>getBalance()){
			setOverdraftMax(getOverdraftMax()-getBalance());
		}
		else if(requestedWithdrawal<=getBalance()){
			setBalance(getBalance()-requestedWithdrawal);
		return requestedWithdrawal;
		}
		else if (linkedSavingsAccount!=null && requestedWithdrawal<getBalance()+linkedSavingsAccount.getBalance()){
			getLinkedSavingsAccount().setBalance(linkedSavingsAccount.getBalance()+getBalance()-requestedWithdrawal);
			setBalance(0);
			return requestedWithdrawal;
		}
		else 
			return 0;
		return requestedWithdrawal;
		
	}
	private double requestedWithdrawal() {
		// TODO Auto-generated method stub
		return 0;
	}
}



	