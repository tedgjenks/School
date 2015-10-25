/**
 * 
 */
package edu.banking.jenks.ted;

import edu.jenks.dist.banking.AbstractCheckingAccount;
import edu.jenks.dist.banking.AbstractCustomer;
import edu.jenks.dist.banking.AbstractSavingsAccount;

/**
 * @author Ted
 *
 */
public class Customer extends AbstractCustomer {

	/**
	 * @param name
	 */
	public Customer(String name) {
		this(name, null, null);
	}
	
	/**
	 * @param name
	 * @param checkingAccount
	 * @param savingsAccount
	 */
	public Customer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		super(name, checkingAccount, savingsAccount);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AbstractCustomer arg0) {
		return getName().compareTo(arg0.getName());
	}
	
	/**
	 * @return true if the customer names are equal, otherwise false
	 */
	public boolean equals(Object cust) {
		return getName().equals(cust.toString());
	}
	
	/** 
	 * @return the name of the customer
	 */
	public String toString() {
		return getName();
	}

}
