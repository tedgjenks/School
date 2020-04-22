package edu.banking.sweezy.kenneth;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class CheckingAccount extends AbstractCheckingAccount {

	public static void main(String[] args) {
		// Creation and Linking of Accounts
		AbstractCheckingAccount accountOne = new CheckingAccount(50.0, 0.00411);
		AbstractSavingsAccount accountTwo = new SavingsAccount(250.0, 0.004);
		Customer testing = new Customer("Denis", accountOne, accountTwo);
		testing.getCheckingAccount().setLinkedSavingsAccount(accountTwo);

		// Setting Values
		accountOne.setOverdraftProtected(true);
		accountOne.setOverdraftFee(20);
		accountOne.setOverdraftMax(500);

		// Testing
		System.out.println("Start Savings Balance: " + accountTwo.getBalance() + "\nStart Checking Balance: "
				+ accountOne.getBalance());
		System.out.println("Withdrawn: " + accountTwo.transfer(accountOne, 100));
		System.out.println("End Savings Balance: " + accountTwo.getBalance() + "\nEnd Checking Balance: "
				+ accountOne.getBalance());
	}

	public CheckingAccount() {
		super();
	}

	public CheckingAccount(double balance, double APR) {
		super(balance, APR);
		setBalance(balance);
		setAccountInterestAPR(APR);
	}

	public void payInterest(int days) {
		setBalance((getBalance() * Math.exp(((getAccountInterestAPR() / 100) * (days / DAYS_IN_A_YEAR)))));
	}

	public double transfer(Account transferTo, double amountToTransfer) {
		if (getBalance() < amountToTransfer) {
			return 0.0;
		} else {
			if (getLinkedSavingsAccount() != null) {
				if (getLinkedSavingsAccount().equals(transferTo)) {
					if (getLinkedSavingsAccount().canTransact()) {
						transferTo.setBalance(transferTo.getBalance() + amountToTransfer);
						setBalance(getBalance() - amountToTransfer);
						getLinkedSavingsAccount()
								.setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
						return amountToTransfer;
					}
				}
			} else {
				transferTo.setBalance(transferTo.getBalance() + amountToTransfer);
				setBalance(getBalance() - amountToTransfer);
				return amountToTransfer;
			}
			return 0.0;
		}
	}

	public double withdraw(double withdrawAmount) {
		double tempAmount = 0;
		if (getLinkedSavingsAccount() != null) {
			if (withdrawAmount >= (getBalance() + getLinkedSavingsAccount().getBalance())) {
				if (Math.abs(withdrawAmount
						- (getBalance() + getLinkedSavingsAccount().getBalance())) <= getOverdraftMax()) {
					if (getLinkedSavingsAccount().canTransact()) {
						tempAmount = getBalance() + getLinkedSavingsAccount().getBalance();
						setBalance(tempAmount - withdrawAmount);
						getLinkedSavingsAccount().setBalance(0.0);
						setNumberOverdrafts(getNumberOverdrafts() + 1);
						getLinkedSavingsAccount()
								.setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
						return tempAmount - withdrawAmount;
					} else {
						return 0.0;
					}
				}
				return 0.0;
			} else {
				if (getLinkedSavingsAccount().canTransact()) {
					tempAmount = withdrawAmount - getBalance();
					setBalance(0);
					getLinkedSavingsAccount().withdraw(tempAmount);
					getLinkedSavingsAccount().setNumTransactions(getLinkedSavingsAccount().getNumTransactions() + 1);
					return withdrawAmount;
				} else {
					return 0.0;
				}
			}
		} else {
			if (withdrawAmount < getBalance()) {
				setBalance(getBalance() - withdrawAmount);
				return withdrawAmount;
			} else if (isOverdraftProtected()) {
				if ((getBalance() + getOverdraftMax()) >= withdrawAmount) {
					setBalance(getBalance() - withdrawAmount);
					setNumberOverdrafts(getNumberOverdrafts() + 1);
					return withdrawAmount;
				} else {
					return 0.0;
				}
			} else {
				return 0.0;
			}
		}
	}

}
