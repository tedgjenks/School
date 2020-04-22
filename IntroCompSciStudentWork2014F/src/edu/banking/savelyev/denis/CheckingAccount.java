package edu.banking.savelyev.denis;

import edu.jenks.dist.banking.*;

public class CheckingAccount extends AbstractCheckingAccount {
	

	public static void main(String[] args) {
		AbstractCheckingAccount test1 = new CheckingAccount(50.0, 0.02);
		AbstractSavingsAccount test2 = new SavingsAccount(250.0, 0.02);
		test1.setOverdraftProtected(false);
		//test1.setOverdraftFee(2);
		//test2.setBalance(50.0);
		test2.setMaxMonthlyTransactions(5);
		test2.setNumTransactions(5);
		System.out.println("Amount transferred: " + test1.transfer(test2, 5.0) + " " + "Balance: " + test1.getBalance());
		//System.out.println("Savings Balance: " + test1.getLinkedSavingsAccount().getBalance());
		System.out.println("Savings Balance: " + test2.getBalance());
	}
	
	public CheckingAccount() {
		super();
	}
	
	public CheckingAccount(double accountBalance, double accountAPR) {
		super(accountBalance, accountAPR);
		setBalance(accountBalance);
		setAccountInterestAPR(accountAPR);
		
	}

	public void payInterest(int days) {
		double years = days / DAYS_IN_A_YEAR;
		setBalance(getBalance()*(Math.pow(Math.E, getAccountInterestAPR() * years)));
	}

	public double transfer(Account destination, double amount) {
		if(getLinkedSavingsAccount() != null) {
			if(getLinkedSavingsAccount().canTransact()) {
					if (getBalance() >= amount) {
						setBalance(getBalance() - amount);
						destination.setBalance(destination.getBalance() + amount);
						getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
						return amount;
					} else {
						return 0.0;
					}
			} else {
				return 0.0;
			}
		} else {
			return 0.0;
		}
	}

	public double withdraw(double withdrawAmount) {
		double payed = withdrawAmount;
		if(getLinkedSavingsAccount() != null) {
			if(getBalance() >= withdrawAmount) { //balance is greater than amount (able to transact)
				setBalance(getBalance() - withdrawAmount);
				return withdrawAmount;
			} else if(getBalance() + getLinkedSavingsAccount().getBalance() <= withdrawAmount) {  //overdraft  transaction
				if(isOverdraftProtected() == false) {
					if(getLinkedSavingsAccount().canTransact()) {
						double temp = getBalance() - withdrawAmount;
						setNumberOverdrafts(getNumberOverdrafts() + 1);
						setBalance(temp);
						getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
						setNumberOverdrafts(getNumberOverdrafts() + 1);
						return withdrawAmount;
					} else {
						return 0.0;
					}
				} else {
					return 0.0;
				}
			} else {
				if(getLinkedSavingsAccount().getBalance() > 0) {
					payed -= getBalance();
					setBalance(0);
					getLinkedSavingsAccount().setBalance(getLinkedSavingsAccount().getBalance() - payed);
					return withdrawAmount;
				} else {
					payed -= getLinkedSavingsAccount().getBalance();
					getLinkedSavingsAccount().setBalance(0.0);
					return withdrawAmount;
				}
			}
		} else {
			if(getBalance() >= withdrawAmount) {
				setBalance(getBalance() - withdrawAmount);
				
				return withdrawAmount;
			} else {
				return 0.0;
			}
		}
	}

}
