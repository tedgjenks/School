package edu.banking.ruhoff.brooke;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

public CheckingAccount(){
		
	}
	public CheckingAccount(double balance, double accountInterestAPR){
		super(balance, accountInterestAPR);
	}

	@Override
	public void payInterest(int days) {
		if(getBalance()>0){
			double numYears=days*DAYS_IN_A_YEAR;
			double interest=getBalance()*(Math.pow(Math.E, (getAccountInterestAPR()*numYears)));
			setBalance(interest);
		}
	}
		
	@Override
	public double withdraw(double requestedWithdrawal) {
		AbstractSavingsAccount linkedSavings=getLinkedSavingsAccount();
		if(requestedWithdrawal<=getBalance()){
			setBalance(getBalance()-requestedWithdrawal);
			return requestedWithdrawal;
		}
		else if(getLinkedSavingsAccount()!=null&&requestedWithdrawal<=getLinkedSavingsAccount().getBalance()+getBalance()&&canTransact()){
			double checkingDeficit=getBalance()-requestedWithdrawal;
			System.out.println(checkingDeficit);
			double newSavingsBalance=getLinkedSavingsAccount().getBalance()+checkingDeficit;
			System.out.println(newSavingsBalance);
			getLinkedSavingsAccount().setBalance(newSavingsBalance);
			setBalance(0);
			return requestedWithdrawal;
		}
		else if(isOverdraftProtected()==true){
			if(getLinkedSavingsAccount()!=null&&canTransact()){
				setBalance(getLinkedSavingsAccount().getBalance()+getBalance());
				setBalance(getBalance()-requestedWithdrawal);
				getLinkedSavingsAccount().setBalance(0);
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
		else return 0;
	}
	@Override
	public double transfer(Account destination, double amount) {
		if(amount<=getBalance()){
			setBalance(getBalance()-amount);
			destination.setBalance(destination.getBalance()+amount);
			return amount;
		}
		return 0;
	}
	
}
