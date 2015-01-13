package edu.banking.stevens.reid;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		
	}

	public CheckingAccountGen(double balance) {
		super(balance);
	}

	@Override
	public double deposit(double input) {
		setBalance(getBalance()+input); 
		return input;
	}

	@Override
	public double transfer(double changeAmount, Account toTransfer) {
		if(withdraw(changeAmount) == changeAmount) 
			toTransfer.deposit(changeAmount);
		else
			return 0;
		return changeAmount;
	}

	@Override
	public double withdraw(double withdrawAmount) {
		if(getBalance()>=withdrawAmount) {
			setBalance(getBalance()-withdrawAmount);
			return withdrawAmount;
		}
			
		return 0;
	}

}
