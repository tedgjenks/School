/**
 * 
 */
package edu.jenks.dist.banking;

/**
 * <p>AbstractCustomer objects are comparable by name.</p>
 * 
 * @author Ted Jenks
 *
 */
public abstract class AbstractCustomer implements Comparable<AbstractCustomer> {
	
	private String name;
	private AbstractCheckingAccount checkingAccount;
	private AbstractSavingsAccount savingsAccount;

	/**
	 * @param name
	 */
	public AbstractCustomer(String name) {
		this(name, null, null);
	}
	
	/**
	 * @param name
	 * @param checkingAccount
	 * @param savingsAccount
	 */
	public AbstractCustomer(String name, AbstractCheckingAccount checkingAccount, AbstractSavingsAccount savingsAccount) {
		this.name = name;
		this.checkingAccount = checkingAccount;
		setSavingsAccount(savingsAccount);
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return
	 */
	public AbstractCheckingAccount getCheckingAccount() {
		return checkingAccount;
	}

	/**
	 * POSTCONDITION: links the existing savings account to this checking account
	 * 
	 * @param checkingAccount
	 */
	public void setCheckingAccount(AbstractCheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
		if(checkingAccount != null)
			checkingAccount.setLinkedSavingsAccount(savingsAccount);
	}

	/**
	 * @return
	 */
	public AbstractSavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	/**
	 * POSTCONDITION: links this savings account to the existing checking account.
	 * 
	 * @param savingsAccount
	 */
	public void setSavingsAccount(AbstractSavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
		if(checkingAccount != null)
			checkingAccount.setLinkedSavingsAccount(savingsAccount);
	}


}
