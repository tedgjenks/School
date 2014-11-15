/**
 * 
 */
package edu.banking.jenks.ted;

import edu.jenks.dist.banking.Account;
import edu.jenks.dist.banking.AccountGen;
import edu.jenks.dist.banking.CheckingAccount;

/**
 * @author JenksT
 *
 */
public class CheckingAccountGen extends AccountGen implements CheckingAccount {

	/**
	 * 
	 */
	public CheckingAccountGen() {
		this(0);
	}

	/**
	 * @param balance
	 */
	public CheckingAccountGen(double balance) {
		super(balance);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.banking.Account#deposit(double)
	 */
	@Override
	public double deposit(double amount) {
		setBalance(getBalance() + amount);
		return amount;
	}

	/**
	 * If amount is greater than the balance, nothing is transferred.
	 * 
	 * @param amount the requested amount to be transferred from this account to targetAccount
	 * @param targetAccount receives transfer
	 * @return the amount actually transferred
	 */
	@Override
	public double transfer(double amount, Account targetAccount) {
		double amtWithdrawn = withdraw(amount);
		if(amtWithdrawn > 0)
			targetAccount.deposit(amtWithdrawn);
		return amtWithdrawn;
	}

	/**
	 * If amount is greater than the balance, nothing is withdrawn.
	 * 
	 * @param amount the requested amount to be withdrawn from this account
	 * @return the amount actually withdrawn
	 */
	@Override
	public double withdraw(double amount) {
		double amtWithdrawn = 0;
		double balance = getBalance();
		if(balance >= amount) {
			balance -= amount;
			setBalance(balance);
			amtWithdrawn = amount;
		}
		return amtWithdrawn;
	}

}
