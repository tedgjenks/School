package edu.banking.patterson.andrew;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	
	public CheckingAccountGen(){
		
	}
	
	public CheckingAccountGen(double balance){
		super(balance);
	}
	
	@Override
	public double deposit(double depositAmount) {
		double balance= getBalance() + depositAmount;
		setBalance(balance);
		return depositAmount;
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		return 0;
	}

	@Override
	public double withdraw(double withdrawAmount) {
		double balance= getBalance()-withdrawAmount;
		setBalance(balance);
		if(balance>0){
			return(withdrawAmount);
		}else{
			setBalance(balance+withdrawAmount);
			System.out.println("Illegal operation");
			return(0);
		}
	}

}
