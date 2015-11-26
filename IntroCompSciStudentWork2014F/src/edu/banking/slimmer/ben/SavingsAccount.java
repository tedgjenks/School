package edu.banking.slimmer.ben;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount
extends AbstractSavingsAccount{

	public SavingsAccount(){
		super();
	}
	
	public SavingsAccount(double balance,
            double accountInterestAPR){
		super();
		this.setBalance(balance);
		this.setAccountInterestAPR(accountInterestAPR);
	}
	
	public void payInterest(int days) {
		double exp= (getAccountInterestAPR()*days)/(DAYS_IN_A_YEAR*100);
		setBalance(Math.pow(Math.E,exp)*getBalance());
	}

	@Override
	public double withdraw(double request) {
		if(request>getBalance() || canTransact()==false)
			return 0;
		else{
			setBalance(getBalance()-request);
			this.setNumTransactions(getNumTransactions()+1);
			return request;
		}
	}

	@Override
	public boolean canTransact() {
		boolean num= this.getMaxMonthlyTransactions()>this.getNumTransactions();
		return num;
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		if(arg1>this.getBalance())
			return 0;
		else{
			double exchange=this.withdraw(arg1);
			arg0.deposit(exchange);
			return exchange;
		}
	}
	
	public double deposit(double depositamount){
		if(this.getNumTransactions()<this.getMaxMonthlyTransactions()){
			this.setBalance(getBalance()+depositamount);
			this.setNumTransactions(getNumTransactions()+1);
			return depositamount;
		}
		else
			return 0;
	}

}
