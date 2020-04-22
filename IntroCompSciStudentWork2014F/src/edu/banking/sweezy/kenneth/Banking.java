package edu.banking.sweezy.kenneth;

import edu.jenks.dist.banking.AbstractBanking;
import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

public class Banking extends AbstractBanking {
	public void addCustomer(String arg0, AbstractCheckingAccount arg1, AbstractSavingsAccount arg2) {

	}

	public void performMaintenance(int days) {
		/*
		 * IF CHECKING BALANCE IS NEGATIVE, SHOULD CHECK TO SEE IF THERE IS ENOUGH
		 * BALANCE TO COVER THE NEGATIVE
		 * 
		 */
		for (AbstractCustomer curCust : getCustomers()) {
			if (curCust.getCheckingAccount().getBalance() < 0) {
				if (curCust.getSavingsAccount() != null) {
					curCust.getSavingsAccount().setBalance(curCust.getSavingsAccount().getBalance()
							- Math.abs(curCust.getCheckingAccount().getBalance()));
				}
			} else {
				if (curCust.getSavingsAccount() != null) {
					if ((curCust.getCheckingAccount().getBalance()
							+ curCust.getSavingsAccount().getBalance()) < getMinNoFeeCombinedBalance()) {
						int numOver = curCust.getCheckingAccount().getNumberOverdrafts();
						for (int i = 0; i < numOver; i++) {
							curCust.getCheckingAccount().setBalance(curCust.getCheckingAccount().getBalance()
									- curCust.getCheckingAccount().getOverdraftFee());
						}
					}
					curCust.getCheckingAccount().payInterest(days);
					curCust.getSavingsAccount().payInterest(days);
				} else {
					if (curCust.getCheckingAccount().getBalance() < getMinNoFeeCombinedBalance()) {
						int numOver = curCust.getCheckingAccount().getNumberOverdrafts();
						for (int i = 0; i < numOver; i++) {
							curCust.getCheckingAccount().setBalance(curCust.getCheckingAccount().getBalance()
									- curCust.getCheckingAccount().getOverdraftFee());
						}
					}
					curCust.getCheckingAccount().payInterest(days);
				}
			}
			curCust.getCheckingAccount().setNumberOverdrafts(0);
			if (curCust.getSavingsAccount() != null) {
				curCust.getSavingsAccount().setNumTransactions(0);
			}
		}
	}

	public boolean removeCustomer(String arg0) {
		return false;
	}
}
