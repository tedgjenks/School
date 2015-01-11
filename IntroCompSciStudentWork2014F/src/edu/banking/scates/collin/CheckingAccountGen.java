package edu.banking.scates.collin;

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
	public double deposit(double amountdeposit) {
		double newBalance = getBalance() + amountdeposit ;
		setBalance (newBalance);
		return amountdeposit;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		return 0;
	}

	@Override
	public double withdraw(double amountWithdrawn) {
		double newbalence = getBalance() - amountWithdrawn;
		setBalance(newbalence);
		if(newbalence > 0){
			return(amountWithdrawn);
		}else{
			setBalance(newbalence + amountWithdrawn);
			System.out.println("Can Not Happen.");
			return (0);
		}
	}

}