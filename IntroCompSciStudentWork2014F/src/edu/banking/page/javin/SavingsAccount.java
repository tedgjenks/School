package edu.banking.page.javin;
import edu.jenks.dist.banking.*;
public class SavingsAccount extends AbstractSavingsAccount{
	public SavingsAccount() {
		super();
	}
	public SavingsAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}
	@Override
	public boolean canTransact() {
		return getMaxMonthlyTransactions() > getNumTransactions();
	}

	@Override
	public void payInterest(int days) {
		setBalance(getBalance() * Math.pow(Math.E, (getAccountInterestAPR()/100 * ((double)days / DAYS_IN_A_YEAR))));
		
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		if(getBalance() < arg1 || !(canTransact())) {
			return 0;
		}
		setBalance(getBalance() - arg1);
		arg0.setBalance(arg0.getBalance() + arg1);
		setNumTransactions(getNumTransactions() + 1);
		return arg1;
	}

	@Override
	public double withdraw(double arg0) {
		if(canTransact()) {
			if(arg0 < getBalance()) {
				setBalance(getBalance() - arg0);
				setNumTransactions(getNumTransactions() + 1);
				return arg0;
			}
		}
		return 0;
	}
	
	public double deposit(double depositAmount) {
		if(canTransact()) {
			setBalance(getBalance() + depositAmount);
			setNumTransactions(getNumTransactions() + 1);
			return depositAmount;
		}
		return 0.0;
	}
	
	
}
