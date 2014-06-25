package edu.jenks.banking;

import edu.jenks.banking.dist.Account;

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
}
