/**
 * 
 */
package edu.jenks.banking;

/**
 * @author JenksT
 *
 */
public interface Customer {

	void addSavingsAccount(SavingsAccount savingsAccount);
	void addCheckingAccount(CheckingAccount checkingAccount);
	SavingsAccount getSavingsAccount();
	CheckingAccount getCheckingAccount();
}
