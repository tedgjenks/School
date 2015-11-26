package edu.banking.slimmer.ben;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount
extends AbstractCheckingAccount{

	
	public CheckingAccount(){
		super();
	}
	
	public CheckingAccount(double balance, double accountInterestAPR){
		super(balance,accountInterestAPR);
	}
	
	@Override
	public void payInterest(int days) {
		double exp= (getAccountInterestAPR()*days)/(DAYS_IN_A_YEAR*100);
		setBalance(Math.pow(Math.E,exp)*getBalance());
	}

	@Override
	public double withdraw(double requested) {
		if(requested<=this.getBalance()){
			setBalance(getBalance()-requested);
			return requested;
		}
		double addup=this.getBalance();
		if(this.getLinkedSavingsAccount()!=null && this.getLinkedSavingsAccount().canTransact()){
			addup+= getLinkedSavingsAccount().getBalance();
		}
		if (addup>=requested){
			this.getLinkedSavingsAccount().withdraw(requested-this.getBalance());
			this.setBalance(0);
			return requested;
		}
		if(isOverdraftProtected()==true && (requested-addup)<=getOverdraftMax()){
			double requestnew=requested;
			if(this.getLinkedSavingsAccount()!=null && this.getLinkedSavingsAccount().canTransact()){
				requestnew=requested-this.getLinkedSavingsAccount().getBalance();
				this.getLinkedSavingsAccount().withdraw(this.getLinkedSavingsAccount().getBalance());
			}
			this.setNumberOverdrafts(getNumberOverdrafts()+1);
			this.setBalance(getBalance()-requestnew);
			return requested;
		}
		else
			return 0;
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

}
