package edu.banking.sweezy.kenneth;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractSavingsAccount;
import edu.jenks.dist.banking.Account;

public class SavingsAccount extends AbstractSavingsAccount {

	public static void main(String[] args) {
		// Creation and Linking of Test Accounts
		AbstractCheckingAccount accountOne = new CheckingAccount(50.0, 0.00411);
		AbstractSavingsAccount accountTwo = new SavingsAccount(250.0, 0.00411);
		Customer testing = new Customer("Denis", accountOne, accountTwo);
		testing.getCheckingAccount().setLinkedSavingsAccount(accountTwo);

		// Setting Values
		accountTwo.setMaxMonthlyTransactions(5);
		accountTwo.setNumTransactions(4);

		// Testing
		System.out.println("Start Savings Balance: " + accountTwo.getBalance() + "\nCurrent Checking Balance: "
				+ accountOne.getBalance() + "\nCurrent TXs: " + accountTwo.getNumTransactions() + "\n");
		accountTwo.transfer(accountOne, 5);
		System.out.println("End Savings Balance: " + accountTwo.getBalance() + "\nEnd Checking Balance "
				+ accountOne.getBalance() + "\nCurrent TXs: " + accountTwo.getNumTransactions());
	}

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(double balance, double APR) {
		super(balance, APR);
		setBalance(balance);
		setAccountInterestAPR(APR);
	}

	public boolean canTransact() {
		return getNumTransactions() < getMaxMonthlyTransactions();
	}

	public void payInterest(int days) {
		setBalance((getBalance() * Math.exp(((getAccountInterestAPR() / 100) * (days / DAYS_IN_A_YEAR)))));
	}

	public double transfer(Account transferTo, double amountToTransfer) {
		if (!canTransact()) {
			if (getBalance() <= amountToTransfer) {
				return 0.0;
			} else {
				setBalance(getBalance() - amountToTransfer);
				transferTo.setBalance(transferTo.getBalance() + amountToTransfer);
				setNumTransactions(getNumTransactions() + 1);
				return amountToTransfer;
			}
		} else {
			return 0.0;
		}
	}

	public double withdraw(double amount) {
		if (!canTransact()) {
			if (amount > getBalance()) {
				return 0.0;
			} else {
				setBalance(getBalance() - amount);
				setNumTransactions(getNumTransactions() + 1);
				return amount;
			}
		} else {
			return 0.0;
		}
	}

}
