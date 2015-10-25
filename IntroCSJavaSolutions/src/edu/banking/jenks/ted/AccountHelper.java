package edu.banking.jenks.ted;

import edu.jenks.dist.banking.Account;

public class AccountHelper {

	public static void payInterest(Account account, int days) {
		double balance = account.getBalance();
		if(balance > 0) {
			double timeInYears = days/365.0;
			balance *= Math.pow(Math.E, account.getAccountInterestAPR() / 100 * timeInYears);
			account.setBalance(balance);
		}
	}
	
	public static double standardWithdraw(Account account, double requestedWithdrawal) {
		double withdrawal = 0;
		if(requestedWithdrawal > 0) {
			double balance = account.getBalance();
			if(requestedWithdrawal <= balance)
				withdrawal = requestedWithdrawal;
			balance -= withdrawal;
			account.setBalance(balance);
		}
		return withdrawal;
	}
	
	public static double transfer(Account src, Account dest, double amt) {
		double transferAmt = 0;
		double srcBalance = src.getBalance();
		if(srcBalance >= amt)
			dest.deposit(src.withdraw(amt));
		return transferAmt;
	}
}
