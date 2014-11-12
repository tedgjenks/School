package edu.jenks.dist.banking;

/**
 * @author JenksT
 *
 */
public interface Account {

	/**
	 * @param amount the requested amount to be withdrawn from this account
	 * @return the amount actually withdrawn
	 */
	double withdraw(double amount);
	
	/**
	 * @param amount the requested amount to be deposited to this account
	 * @return the amount actually deposited
	 */
	double deposit(double amount);
	
	/**
	 * @param amount the requested amount to be transferred from this account to targetAccount
	 * @param targetAccount receives transfer
	 * @return the amount actually transferred
	 */
	double transfer(double amount, Account targetAccount);
	
	double getBalance();
	
	void setBalance(double balance);
}
