package edu.banking.rhodes.maddux;

import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {

	public SavingsAccount() {
		
	}
	
	public SavingsAccount(double balance, double accountInterestAPR) {
		setAccountInterestAPR(accountInterestAPR);
		setBalance(balance);
	}
	
	public static void main(String[] args) {
		CheckingAccount ca = new CheckingAccount(50.00, 0.05);
		SavingsAccount sa = new SavingsAccount(250.00, 0.05);
		System.out.println(sa.canTransact());
		ca.setLinkedSavingsAccount(sa);
		sa.setMaxMonthlyTransactions(2);
		System.out.println(sa.canTransact());
		System.out.println("From method: " + sa.transfer(ca, 100.00));
		System.out.println("Savings Account Balance: " + sa.getBalance());
		System.out.println("Checking Account Balance: " + ca.getBalance());
		//System.out.println("Balance before withdraw: " + sa.getBalance());
		//System.out.println(sa.withdraw(500));
		//System.out.println("Balance after withdraw: " + sa.getBalance());
		System.out.println(sa.getNumTransactions());
		//System.out.println(sa.getMaxMonthlyTransactions());
	}
	
	public boolean canTransact() {
		return this.getNumTransactions() < this.getMaxMonthlyTransactions();
	}

	public double deposit(double depositAmount) {
		if(canTransact()) {
			setBalance(getBalance() + depositAmount);
			setNumTransactions(getNumTransactions() + 1);
			return depositAmount;
		}
		return 0.0;
	}
	
	public void payInterest(int days) {
		double interestAmt = getBalance() * (Math.exp((getAccountInterestAPR()/100) * (days/(double)DAYS_IN_A_YEAR)));
		setBalance(interestAmt);
	}

	public double transfer(Account destination, double amount) {
		if(canTransact()) {
			if(getBalance() < amount) {
				return 0.0;
			} else {
				System.out.println("Here");
				setNumTransactions(getNumTransactions() + 1);
				destination.setBalance(destination.getBalance() + amount);
				this.setBalance(this.getBalance() - amount);
				return amount;
			}
		}
		return 0.0;
	}

	public double withdraw(double requestedWithdrawal) {
		if(getNumTransactions() < getMaxMonthlyTransactions()) {
			if(getBalance() >= requestedWithdrawal) {
				setNumTransactions(getNumTransactions() + 1);
				setBalance(getBalance() - requestedWithdrawal);
				return requestedWithdrawal;
			}
		}
		return 0.0;
	}

}
