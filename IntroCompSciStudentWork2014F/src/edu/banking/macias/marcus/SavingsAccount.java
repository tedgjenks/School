package edu.banking.macias.marcus;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;
public class SavingsAccount extends AbstractSavingsAccount{
	public static void main(String[] args) {
		
	}
	public SavingsAccount() {
		
	}
	public SavingsAccount(double balance, double accountInterestAPR) {
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
		
	}
	
	public boolean canTransact() {
		
		if(getMaxMonthlyTransactions() == getNumTransactions()) {
			return false;
		}
		return true;
	}
	
	
	public void payInterest(int days) {
		setBalance(getBalance() * Math.exp((days / DAYS_IN_A_YEAR) * (getAccountInterestAPR()/100)));
		//setBalance(getBalance() + money);
	}

	
	public double transfer(Account destination, double amount) {
		if(!canTransact() || getBalance() < amount) {
			return 0;
		}else {
			destination.deposit(amount);
			setBalance(getBalance() - amount);
			this.setNumTransactions(this.getNumTransactions()+1);
			return amount;
		}
		
	}
	public double deposit(double depositAmount) {
		if(canTransact()) {
			setBalance(getBalance() + depositAmount);
			this.setNumTransactions(this.getNumTransactions()+1);
			return depositAmount;
		}
		return 0;
	}
	
	public double withdraw(double amount) {
		if(!canTransact() || amount > getBalance()) {
			return 0;
		}else {
			setBalance(getBalance() - amount);
			this.setNumTransactions(this.getNumTransactions()+1);
			return amount;
		}
		
	}

}
