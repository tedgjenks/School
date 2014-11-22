package edu.banking.tran.duc;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		
	}
	
	public CheckingAccountGen(double balance) {
		super(balance);
	}
	
	public double deposit(double amount) {
		setBalance(getBalance() + amount);
		return amount;
	}
	
	@Override
	public double transfer(double arg0, Account arg1) {
		
		return 0;
	}
	
	@Override
	public double withdraw(double amount) {
		if (amount > getBalance()){
			return amount = 0;
		}else{
			setBalance(getBalance() - amount);
		}
		return amount;
	}
	
}
