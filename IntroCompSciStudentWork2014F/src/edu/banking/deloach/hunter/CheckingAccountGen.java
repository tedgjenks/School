package edu.banking.deloach.hunter;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		super(0);
	}
	
	public CheckingAccountGen(double balance) {
		
		super(balance);
	}
	
	@Override
	public double deposit(double depositamount) {
		double balance=depositamount+getBalance();
		setBalance(balance);
		return depositamount;
	}

	@Override
	public double transfer(double balance, Account transferto) {
		if(withdraw(balance)>0){
			transferto.deposit(balance);
			return balance;
		}
		else{
			return 0;
		}
	}

	@Override
	public double withdraw(double withdrawamount) {
		double balance=getBalance()-withdrawamount;
		if (balance<0){
			return 0;
		}
		else{
			setBalance(balance);
			return withdrawamount;
		}
	}

}
