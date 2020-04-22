package edu.banking.macias.marcus;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	public CheckingAccount() {

	}

	public CheckingAccount(double balance, double accountInterestAPR) {
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}

	public void payInterest(int days) {
		setBalance(getBalance() * Math.exp(((double)days / DAYS_IN_A_YEAR) * (this.getAccountInterestAPR()/100)));
		//setBalance(getBalance() + money);
	}

	public double transfer(Account destination, double amount) {
		
		if (getBalance() < amount || !destination.canTransact()) {
			return 0;

		} else {
			destination.deposit(amount);
			setBalance(getBalance() - amount);
			
			//this.getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
			return amount;
		}

	}
	public static void main(String[] args) {
		CheckingAccount run = new CheckingAccount(100,10);
		SavingsAccount thing = new SavingsAccount(200,10);
		run.setLinkedSavingsAccount(thing);
		run.getLinkedSavingsAccount().setMaxMonthlyTransactions(5);
		run.getLinkedSavingsAccount().setNumTransactions(0);
		run.setOverdraftProtected(true);
		run.setOverdraftMax(50);
		
		System.out.println("Linked before withdraw  $" + run.getLinkedSavingsAccount().getBalance());
		System.out.println("Before withdraw  $" + run.getBalance());
		System.out.println(run.withdraw(101));
		System.out.println("Linked After withdraw  $" + run.getLinkedSavingsAccount().getBalance());
		System.out.println("After Withdraw  $" + run.getBalance());
		System.out.println(run.getLinkedSavingsAccount().getNumTransactions());
		//run.getLinkedSavingsAccount().deposit(100);
		//run.withdraw(100);
		
		/*System.out.println(run.withdraw(20));
		System.out.println("Linked before withdraw  $" + run.getLinkedSavingsAccount().getBalance());
		System.out.println("After Withdraw  $" + run.getBalance());
		*/
		
	}
	public double withdraw(double amount) {
		double remaining = amount;
		boolean isLinked = getLinkedSavingsAccount() != null;
		double max = getBalance() + this.getOverdraftMax();
		
		
		
		if(remaining <= getBalance()) {
			setBalance(getBalance() - remaining);
			return amount;
		}
		if(isLinked) {
			if((max + this.getLinkedSavingsAccount().getBalance()) < amount) {
				return 0;
			}else {
				remaining -= getBalance();
				if(remaining <= this.getLinkedSavingsAccount().getBalance() && this.getLinkedSavingsAccount().canTransact()) {
					setBalance(0);
					this.getLinkedSavingsAccount().setBalance(this.getLinkedSavingsAccount().getBalance() - remaining);
					this.getLinkedSavingsAccount().setNumTransactions(this.getLinkedSavingsAccount().getNumTransactions() + 1);
					return amount;
				}
				
				remaining -= this.getLinkedSavingsAccount().getBalance();
				if(remaining <= this.getOverdraftMax() && this.isOverdraftProtected() && this.getLinkedSavingsAccount().canTransact()) {
					setBalance(-remaining);
					this.getLinkedSavingsAccount().setBalance(0);
					this.getLinkedSavingsAccount().setNumTransactions(this.getLinkedSavingsAccount().getNumTransactions() + 1);
					this.setNumberOverdrafts(getNumberOverdrafts() + 1);
					return amount;
				}
			}
		}else if(max >= amount && this.isOverdraftProtected()) {
			setBalance(getBalance() - amount);
			this.setNumberOverdrafts(getNumberOverdrafts() + 1);
			return amount;
		}
		
		return 0;
	}

}
