package edu.banking.mariscal.juan;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {


	public CheckingAccount(){

	}
	public CheckingAccount(double balance,
			double accountInterestAPR) {
		setAccountInterestAPR(accountInterestAPR);
		setBalance(balance);
	}
	@Override
	public void payInterest(int days) {
		// TODO Auto-generated method stub
		if(getBalance()> 0)
		setBalance(getBalance() * Math.pow(Math.E, ((this.getAccountInterestAPR()/100) * (days/DAYS_IN_A_YEAR))));
	}

	@Override
	public double withdraw(double amount) {
		// TODO Auto-generated method stub
		double balance = getBalance();

		AbstractSavingsAccount link = getLinkedSavingsAccount();  
		double linkbalance=0;
		if(link!= null)
			linkbalance=link.getBalance();

		if( amount <= balance){
			setBalance(balance-amount);
		}
		else if (link != null && Math.abs(amount-balance)<= linkbalance && link.canTransact()){

			link.setBalance(linkbalance-(amount-balance));
			link.setNumTransactions(link.getNumTransactions() + 1);
			amount = balance + (amount-balance);
			setBalance(0);
		}
		else if (this.isOverdraftProtected())   {
			if(link != null && link.canTransact()){
				balance += linkbalance;
				link.setNumTransactions(link.getNumTransactions() + 1);
				link.setBalance(0);
			}
			if(Math.abs(amount- balance) <= getOverdraftMax()){
				setNumberOverdrafts(getNumberOverdrafts()+1);
				setBalance(balance-amount);	
			}
			else{amount=0;}

		}
		else{amount = 0;}
		return amount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public double transfer(Account type, double amount) {
		// TODO Auto-generated method stub

		if(type.canTransact() && this.canTransact()){
			if(amount <= this.getBalance()){
				this.withdraw(amount);
				type.deposit(amount);
			}
			else{amount=0;}}
		else{amount=0;}

		return amount;
	}
	public boolean canTransact(){
		return true;
	}



}
