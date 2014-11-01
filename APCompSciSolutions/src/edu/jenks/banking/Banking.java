/**
 * 
 */
package edu.jenks.banking;

import edu.jenks.banking.dist.AbstractBanking;
import edu.jenks.banking.dist.AbstractCheckingAccount;
import edu.jenks.banking.dist.Account;

/**
 * @author Ted Jenks
 *
 */
public class Banking extends AbstractBanking {

	/* (non-Javadoc)
	 * @see jenks.unit4.AbstractBanking#performMaintenance()
	 */
	@Override
	public void performMaintenance(int days) {
		if(days < 0)
			days = 0;
		AbstractCheckingAccount checkingAccount = getCheckingAccount();
		Account savingsAccount = getSavingsAccount();
		applyFees(checkingAccount, savingsAccount);
		payInterest(days, checkingAccount, savingsAccount);
	}
	
	private void applyFees(AbstractCheckingAccount checkingAccount, Account savingsAccount) {
		applyOverdraftFees(checkingAccount);
		applyBankingFee(checkingAccount, savingsAccount);
	}
	
	private void applyOverdraftFees(AbstractCheckingAccount checkingAccount) {
		if(checkingAccount != null) {
			int numberOverdrafts = checkingAccount.getNumberOverdrafts();
			if(numberOverdrafts > 0) {
				int totalOverdraftFee = checkingAccount.getOverdraftFee() * numberOverdrafts;
				double withdrawal = checkingAccount.withdraw(totalOverdraftFee);
				double difference = totalOverdraftFee - withdrawal;
				if(difference > 0) {
					double balance = checkingAccount.getBalance() - difference;
					checkingAccount.setBalance(balance);
				}
			}
			checkingAccount.setNumberOverdrafts(0);
		}
	}
	
	private void applyBankingFee(AbstractCheckingAccount checkingAccount, Account savingsAccount) {
		double combinedBalance = 0;
		if(checkingAccount != null)
			combinedBalance += checkingAccount.getBalance();
		if(savingsAccount != null)
			combinedBalance += savingsAccount.getBalance();
		if(combinedBalance < getMinNoFeeCombinedBalance()) {
			int bankingFee = getBankingFee();
			double withdrawal = checkingAccount.withdraw(bankingFee);
			double difference = bankingFee - withdrawal;
			if(difference > 0) {
				double balance = checkingAccount.getBalance() - difference;
				checkingAccount.setBalance(balance);
			}
		}
	}
	
	private void payInterest(int days, AbstractCheckingAccount checkingAccount, Account savingsAccount) {
		if(checkingAccount != null)
			checkingAccount.payInterest(days);
		if(savingsAccount != null)
			savingsAccount.payInterest(days);
	}

}
