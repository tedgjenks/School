package edu.banking.mariscal.juan;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount  {

	public SavingsAccount(){

	}
	public SavingsAccount(double balance){
		setBalance(balance);
	}
	public SavingsAccount(double balance, double accountInterestAPR){
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}
	@Override
	public void payInterest(int days) {
		// TODO Auto-generated method stub
		if(getBalance()> 0){
		double years = days/DAYS_IN_A_YEAR;
		setBalance(getBalance() * Math.pow(Math.E, ((this.getAccountInterestAPR()/100) * years)));
		}
	}

	@Override
	public double withdraw(double amount) {
		// TODO Auto-generated method stub

		if( amount <= this.getBalance() && canTransact()){
			setBalance(getBalance()-amount);
			setNumTransactions(getNumTransactions() + 1);
		}
		else{   amount=0;  }
		return amount;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean canTransact() {
		// TODO Auto-generated method stub

		return (getMaxMonthlyTransactions() > getNumTransactions());
	}
	@Override
	public double transfer(Account type, double amount) {
		if(type.canTransact() && this.canTransact()){
			if(amount <= this.getBalance()){
				this.withdraw(amount);
				
				type.deposit(amount);
			}
			else{amount=0;}}
		else{amount=0;}

		return amount;
	}
	public double deposit(double amount ){
		if(canTransact()){
			setBalance(getBalance() + amount);
			setNumTransactions(getNumTransactions() + 1);
			
		}
		else{amount = 0;}
		return amount;
	}

}
