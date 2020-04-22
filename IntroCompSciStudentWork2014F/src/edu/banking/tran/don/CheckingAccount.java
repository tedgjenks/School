package edu.banking.tran.don;

import edu.jenks.dist.banking.*;

public class CheckingAccount extends AbstractCheckingAccount {
	double yearlyRate;
	double totBalance;

	public static void main(String args[]) {
		System.out.println(-15 / 3);
	}

	public CheckingAccount() {

	}

	public CheckingAccount(double balance, double accountInterestAPR) {
		this.setBalance(balance);
		this.setAccountInterestAPR(accountInterestAPR);
		totBalance = balance;
		yearlyRate = accountInterestAPR;
	}

	public void payInterest(int days) {
		this.setBalance(this.getBalance() * Math.exp((this.getAccountInterestAPR()/100.0) * (days / DAYS_IN_A_YEAR)));
	}

	public double transfer(Account dest, double amount) {
		SavingsAccount temp = (SavingsAccount) dest;
		// double tempTran = amount;
		if (!dest.canTransact()) {
			return 0.0;
		}
		if(this.getBalance() < amount) {
			return 0.0;
		}
		if (temp.getNumTransactions() < temp.getMaxMonthlyTransactions()) {
			this.setBalance(((double) this.getBalance() - (double) amount));
			temp.setBalance(((double) temp.getBalance() + (double) amount));
			temp.setNumTransactions(temp.getNumTransactions() + 1);
			return amount;
		}
		return 0;
		// double thisBalance = this.getBalance();

	}

	public double withdraw(double requestedWithdrawl) {
		double trans = requestedWithdrawl;
		if (requestedWithdrawl > this.getBalance()) {
			if (this.getLinkedSavingsAccount() == null || !this.getLinkedSavingsAccount().canTransact()) {
				if (this.isOverdraftProtected()) {
					
					if (this.getOverdraftMax() + this.getBalance() >= requestedWithdrawl) {
						this.setNumberOverdrafts(this.getNumberOverdrafts() + 1);
						this.setBalance(this.getBalance() - requestedWithdrawl);
						return requestedWithdrawl;
					}
					
				} else {
					return 0.0;
				}
				return 0.0;
			}
			/*
			if (!this.getLinkedSavingsAccount().canTransact()) {
				return 0.0;
			}
			*/
			if (this.getLinkedSavingsAccount().getBalance() + this.getBalance() < requestedWithdrawl) {
				if (this.isOverdraftProtected()) {
					if (this.getOverdraftMax() + this.getLinkedSavingsAccount().getBalance()
							+ this.getBalance() >= requestedWithdrawl) {
						this.setNumberOverdrafts(this.getNumberOverdrafts() + 1);
						trans -= this.getBalance();
						trans -= this.getLinkedSavingsAccount().getBalance();
						this.setBalance(0.0 - trans);
						this.getLinkedSavingsAccount().setBalance(0.0);
						this.getLinkedSavingsAccount()
								.setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
						return requestedWithdrawl;
					} else {
						return 0.0;
					}
				} else {
					return 0.0;
				}
			}
			trans -= this.getBalance();
			this.setBalance(0.0);
			this.getLinkedSavingsAccount().setBalance(this.getLinkedSavingsAccount().getBalance() - trans);
			this.getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
			return requestedWithdrawl;
		}
		this.setBalance(this.getBalance() - requestedWithdrawl);
		return requestedWithdrawl;
	}

}
