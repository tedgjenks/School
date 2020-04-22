package edu.banking.rhodes.maddux;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public CheckingAccount() {

	}

	public CheckingAccount(double balance, double accountInterestAPR) {
		setAccountInterestAPR(accountInterestAPR);
		setBalance(balance);
	}

	public static void main(String[] args) {
		CheckingAccount ca = new CheckingAccount(25.0, 0.05);
		SavingsAccount sa = new SavingsAccount(500.00, 0.05);
		ca.setLinkedSavingsAccount(sa);
		ca.setOverdraftProtected(true);
		ca.setOverdraftMax(500.00);
		System.out.println("Balance before withdraw: " + ca.getBalance());
		System.out.println(ca.withdraw(600.00));
		System.out.println("Balance after withdraw: " + ca.getBalance());
		System.out.println(ca.getNumberOverdrafts());
		System.out.println(ca.getLinkedSavingsAccount().getBalance());
	}

	public void payInterest(int days) {
		double interestAmt = getBalance() * (Math.exp( (getAccountInterestAPR()/100) * (days / (double) DAYS_IN_A_YEAR)));
		setBalance(interestAmt);
	}

	public double transfer(Account destination, double amount) {
		if(this.getBalance() < amount) {
			return 0.0;
		} else {
			destination.setBalance(destination.getBalance() + amount);
			if(getLinkedSavingsAccount() != null) {
				getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
			}
			this.setBalance(this.getBalance() - amount);
			return amount;
		}
	}

	public double withdraw(double requestedWithdrawal) {
		double acctBalance = getBalance();
		double savingsBalance = 0;
		if (getLinkedSavingsAccount() != null) {
			System.out.println("Here1");
			if (getLinkedSavingsAccount().getNumTransactions() < getLinkedSavingsAccount().getMaxMonthlyTransactions()) {
				System.out.println("Here2");
				savingsBalance = getLinkedSavingsAccount().getBalance();
			}
		}
		double max = acctBalance + savingsBalance;
		if (isOverdraftProtected()) {
			max += getOverdraftMax();
		}
		if (requestedWithdrawal <= acctBalance) {
			System.out.println("Here3");
			setBalance(acctBalance - requestedWithdrawal);
			return requestedWithdrawal;
		} else if (requestedWithdrawal > acctBalance && requestedWithdrawal <= (acctBalance + savingsBalance)) {
			System.out.println("Here4");
			setBalance(0);
			getLinkedSavingsAccount().setBalance(savingsBalance - (requestedWithdrawal - acctBalance));
			getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
			return requestedWithdrawal;
		} else if (requestedWithdrawal > (acctBalance + savingsBalance) && requestedWithdrawal <= max) {
			System.out.println("Here5");
			if (isOverdraftProtected()) {
				double cover1 = requestedWithdrawal - acctBalance;
				setNumberOverdrafts(getNumberOverdrafts() + 1);
				setBalance(0);
				if(getLinkedSavingsAccount() != null) {
					getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
					getLinkedSavingsAccount().setBalance(0);
				}
				if(getLinkedSavingsAccount() != null) {
					cover1 -= savingsBalance;
				}
				setBalance(cover1 * -1);
				return requestedWithdrawal;
			}
		}
		return 0;
	}
}
