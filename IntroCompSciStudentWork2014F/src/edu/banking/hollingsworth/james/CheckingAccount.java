package edu.banking.hollingsworth.james;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {
	
	public CheckingAccount() {}
	
	public CheckingAccount(double balance, double accountInterestAPR)  {
		this.setBalance(balance);
		this.setAccountInterestAPR(accountInterestAPR);
	}

	@Override
	public void payInterest(int days) {
		setBalance(getBalance() * Math.pow(Math.E, ((getAccountInterestAPR() / 100.0) * ((double) days / DAYS_IN_A_YEAR))));
	}

	@Override
	public double transfer(Account acc, double amount) {
		if(this.getBalance() >= amount && acc.canTransact()) {
			this.setBalance(this.getBalance() - amount);
			acc.deposit(amount);
			return amount;
		}
		else return 0;
	}

	@Override
	public double withdraw(double requestedWithdrawal) {
		if(this.getBalance() >= requestedWithdrawal) {
			
			this.setBalance(this.getBalance() - requestedWithdrawal);
			return requestedWithdrawal;
			
		} else if(	this.getLinkedSavingsAccount() != null && this.getLinkedSavingsAccount().canTransact()) {
			if(this.getLinkedSavingsAccount().getBalance() + this.getBalance() >= requestedWithdrawal) {
				
				double tmp = requestedWithdrawal;
				tmp -= this.getBalance();
				this.setBalance(0.0);
				this.getLinkedSavingsAccount().setBalance(this.getLinkedSavingsAccount().getBalance() - tmp);
				return requestedWithdrawal;
				
			} else if(Math.abs(this.getLinkedSavingsAccount().getBalance() + this.getBalance() - requestedWithdrawal) < this.getOverdraftMax()) {
				
				double tmp = requestedWithdrawal;
				tmp -= this.getBalance() + this.getLinkedSavingsAccount().getBalance();
				this.setBalance(0.0);
				this.getLinkedSavingsAccount().setBalance(0);
				this.setBalance(this.getBalance() - tmp);
				this.setNumberOverdrafts(this.getNumberOverdrafts() + 1);
				return requestedWithdrawal;
				
			}
		} else if(this.isOverdraftProtected() && requestedWithdrawal - this.getBalance() <= this.getOverdraftMax()) {
			
			this.setNumberOverdrafts(this.getNumberOverdrafts() + 1);
			this.setBalance(this.getBalance() - requestedWithdrawal);
			return requestedWithdrawal;
			
		}
		return 0.0;
	}

	public static void main(String[] args) {
		CheckingAccount c = new CheckingAccount(0, 0);
		c.setLinkedSavingsAccount(new SavingsAccount(50, 0));
		c.getLinkedSavingsAccount().setMaxMonthlyTransactions(50);
		c.setOverdraftProtected(true);
		c.setOverdraftMax(500);
		System.out.println(c.withdraw(500));
		System.out.println(c.getBalance());
	}
	
}
