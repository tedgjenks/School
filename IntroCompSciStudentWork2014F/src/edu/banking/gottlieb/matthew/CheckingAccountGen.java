package edu.banking.gottlieb.matthew;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		// TODO Auto-generated constructor stub
	}

	public CheckingAccountGen(double balance) {
		super(balance);
		
	}

	@Override
	public double deposit(double amountDeposited) {
		double newBalance= amountDeposited + getBalance();
		setBalance(newBalance);
		return amountDeposited;
	}
	@Override
	public double transfer(double arg0, Account arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double amountWithdrawn) {
		double balance = getBalance() - amountWithdrawn;
		setBalance(balance);
		if (balance>0){
			return amountWithdrawn;
		}else{
			setBalance(balance + amountWithdrawn);
			System.out.println("2 Big.");
			return 0;
		}
	}
}
