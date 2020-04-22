package edu.banking.page.javin;
import edu.jenks.dist.banking.*;
public class CheckingAccount extends AbstractCheckingAccount{
	public CheckingAccount() {
		super();
	}
	public CheckingAccount(double balance, double accountInterestAPR) {
		super(balance, accountInterestAPR);
		setBalance(balance);
		setAccountInterestAPR(accountInterestAPR);
	}
	@Override
	public void payInterest(int arg0) {
		// TODO Auto-generated method stub
		setBalance(getBalance() * Math.pow(Math.E, (getAccountInterestAPR()/100 * (arg0 / DAYS_IN_A_YEAR))));
	}

	@Override
	public double transfer(Account arg0, double arg1) {
		if(getBalance() < arg1) {
			return 0;
		}
		if(getLinkedSavingsAccount() != null && getLinkedSavingsAccount().equals(arg0)) {
			if(!getLinkedSavingsAccount().canTransact()) {
				return 0;
			}
			getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
		}
		arg0.setBalance(arg0.getBalance() + arg1);
		setBalance(getBalance() - arg1);
		return arg1;
	}

	@Override
	public double withdraw(double arg0) {
		if(arg0 > getBalance()) {
			double moneyLeft = arg0;
			if(getLinkedSavingsAccount() != null && getLinkedSavingsAccount().canTransact()) {
					if(arg0 > getLinkedSavingsAccount().getBalance() + getBalance()) {
						if(isOverdraftProtected()) {
							if (arg0 < getLinkedSavingsAccount().getBalance() + getBalance() + getOverdraftMax()) {
								moneyLeft -= getLinkedSavingsAccount().getBalance();
								getLinkedSavingsAccount().setBalance(0.0);
								setBalance(getBalance() - moneyLeft);
								setNumberOverdrafts(getNumberOverdrafts() + 1);
								getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
								return arg0;
							}
						}
					}else {
						moneyLeft -= getBalance();
						setBalance(0.0);
						getLinkedSavingsAccount().setBalance(getLinkedSavingsAccount().getBalance() - moneyLeft);
						getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
						return arg0;
					}
			}
			if(isOverdraftProtected()) {
				if (arg0 < getOverdraftMax() + getBalance()) {
					setBalance(getBalance() - moneyLeft);
					setNumberOverdrafts(getNumberOverdrafts() + 1);
					return arg0;
				}
			}
			return 0.0;
		}
		setBalance(getBalance() - arg0);
		return arg0;
	}
}
