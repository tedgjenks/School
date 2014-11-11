package edu.jenks.dist.banking;

/**
 * @author JenksT
 *
 */
public interface Account {

	double withdraw(double amount);
	double deposit(double amount);
	double transfer(double amount, Account targetAccount);
	double getBalance();
	void setBalance(double balance);
}
