package edu.banking.higginbotham.andrew;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount(){
		
	}
	
	public CheckingAccount(double balance){
		
	}
	
	@Override
	public void payInterest(int days) {
		

	}

	@Override
	public double withdraw(double requestedAmount) {
		
		if(getBalance() >= requestedAmount)
		{
			setBalance(getBalance() - requestedAmount);
			return requestedAmount;
		}
		else
			return 0.0;
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
