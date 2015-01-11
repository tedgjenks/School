package edu.banking.clark.jessica;

import edu.jenks.dist.banking.Account; 
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	public CheckingAccountGen() {
		// TODO Auto-generated constructor stub
	}

	public CheckingAccountGen(double balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double deposit(double money) {
		// TODO Auto-generated method stub
		setBalance(getBalance()+money);
		return money;
		
	}

	@Override
	public double transfer(double arg0, Account arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double money) {
		// TODO Auto-generated method stub
		if (getBalance()< money){
			return 0;
		}else {
			setBalance(getBalance()-money);
			return money;
		}
	}
		
}
