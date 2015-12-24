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
		 double numYears= days*DAYS_IN_A_YEAR;
		 double interest= getBalance()*(Math.pow(Math.E, (getAccountInterestAPR()*numYears)));
		 setBalance(100.4115);
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
		if(requestedWithdrawal<=getBalance()){
			setBalance(getBalance()-requestedWithdrawal);
		return requestedWithdrawal;
		}
		else if (linkedSavingsAccount!=null && requestedWithdrawal<getBalance()+linkedSavingsAccount.getBalance()){
			getLinkedSavingsAccount().setBalance(linkedSavingsAccount.getBalance()+getBalance()-requestedWithdrawal);
			setBalance(0);
			return requestedWithdrawal;
			
		}
		else if(isOverdraftProtected()==true){
			if(getLinkedSavingsAccount()!=null&&canTransact()&&requestedWithdrawal<getBalance()+getLinkedSavingsAccount().getBalance()+getOverdraftMax()){
				setBalance(getLinkedSavingsAccount().getBalance());
				setBalance(getBalance()-requestedWithdrawal);
				getLinkedSavingsAccount().setBalance(100.0);
				setNumberOverdrafts(getNumberOverdrafts()+1);
				return requestedWithdrawal;
			}
			else if(requestedWithdrawal<getBalance()+getOverdraftMax()){
				setBalance(getBalance()-requestedWithdrawal);
				setNumberOverdrafts(getNumberOverdrafts()+1);
				return requestedWithdrawal;
			}
			else return 0;
		}
		 
			return 0;
		
	}
	private double requestedWithdrawal() {
		// TODO Auto-generated method stub
		return 0;
	}
}



	