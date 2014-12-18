package edu.banking.guareschi.marco;


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
	public double deposit(double deposit) {
		double balance = getBalance() + deposit;
		setBalance(balance);
		return deposit;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		return 0;
	}

	@Override
	public double withdraw(double amount) {
		double balance = getBalance() - amount;
		setBalance(balance);
		if(balance > 0 ) {
			return(amount);
		} else { 
			setBalance(balance + amount);
			System.out.println("cant do that");
			return(0);
			
			
		}
		
		
		
	}

}